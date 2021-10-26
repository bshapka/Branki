package persistence;

import model.Deck;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// citation: partially adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Test class for JsonWriter class
public class JsonWriterTest {

    @Test
    void testWriteInvalidFileName() {
        JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
        assertThrows(FileNotFoundException.class, () -> writer.write(new ArrayList<>()));
    }

    @Test
    void testWriteDecksEmpty() {
        JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
        List<Deck> decks = new ArrayList<>();
        try {
            writer.write(decks);
        }
        catch (IOException ex) {
            fail("Unable to write empty decks to json file.");
        }
        String expectedChecksum = getChecksum("./data/testReaderEmpty.json");
        String actualChecksum = getChecksum("./data/testWriterEmpty.json");
        assertEquals(expectedChecksum, actualChecksum);
    }

    @Test
    void testWriteDecksNotEmpty() {
        JsonWriter writer = new JsonWriter("./data/testWriterNotEmpty.json");
        List<Deck> decks = JsonTest.getDecks();
        try {
            writer.write(decks);
        }
        catch (IOException ex) {
            fail("Unable to write non-empty decks to json file.");
        }
        String expectedChecksum = getChecksum("./data/testReaderNotEmpty.json");
        String actualChecksum = getChecksum("./data/testWriterNotEmpty.json");
        assertEquals(expectedChecksum, actualChecksum);
    }

    private String getChecksum(String s) {
        String checksum = null;
        try (InputStream is = Files.newInputStream(Paths.get(s))) {
            checksum = DigestUtils.sha256Hex(is);
        } catch (IOException e) {
            fail("Unable to get expected checksum");
        }
        return checksum;
    }

}