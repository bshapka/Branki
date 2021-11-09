package ui.gui.views.selectors.deck;

import exceptions.NoDecksException;
import model.Deck;
import model.Selectable;
import ui.gui.enums.DialogMessage;
import ui.gui.views.selectors.Selector;

import java.util.List;
import java.util.stream.Collectors;

// represents a deck selector used to select a deck
public abstract class DeckSelector extends Selector {

    // EFFECTS: calls superclass constructor, and then throws NoDecksException if decks is empty
    public DeckSelector(List<Deck> decks) throws NoDecksException {
        super(decks.stream().map(d -> (Selectable) d).collect(Collectors.toList()), "Deck Selection");
        if (decks.isEmpty()) {
            throw new NoDecksException(DialogMessage.NO_DECKS.getMessage());
        }
    }

}
