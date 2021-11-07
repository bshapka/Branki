package exceptions;

// represents exception thrown in DeckSelector constructor if list of deck arg contains no decks with cards
public class NoDecksWithCardsException extends Exception {

    // EFFECTS: constructs an NoDecksWithCardsException using the argument as an error message
    public NoDecksWithCardsException(String message) {
        super(message);
    }

}
