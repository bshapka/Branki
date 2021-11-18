package exceptions;

// represents exception thrown to indicate that a deck has no cards when at least one card is expected
public class DeckHasNoCardsException extends Exception {

    // EFFECTS: constructs an DeckHasNoCardsException using the argument as an error message
    public DeckHasNoCardsException(String message) {
        super(message);
    }

}
