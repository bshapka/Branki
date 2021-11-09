package ui.gui.views.selectors;

import exceptions.NoDecksException;
import model.Deck;
import model.Selectable;
import ui.gui.GUI;
import ui.gui.enums.DialogMessage;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

// represents a deck selector used to select a deck for a study session
public class StudyDeckSelector extends Selector {

    // EFFECTS: calls superclass constructor, and then throws NoDecksException if decks is empty or
    //          contains a deck with no cards
    public StudyDeckSelector(List<Deck> decks) throws NoDecksException {
        super(decks.stream().map(d -> (Selectable) d).collect(Collectors.toList()), "Deck Selection");
        if (decks.size() == 0 || !decks.stream().allMatch(Deck::hasCards)) {
            throw new NoDecksException(DialogMessage.NO_DECKS_WITH_CARDS.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: as described in superclass, but with specific actionListener that calls GUI.showStudyDeckWindow
    protected void setupSubmitButton() {
        submitButton = new JButton("Select");
        submitButton.addActionListener(e -> GUI.showStudyDeckWindow((Deck) selectables.get(list.getSelectedIndex())));
    }

}
