package persistence;

import model.Deck;
import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

// citation: partially adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// represents a writer that converts a collection of Deck to JSON format and writes this JSON to a specified file
public class JsonWriter {

    private static final int INDENT_FACTOR = 4;
    private PrintWriter printWriter;
    private String filePath;

    // EFFECTS: constructs JsonWriter instance to write to filePath
    public JsonWriter(String filePath) {
        this.filePath = filePath;
    }

    // MODIFIES: this
    // EFFECTS: throws FileNotFoundException if filePath cannot be found,
    //          otherwise writes JSON representation of decks to file identified by filePath
    public void write(List<Deck> decks) throws FileNotFoundException {
        File file = new File(filePath);
        printWriter = new PrintWriter(file);
        JSONArray json = new JSONArray(decks);
        printWriter.write(json.toString(INDENT_FACTOR));
        printWriter.close();
    }

}
