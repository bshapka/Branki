package ui.gui.views.selectors;

import exceptions.NoDecksException;
import exceptions.NoDecksWithCardsException;
import model.Deck;
import ui.gui.GUI;

import javax.swing.*;
import java.util.List;

// represents a deck selector used to select a deck for card editing
public class EditCardDeckSelector extends NonEmptyDeckSelector {

    // EFFECTS: calls superclass constructor
    public EditCardDeckSelector(List<Deck> decks) throws NoDecksException, NoDecksWithCardsException {
        super(decks);
    }

    // MODIFIES: this
    // EFFECTS: as described in (abstract) superclass, but with specific actionListener that
    //          calls GUI.showEditCardCardSelector
    protected void setupSubmitButton() {
        submitButton = new JButton("Select");
        submitButton.addActionListener(e -> GUI.showEditCardCardSelector((Deck) selectables.get(list.getSelectedIndex())));
    }

}
