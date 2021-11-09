package ui.gui.views.selectors;

import exceptions.NoDecksException;
import model.Deck;
import ui.gui.GUI;

import javax.swing.*;
import java.util.List;

// represents a deck selector used to select a deck for card creation
public class CreateCardDeckSelector extends DeckSelector {

    // EFFECTS: calls superclass constructor
    public CreateCardDeckSelector(List<Deck> decks) throws NoDecksException {
        super(decks);
    }

    // MODIFIES: this
    // EFFECTS: as described in (abstract) superclass, but with specific actionListener that
    //          calls GUI.showCreateCardWindow
    protected void setupSubmitButton() {
        submitButton = new JButton("Select");
        submitButton.addActionListener(e -> GUI.showCreateCardWindow((Deck) selectables.get(list.getSelectedIndex())));
    }

}
