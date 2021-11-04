package ui;

import model.Deck;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.util.List;

// abstract class representing aspects common to both console and GUI apps
public abstract class App {

    // the name of the application
    protected static final String APP_NAME = "Branki";
    // the file path for the json file used to save state
    protected static final String JSON_FILE_PATH = "./data/decks.json";
    // the jsonReader used to save application data to a file
    protected static final JsonReader jsonReader = new JsonReader(JSON_FILE_PATH);
    // the jsonReader used to read saved application data from a file
    protected static final JsonWriter jsonWriter = new JsonWriter(JSON_FILE_PATH);

    protected static List<Deck> decks;

    // EFFECTS: returns welcome message
    protected static String getWelcomeMessage() {
        String welcomeMessage;
        int hourOfDay = LocalTime.now().getHour();
        try {
            String timeOfDay = getTimeOfDay(hourOfDay);
            welcomeMessage = MessageFormat.format("Good {0} and welcome to {1}!", timeOfDay, APP_NAME);
        } catch (IllegalArgumentException ex) {
            welcomeMessage = MessageFormat.format("Welcome to {0}!", APP_NAME);
        }
        return welcomeMessage;
    }

    // EFFECTS: returns the time of day based on the given hour. If given hour is not valid
    //          (i.e. is not in [0, 23]), throws exception
    private static String getTimeOfDay(int hour) throws IllegalArgumentException {
        if (hour < 0 || hour > 23) {
            String errorMessage = MessageFormat.format("{0} is not a valid hour", hour);
            throw (new IllegalArgumentException(errorMessage));
        }
        String timeOfDay;
        timeOfDay = hour < 12 ? "morning" : (hour < 18 ? "afternon" : "evening");
        return timeOfDay;
    }

}
