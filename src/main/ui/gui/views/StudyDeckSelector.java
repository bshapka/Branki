package ui.gui.views;

import exceptions.NoDecksWithCardsException;
import model.Deck;
import ui.gui.GUI;

import javax.swing.*;
import java.util.List;

// represents a deck selector used to select a deck for a study session
public class StudyDeckSelector extends DeckSelector {

    // EFFECTS: calls superclass constructor
    public StudyDeckSelector(List<Deck> decksWithCards) throws NoDecksWithCardsException {
        super(decksWithCards);
    }

    // MODIFIES: this
    // EFFECTS: as described in superclass, but with specific actionListener that calls GUI.showStudyDeckWindow
    protected void setupSubmitButton() {
        submitButton = new JButton("Select");
        submitButton.addActionListener(e -> GUI.showStudyDeckWindow(decksWithCards.get(list.getSelectedIndex())));
    }

}
