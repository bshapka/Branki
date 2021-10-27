package persistence;

import model.Card;
import model.Deck;
import model.Result;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

// provides list of deck used for testing JsonReader and JsonWriter
public class JsonTest {

    // EFFECTS: returns a fixed list of Deck used for testing JsonReader and JsonWriter
    public static List<Deck> getDecks() {
        List<Deck> decks = new ArrayList<>();
        // Add three decks
        IntStream.rangeClosed(1, 3).forEach(i -> decks.add(new Deck("Deck " + i)));
        // Add four cards to the first deck
        IntStream.rangeClosed(1, 4).forEach(i ->
                decks.get(0).addCard(new Card("question " + i, "answer " + i)));
        // Add two cards to the second deck
        IntStream.rangeClosed(1, 2).forEach(i ->
                decks.get(1).addCard(new Card("question " + i, "answer " + i)));
        // Don't add any cards to the third deck
        int minDifficulty = Result.MIN_DIFFICULTY;
        int maxDifficulty = Result.MAX_DIFFICULTY;
        LocalDateTime date = LocalDateTime.of(2020, 1, 1, 12, 0, 0);
        // Add two results to each card in the first deck
        decks.get(0).getCards().forEach(card -> card.addResult(new Result(3, minDifficulty, maxDifficulty, date)));
        decks.get(0).getCards().forEach(card -> card.addResult(new Result(4, minDifficulty, maxDifficulty, date)));
        return decks;
    }
}
