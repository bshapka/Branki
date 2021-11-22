package model;

import org.json.JSONPropertyIgnore;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

// represents a deck with a name and a list of cards
public class Deck implements Selectable {

    private String name;
    private final List<Card> cards;

    // EFFECTS: constructs a Deck. Sets name to given value, and cards to an empty list.
    public Deck(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    @Override
    @JSONPropertyIgnore
    // EFFECTS: returns name as description
    public String getDescription() {
        return name;
    }

    // EFFECTS: returns cards
    public List<Card> getCards() {
        return cards;
    }

    // EFFECTS: returns true if cards contains a difficult card (as per card.isDifficult()),
    //          else returns false.
    @JSONPropertyIgnore
    public boolean hasDifficultCards() {
        for (Card card : cards) {
            if (card.isDifficult()) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns card at given index in cards if 0 <= index < cards.size(), else throws
    //          IndexOutOfBoundsException
    @JSONPropertyIgnore
    public Card getCard(int index) throws IndexOutOfBoundsException {
        return cards.get(index);
    }

    // EFFECTS: returns size of cards
    @JSONPropertyIgnore
    public int getSize() {
        return cards.size();
    }

    // EFFECTS: returns true if cards is not empty, else returns false
    @JSONPropertyIgnore
    public boolean hasCards() {
        return !cards.isEmpty();
    }

    // MODIFIES: this
    // EFFECTS: sets name to the given value
    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        String description = MessageFormat.format("Deck name changed from {0} to {1}", oldName, name);
        EventLog.getInstance().logEvent(new Event(description));
    }

    // MODIFIES: this
    // EFFECTS: adds card to cards
    public void addCard(Card card) {
        cards.add(card);
        String description = MessageFormat.format(
                "Card with question {0} added to deck with name {1}", card.getQuestion(), name);
        EventLog.getInstance().logEvent(new Event(description));
    }

    // MODIFIES: this
    // EFFECTS: removes card from cards
    public void removeCard(Card card) {
        cards.remove(card);
        String description = MessageFormat.format(
                "Card with question {0} removed from deck with name {1}", card.getQuestion(), name);
        EventLog.getInstance().logEvent(new Event(description));
    }

}
