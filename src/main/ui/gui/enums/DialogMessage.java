package ui.gui.enums;

import java.text.MessageFormat;

// represents various messages used in dialogue boxes in the GUI
public enum DialogMessage {

    NO_DECKS_WITH_CARDS(MessageFormat.format(
        "<html>{0}<br>{1}</html>",
        "You don't have any decks with cards.",
        "Please ensure there is a deck with a card and retry.")),
    STUDY_DECK_HAS_NO_CARDS("The deck to study has no cards."),
    STUDY_SESSION_COMPLETE("Study session has been completed!");

    private final String message;

    // EFFECTS: constructs instance using given fileName by prepending directory path
    DialogMessage(String message) {
        this.message = message;
    }

    // EFFECTS: returns path
    public String getMessage() {
        return message;
    }

}
