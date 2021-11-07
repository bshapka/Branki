package exceptions;

// represents exception thrown in the StudyDeckWindow constructor if deck arg has no cards
public class DeckHasNoCardsException extends Exception {

    // EFFECTS: constructs an DeckHasNoCardsException using the argument as an error message
    public DeckHasNoCardsException(String message) {
        super(message);
    }

}
