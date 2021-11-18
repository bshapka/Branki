package exceptions;

// represents exception thrown to indicate there are no decks with cards when at least one such deck is expected
public class NoDecksWithCardsException extends Exception {

    // EFFECTS: constructs an NoDecksWithCardsException using the argument as an error message
    public NoDecksWithCardsException(String message) {
        super(message);
    }

}
