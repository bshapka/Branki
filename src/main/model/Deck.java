package model;

import java.util.ArrayList;
import java.util.List;

// represents a deck with a name and a list of cards
public class Deck {

    private String name;
    private List<Card> cards;

    // EFFECTS: constructs a Deck. Sets name to given value, and cards to an empty list.
    public Deck(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns cards
    public List<Card> getCards() {
        return cards;
    }

    // EFFECTS: returns difficult cards in cards (as per card.isDifficult())
    public List<Card> getDifficultCards() {
        List<Card> difficultCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.isDifficult()) {
                difficultCards.add(card);
            }
        }
        return difficultCards;
    }

    // EFFECTS: returns card at given index in cards if 0 <= index < cards.size(), else throws
    //          IndexOutOfBoundsException
    public Card getCard(int index) throws IndexOutOfBoundsException {
        return cards.get(index);
    }

    // EFFECTS: returns size of cards
    public int getSize() {
        return cards.size();
    }

    // EFFECTS: returns true if cards is not empty, else returns false
    public boolean hasCards() {
        return !cards.isEmpty();
    }

    // MODIFIES: this
    // EFFECTS: adds card to cards
    public void addCard(Card card) {
        cards.add(card);
    }

    // MODIFIES: this
    // EFFECTS: removes card from cards
    public void removeCard(Card card) {
        cards.remove(card);
    }

}
