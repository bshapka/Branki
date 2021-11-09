package exceptions;

// represents exception thrown in StudyDeckSelector constructor if list of deck arg is empty
public class NoDecksException extends Exception {

    // EFFECTS: constructs an NoDecksException using the argument as an error message
    public NoDecksException(String message) {
        super(message);
    }

}
