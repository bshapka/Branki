package ui.gui.enums;

import java.text.MessageFormat;

// represents various messages used in dialogue boxes in the GUI
public enum DialogMessage {

    NO_DECKS_WITH_CARDS(MessageFormat.format(
        "<html>{0}<br>{1}</html>",
        "There aren't any decks with cards.",
        "Please ensure there is a deck with a card and retry.")),
    STUDY_DECK_HAS_NO_CARDS("The deck to study has no cards."),
    STUDY_SESSION_COMPLETE("Study session has been completed!"),
    DECK_DELETED("The deck has been deleted."),
    DECK_UPDATED("The deck has been updated."),
    NO_DECKS(MessageFormat.format(
            "<html>{0}<br>{1}</html>",
            "There aren't any decks.",
            "Please ensure there is a deck and retry.")),
    DECK_CREATED("The deck has been created."),
    CARD_CREATED("The card has been created."),
    CARD_UPDATED("The card has been updated."),
    CARD_DELETED("The card has been deleted.");

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
