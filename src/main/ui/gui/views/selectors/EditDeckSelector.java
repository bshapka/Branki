package ui.gui.views.selectors;

import exceptions.NoDecksException;
import model.Deck;
import model.Selectable;
import ui.gui.GUI;
import ui.gui.enums.DialogMessage;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

// represents a deck selector used to select a deck for editing
public class EditDeckSelector extends Selector {

    // EFFECTS: calls superclass constructor, and then throws NoDecksException if decks is empty
    public EditDeckSelector(List<Deck> decks) throws NoDecksException {
        super(decks.stream().map(d -> (Selectable) d).collect(Collectors.toList()), "Deck Selection");
        if (decks.isEmpty()) {
            throw new NoDecksException(DialogMessage.NO_DECKS.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: as described in superclass, but with specific actionListener that calls GUI.showEditDeckWindow
    protected void setupSubmitButton() {
        submitButton = new JButton("Select");
        submitButton.addActionListener(e -> GUI.showEditDeckWindow((Deck) selectables.get(list.getSelectedIndex())));
    }

}
