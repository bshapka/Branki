package ui;

import model.Deck;

import java.util.ArrayList;
import java.util.List;

// represents the Branki application console UI
public class BrankiApp {

    private List<Deck> decks;

    // MODIFIES: this
    // EFFECTS: initializes decks to empty list and starts UI
    public BrankiApp() {
        decks = new ArrayList<>();
        printWelcomeMessage();
    }

    // EFFECTS: prints welcome message
    private void printWelcomeMessage() {
        System.out.println("Welcome!");
    }

}
