package ui.gui.views;

import exceptions.NoDecksException;
import model.Deck;
import ui.gui.GUI;
import ui.gui.enums.DialogMessage;

import javax.swing.*;
import java.util.List;

// represents a deck selector used to select a deck for a study session
public class StudyDeckSelector extends DeckSelector {

    // EFFECTS: throws NoDecksException if decks is empty or contains a deck with no cards,
    //          otherwise calls superclass constructor
    public StudyDeckSelector(List<Deck> decks) throws NoDecksException {
        super(decks);
        if (this.decks.size() == 0 || !this.decks.stream().allMatch(Deck::hasCards)) {
            throw new NoDecksException(DialogMessage.NO_DECKS_WITH_CARDS.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: as described in superclass, but with specific actionListener that calls GUI.showStudyDeckWindow
    protected void setupSubmitButton() {
        submitButton = new JButton("Select");
        submitButton.addActionListener(e -> GUI.showStudyDeckWindow(decks.get(list.getSelectedIndex())));
    }

}
