package exceptions;

// represents exception thrown to indicate there are no decks when at least one deck is expected
public class NoDecksException extends Exception {

    // EFFECTS: constructs an NoDecksException using the argument as an error message
    public NoDecksException(String message) {
        super(message);
    }

}
