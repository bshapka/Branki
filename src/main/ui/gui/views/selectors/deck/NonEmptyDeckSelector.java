package ui.gui.views.selectors.deck;

import exceptions.NoDecksException;
import exceptions.NoDecksWithCardsException;
import model.Deck;
import model.Selectable;
import ui.gui.enums.DialogMessage;
import ui.gui.views.selectors.Selector;

import java.util.List;
import java.util.stream.Collectors;

// represents a deck selector used to select a non-empty deck
public abstract class NonEmptyDeckSelector extends Selector {

    // EFFECTS: calls superclass constructor, and then throws NoDecksException if decks is empty, or throws
    //          NoDecksWithCardsException if decks is not empty but no deck has cards
    public NonEmptyDeckSelector(List<Deck> decks) throws NoDecksException, NoDecksWithCardsException {
        super(decks.stream().filter(Deck::hasCards).map(d -> (Selectable) d).collect(Collectors.toList()),
                "Deck Selection");
        if (decks.isEmpty()) {
            throw new NoDecksException(DialogMessage.NO_DECKS.getMessage());
        } else if (decks.stream().noneMatch(Deck::hasCards)) {
            throw new NoDecksWithCardsException(DialogMessage.NO_DECKS_WITH_CARDS.getMessage());
        }
    }

}
