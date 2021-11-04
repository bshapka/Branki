package ui;

import model.Deck;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.List;

public abstract class App {

    // the file path for the json file used to save state
    protected static final String JSON_FILE_PATH = "./data/decks.json";
    // the jsonReader used to save application data to a file
    protected static final JsonReader jsonReader = new JsonReader(JSON_FILE_PATH);
    // the jsonReader used to read saved application data from a file
    protected static final JsonWriter jsonWriter = new JsonWriter(JSON_FILE_PATH);

    protected List<Deck> decks;

}
