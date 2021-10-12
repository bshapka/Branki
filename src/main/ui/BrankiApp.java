package ui;

import model.Deck;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

// represents the Branki application console UI
public class BrankiApp {

    // the first id for listing objects in tabular form
    private static final int ID_START = 1;

    private List<Deck> decks;

    // MODIFIES: this
    // EFFECTS: initializes decks to empty list and starts UI
    public BrankiApp() {
        decks = new ArrayList<>();
        printWelcomeMessage();
        printMainMenuAndProcessSelection();
    }

    // EFFECTS: prints welcome message
    private void printWelcomeMessage() {
        int hourOfDay = LocalTime.now().getHour();
        try {
            String timeOfDay = getTimeOfDay(hourOfDay);
            String welcomeMessage = MessageFormat.format("Good {0} and welcome to Branki! ", timeOfDay);
            System.out.print(welcomeMessage);
        } catch (IllegalArgumentException ex) {
            System.out.print("Welcome to Branki! ");
        }
    }

    // EFFECTS: returns the time of day based on the given hour. If given hour is not valid
    //          (i.e. is not in [0, 23]) throws exception
    private String getTimeOfDay(int hour) throws IllegalArgumentException {
        if (hour < 0 || hour > 23) {
            String errorMessage = MessageFormat.format("{0} is not a valid hour", hour);
            throw(new IllegalArgumentException(errorMessage));
        }
        String timeOfDay = hour < 12 ? "morning" : (hour < 18 ? "afternon" : "evening");
        return timeOfDay;
    }

    // MODIFIES: this
    // EFFECTS: prints main menu, gets user selection, and processes selection
    private void printMainMenuAndProcessSelection() {
        printMainMenu();
        String selection = getStringFromUser();
        processMainMenuSelection(selection);
    }

    // EFFECTS: prints main menu
    private void printMainMenu() {
        System.out.println("Please select one of the following options:");
        System.out.println("Enter 'c' for deck configuration.");
        System.out.println("Enter 's' to study.");
        System.out.println("Enter anything else to quit.");
    }

    // EFFECTS: gets a string from the user via the console, trims the string, and
    //          then returns the string
    private String getStringFromUser() {
        Scanner scn = new Scanner(System.in);
        String userInput = scn.nextLine().trim();
        return userInput;
    }

    // EFFECTS: processes user's main menu selection
    private void processMainMenuSelection(String selection) {
        switch (selection.toLowerCase()) {
            case "c":
                routeDeckConfigMenu();
                break;
            case "s":
                routeStudyMenu();
                break;
            default:
                routeQuit();
        }
    }

    // MODIFIES: this
    // EFFECTS: routes user to deck configuration menu if decks is not empty.
    //          If decks is empty, routes user to empty decks handler.
    private void routeDeckConfigMenu() {
        if (decks.isEmpty()) {
            handleNoDecks();
            System.out.println("Returning to deck configuration menu.\n");
        }
        printDeckConfigMenuAndProcessSelection();
    }

    // MODIFIES: this
    // EFFECTS: notifies user that there are no decks, then initiates creation
    //          of a deck
    private void handleNoDecks() {
        System.out.print("You don't have any decks! ");
        System.out.println("There isn't much to do without a deck, so let's create one.");
        createDeckAndNotify();
    }

    // MODIFIES: this
    // EFFECTS: performs deck creation, informing user of result (success or cancelled)
    private void createDeckAndNotify() {
        boolean deckCreated = createDeck();
        if (deckCreated) {
            System.out.println("The deck has been created. ");
        } else {
            System.out.print("Deck creation cancelled. ");
        }
    }

    // MODIFIES: this
    // EFFECTS: gets a name for new deck from the user. If name is not blank, adds deck with
    //          given name to decks and returns true. If name is blank, returns false.
    private boolean createDeck() {
        System.out.println("Please enter a name for the new deck, or type enter to cancel:");
        String deckName = getStringFromUser();
        if (deckName.isEmpty()) {
            return false;
        }
        Deck deck = new Deck(deckName);
        decks.add(deck);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: prints deck configuration menu, gets user selection, and processes selection
    private void printDeckConfigMenuAndProcessSelection() {
        printDeckConfigMenu();
        String selection = getStringFromUser();
        processDeckConfigMenuSelection(selection);
    }

    // EFFECTS: prints deck configuration menu
    private void printDeckConfigMenu() {
        System.out.println("Please select one of the following options:");
        System.out.println("Enter 'v' to view the list of decks.");
        System.out.println("Enter 'c' to create a deck.");
        System.out.println("Enter 'm' to modify a deck's name.");
        System.out.println("Enter 'd' to delete a deck.");
        System.out.println("Enter 'o' to configure the cards in a deck");
        System.out.println("Enter anything else to return to the main menu.");
    }

    // EFFECTS: processes deck configuration menu user selection
    private void processDeckConfigMenuSelection(String selection) {
        switch (selection.toLowerCase()) {
            case "v":
                viewDecks();
                break;
            case "c":
                createDeckAndNotify();
                break;
            case "m":
                routeModifyDeck();
                break;
            case "d":
                routeDeleteDeck();
                break;
            case "o":
                routeCardConfigMenu();
                break;
            default:
                printMainMenuAndProcessSelection();
                return;
        }
        System.out.println("Returning to deck configuration menu.\n");
        printDeckConfigMenuAndProcessSelection();
    }

    // EFFECTS: prints decks if decks is not empty. If decks is empty, routes user
    //          to empty decks handler.
    private void viewDecks() {
        if (decks.isEmpty()) {
            handleNoDecks();
        } else {
            printDecks(decks);
            System.out.println("Please enter anything to continue:");
            getStringFromUser();
        }
    }

    // EFFECTS: prints out all of the decks in csv format
    private void printDecks(List<Deck> decks) {
        System.out.println("Here are all of the decks:\n");
        System.out.println("id, name, number of cards, is difficult");
        System.out.println("---------------------------------------");
        IntStream.range(0, decks.size()).forEach(i -> {
            int deckId = i + ID_START;
            Deck deck = decks.get(i);
            String line = MessageFormat.format("{0}, {1}, {2}, {3}",
                    deckId, deck.getName(), deck.getSize(), deck.hasDifficultCards() ? "Yes" : "No");
            System.out.println(line);
        });
        System.out.println();
    }

    // MODIFIES: this
    // EFFECTS: routes user to deck modification if decks is not empty. If decks is empty,
    //          routes user to empty decks handler.
    private void routeModifyDeck() {
        if (decks.isEmpty()) {
            handleNoDecks();
        } else {
            boolean deckModified = modifyDeck();
            if (deckModified) {
                System.out.print("The deck has been renamed. ");
            } else {
                System.out.print("Deck renaming cancelled. ");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: gets a user selected deck from decks and new name for deck from user.
    //          If new name is not blank, changes name of deck to the new name and returns true.
    //          If name is blank, returns false.
    private boolean modifyDeck() {
        Deck deck = getSelectedDeck(decks);
        System.out.println("Please enter a new name for the deck, or type enter to cancel:");
        String newName = getStringFromUser();
        if (newName.isEmpty()) {
            return false;
        }
        deck.setName(newName);
        return true;
    }

    // EFFECTS: acquires deck from decks using id supplied by user. Re-prompts if invalid
    //          index supplied.
    private Deck getSelectedDeck(List<Deck> decks) {
        System.out.println("Please select a deck using its id.");
        Deck deck = null;
        while (deck == null) {
            printDecks(decks);
            try {
                int deckIndex = getIntFromUser() - ID_START;
                deck = decks.get(deckIndex);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("The id you provided is invalid. Please provide a valid id.");
            }
        }
        return deck;
    }

    // EFFECTS: gets an integer from the user via the console and returns it.
    //          Re-prompts if user enters a non-integer.
    private int getIntFromUser() {
        Integer input = null;
        while (input == null) {
            try {
                Scanner scn = new Scanner(System.in);
                input = scn.nextInt();
            } catch (Exception ex) {
                System.out.println("Please enter an integer.");
            }
        }
        return input;
    }

    // MODIFIES: this
    // EFFECTS: routes user to deck deletion if decks is not empty. If decks is empty, routes
    //          user to empty decks handler.
    private void routeDeleteDeck() {
        if (decks.isEmpty()) {
            handleNoDecks();
        } else {
            boolean deckDeleted = deleteDeck();
            if (deckDeleted) {
                System.out.print("The deck has been deleted. ");
            } else {
                System.out.print("Deck deletion cancelled. ");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: gets a user selected deck from decks and a confirmation from the user.
    //          If confirmation is 'y', deletes the deck and returns true. If confirmation
    //          is anything else, returns false.
    private boolean deleteDeck() {
        Deck deck = getSelectedDeck(decks);
        System.out.println("Please enter 'y' to delete the deck, or anything else to cancel:");
        String response = getStringFromUser();
        if (!response.toLowerCase().equals("y")) {
            return false;
        }
        decks.remove(deck);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: routes user to deck selection if decks is not empty. If selected
    //          deck has cards, then routes user to card configuration menu. If
    //          decks is empty, routes user to empty decks handler.
    private void routeCardConfigMenu() {
        if (decks.isEmpty()) {
            handleNoDecks();
        } else {
            Deck deck = getSelectedDeck(decks);
            if (!deck.hasCards()) {
                handleNoCards(deck);
                System.out.println("Returning to card configuration menu.\n");
            }
            printCardConfigMenuAndProcessSelection(deck);
        }
    }

    // MODIFIES: deck
    // EFFECTS: notifies user there are no cards in deck, then initiates creation of a card
    private void handleNoCards(Deck deck) {
        System.out.print("You don't have any cards in this deck! ");
        System.out.println("There isn't much to do with a cardless deck, so let's create a card.");
        createCardAndNotify(deck);
    }

    private void createCardAndNotify(Deck deck) {
        // stub
    }

    private void printCardConfigMenuAndProcessSelection(Deck deck) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: routes user to study menu if decks is not empty. If decks is
    //          empty, routes user to deck creation menu.
    private void routeStudyMenu() {
        // stub
    }

    // EFFECTS: quits the application
    private void routeQuit() {
        System.out.println("Bye for now!");
        System.exit(0);
    }

}
