package ui.gui.views.selectors;

import exceptions.NoDecksException;
import model.Deck;
import ui.gui.GUI;
import ui.gui.enums.DialogMessage;

import javax.swing.*;
import java.util.List;

// represents a deck selector used to select a deck for editing
public class EditDeckSelector extends DeckSelector {

    // EFFECTS: calls superclass constructor
    public EditDeckSelector(List<Deck> decks) throws NoDecksException {
        super(decks);
        if (decks.isEmpty()) {
            throw new NoDecksException(DialogMessage.NO_DECKS.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: as described in superclass, but with specific actionListener that calls GUI.showEditDeckWindow
    protected void setupSubmitButton() {
        submitButton = new JButton("Select");
        submitButton.addActionListener(e -> GUI.showEditDeckWindow(decks.get(list.getSelectedIndex())));
    }

}
