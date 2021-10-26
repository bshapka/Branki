package persistence;

import model.Deck;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// citation: partially adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// test class for JsonReader class
public class JsonReaderTest {

    @Test
    void testReadInvalidFileName() {
        JsonReader reader = new JsonReader("./data/my\0illegal:fileName.json");
        assertThrows(InvalidPathException.class, () -> reader.read());
    }

    @Test
    void testReadDecksEmpty() {
        JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
        List<Deck> expectedDecks = new ArrayList<>();
        List<Deck> actualDecks = null;
        try {
            actualDecks = reader.read();
        }
        catch (IOException ex) {
            fail("Unable to read empty decks from json file.");
        }
        assertEquals(expectedDecks, actualDecks);
    }

    @Test
    void testReadDecksNotEmpty() {
        JsonReader reader = new JsonReader("./data/testWriterNotEmpty.json");
        List<Deck> expectedDecks = JsonTest.getDecks();
        List<Deck> actualDecks = null;
        try {
            actualDecks = reader.read();
        }
        catch (IOException ex) {
            fail("Unable to read non-empty decks from json file.");
        }
        // get JSON representations of expectedDecks and actualDecks
        // to facilitate equality comparison
        String expectedDecksJson = new JSONArray(expectedDecks).toString();
        String actualDecksJson = new JSONArray(actualDecks).toString();
        assertEquals(expectedDecksJson, actualDecksJson);
    }

}