package persistence;

import model.Card;
import model.Deck;
import model.Result;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// citation: partially adapted from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// represents a reader that reads a specified JSON file and converts this JSON to a collection of Deck
public class JsonReader {

    private final String filePath;

    // EFFECTS: constructs a JsonReader with a given file path
    public JsonReader(String filePath) {
        this.filePath = filePath;
    }

    // EFFECTS: reads specified JSON file and returns list of Deck represented by the file
    public List<Deck> read() throws IOException {
        String fileContent = readFile(filePath);
        JSONArray decksJson = new JSONArray(fileContent);
        return parseDecks(decksJson);
    }

    // EFFECTS: reads file at given filePath into string and returns string
    private String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // EFFECTS: parses given JSON representation of a list of Deck and returns a list of Deck
    private List<Deck> parseDecks(JSONArray decksJson) {
        List<Deck> decks = new ArrayList<>();
        decksJson.forEach(deckJson -> decks.add(parseDeck((JSONObject) deckJson)));
        return decks;
    }

    // EFFECTS: parses given JSON representation of a Deck and returns a Deck
    private Deck parseDeck(JSONObject deckJson) {
        Deck deck = new Deck(deckJson.getString("name"));
        JSONArray cardsJson = deckJson.getJSONArray("cards");
        cardsJson.forEach(cardJson ->
                deck.addCard(parseCard((JSONObject) cardJson)));
        return deck;
    }

    // EFFECTS: parses given JSON representation of a Card and returns a Card
    private Card parseCard(JSONObject cardJson) {
        Card card = new Card(
                cardJson.getString("question"),
                cardJson.getString("answer"));
        JSONArray resultsJson = cardJson.getJSONArray("results");
        resultsJson.forEach(resultJson ->
                card.addResult(parseResult((JSONObject) resultJson)));
        return card;
    }

    // EFFECTS: parses given JSON representation of a Result and returns a Result
    private Result parseResult(JSONObject resultJson) {
        return new Result(
            resultJson.getInt("difficulty"),
            resultJson.getInt("minDifficulty"),
            resultJson.getInt("maxDifficulty"),
            LocalDateTime.parse(resultJson.getString("dateTime")));
    }

}