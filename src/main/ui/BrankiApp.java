package ui;

import model.Deck;

import java.text.MessageFormat;
import java.time.LocalTime;
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

    // EFFECTS: processes user's main menu selection
    private void processMainMenuSelection(String selection) {
        // stub
    }

    // EFFECTS: gets a string from the user via the console, trims the string, and
    //          then returns the string
    private String getStringFromUser() {
        return "";
    }

    // EFFECTS: prints main menu
    private void printMainMenu() {
        // stub
    }

}
