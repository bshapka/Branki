package ui;

import model.Deck;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// represents the Branki application console UI
public class BrankiApp {

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
        printMainMenuAndProcessSelection();
    }

    // MODIFIES: this
    // EFFECTS: notifies user that there are no decks, then initiates creation
    //          of a deck
    private void handleNoDecks() {
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
