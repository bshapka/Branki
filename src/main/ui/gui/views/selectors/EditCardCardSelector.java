package ui.gui.views.selectors;

import model.Card;
import model.Deck;
import model.Selectable;
import ui.gui.GUI;

import javax.swing.*;
import java.util.stream.Collectors;

// represents a card selector used to select a card for editing
public class EditCardCardSelector extends Selector {

    private Deck deck;

    // EFFECTS: calls superclass constructor and initializes deck
    public EditCardCardSelector(Deck deck) {
        super(deck.getCards().stream().map(d -> (Selectable) d).collect(Collectors.toList()), "Card Selection");
        this.deck = deck;
    }

    // MODIFIES: this
    // EFFECTS: as described in superclass, but with specific actionListener that calls GUI.showEditCardWindow
    protected void setupSubmitButton() {
        submitButton = new JButton("Select");
        submitButton.addActionListener(e -> GUI.showEditCardWindow(
                deck, (Card) selectables.get(list.getSelectedIndex())));
    }

}

