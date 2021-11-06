package ui;

import model.Deck;

import javax.swing.*;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;

// represents the Branki application GUI UI
public class GUI extends App {

    private static MainWindow mainWindow;
    private static DeckSelector deckSelector;

    // EFFECTS: initializes decks and mainWindow
    GUI() {
        decks = new ArrayList<>();
        mainWindow = new MainWindow();
    }

    // MODIFIES: this
    // EFFECTS: attempts to load data and notifies user of result by activating a popup with the result
    static void loadDecksAndNotify() {
        boolean isDataLoaded = loadData();
        showDataDialog("load", isDataLoaded);
    }

    // EFFECTS: attempts to load decks data from file in JSON format. Returns true if successful and false otherwise.
    private static boolean loadData() {
        try {
            decks = jsonReader.read();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: attempts to save data and notifies user of result by activating a popup with the result
    static void saveDecksAndNotify() {
        boolean isDataSaved = saveData();
        showDataDialog("save", isDataSaved);
    }

    // MODIFIES: this
    // EFFECTS: attempts to write decks data to file in JSON format. Returns true if successful and false otherwise.
    private static boolean saveData() {
        try {
            jsonWriter.write(decks);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    // EFFECTS: displays dialog informing user of success or failure of data save or load
    //          operation (depending on arguments passed).
    static void showDataDialog(String operation, boolean wasSuccessful) {
        String message;
        String title;
        if (wasSuccessful) {
            String messageAction = operation.equals("save") ? "saved" : "loaded";
            String titleAction = operation.equals("save") ? "Saved" : "Loaded";
            message = MessageFormat.format("The data was successfully {0}.", messageAction);
            title = MessageFormat.format("Data {0}", titleAction);
        } else {
            String messageAction = operation.equals("save") ? "saving" : "loading";
            String titleAction = operation.equals("save") ? "Save" : "Load";
            message = MessageFormat.format("An error occurred {0} the data.", messageAction);
            title = MessageFormat.format("Data {0} Error", titleAction);
        }
        int messageType = wasSuccessful ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
        JOptionPane.showMessageDialog(mainWindow, message, title, messageType);
    }

    // MODIFIES: this
    // EFFECTS: if there is a deck, shows deck selector. If there is no deck, displays no decks message.
    static void showDeckSelector() {
        if (decks.isEmpty()) {
            JOptionPane.showMessageDialog(mainWindow,
                    "You don't have any decks. Please create a deck to study.",
                    "No Decks Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        deckSelector = new DeckSelector(decks);
        deckSelector.setVisible(true);
    }

    // MODIFIES: deck
    // EFFECTS: disposes deckSelector and starts study session with deck in decks corresponding to given deckIndex
    static void startStudySession(int deckIndex) {
        deckSelector.dispose();
        Deck deck = decks.get(deckIndex);
    }

    // EFFECTS: displays cat loaf photo in popup window
    static void showCatLoaf() {
        String path = PhotoPath.CAT_LOAF.getPath();
        new PhotoPopup(path);
    }

    // EFFECTS: displays Toby photo in popup window
    static void showToby() {
        String path = PhotoPath.TOBY.getPath();
        new PhotoPopup(path);
    }

}