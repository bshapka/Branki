package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    Deck deck;
    static final String DEFAULT_NAME = "Deck";

    @BeforeEach
    void setup() {
        deck = new Deck(DEFAULT_NAME);
    }

    @Test
    void testGetName() {
        assertEquals(DEFAULT_NAME, deck.getName());
    }

    @Test
    void testGetCardsEmpty() {
        assertTrue(deck.getCards().isEmpty());
    }

    @Test
    void testGetCardsNonEmpty() {
        Card c1 = new Card("Q", "A");
        Card c2 = new Card("Q", "A");

        deck.addCard(c1);
        List<Card> cards = deck.getCards();
        assertEquals(1, cards.size());
        assertEquals(c1, cards.get(0));

        deck.addCard(c2);
        cards = deck.getCards();
        assertEquals(2, cards.size());
        assertEquals(c1, cards.get(0));
        assertEquals(c2, cards.get(1));
    }

    @Test
    void testGetDifficultCardsEmpty() {
        assertTrue(deck.getDifficultCards().isEmpty());
    }

    @Test
    void testGetDifficultCardsNonEmptyNoDifficultCards() {
        Card c = new Card("Q", "A");
        c.addResult(new Result(Result.MIN_DIFFICULTY));
        deck.addCard(c);
        assertTrue(deck.getDifficultCards().isEmpty());
    }

    @Test
    void testGetDifficultCardsNonEmptyHasDifficultCard() {
        Card c = new Card("Q", "A");
        c.addResult(new Result(Result.MAX_DIFFICULTY));
        deck.addCard(c);
        assertEquals(1, deck.getDifficultCards().size());
    }

    @Test
    void testGetCardIndexInBounds() {
        Card c = new Card("Q", "A");
        deck.addCard(c);
        Card expectedCard = deck.getCard(0);
        assertEquals(expectedCard, c);
    }

    @Test
    void testGetCardIndexNotInBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> { deck.getCard(0); });
    }

    @Test
    void testGetSize() {
        Card c1 = new Card("Q", "A");
        Card c2 = new Card("Q", "A");

        deck.addCard(c1);
        assertEquals(1, deck.getSize());

        deck.addCard(c2);
        assertEquals(2, deck.getSize());
    }

    @Test
    void testHasCardsEmpty() {
        assertFalse(deck.hasCards());
    }

    @Test
    void testHasCardsNonEmpty() {
        deck.addCard(new Card("Q", "A"));
        assertTrue(deck.hasCards());
    }

    @Test
    void removeCard() {
        Card c1 = new Card("Q1", "A1");
        Card c2 = new Card("Q2", "A2");
        deck.addCard(c1);
        deck.addCard(c2);

        deck.removeCard(c2);
        assertEquals(1, deck.getSize());
        assertEquals(c1, deck.getCard(0));

        deck.removeCard(c1);
        assertFalse(deck.hasCards());
    }

}
