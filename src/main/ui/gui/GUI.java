package ui.gui;

import exceptions.DeckHasNoCardsException;
import exceptions.NoDecksException;
import model.Deck;
import ui.App;
import ui.gui.enums.DialogMessage;
import ui.gui.enums.PhotoPath;
import ui.gui.views.*;

import javax.swing.*;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// represents the Branki application GUI UI
public class GUI extends App {

    private static MainWindow mainWindow;
    private static DeckSelector studyDeckSelector;
    private static DeckSelector editDeckSelector;
    private static StudyDeckWindow studyDeckWindow;
    private static EditDeckWindow editDeckWindow;

    // EFFECTS: initializes decks and mainWindow
    public GUI() {
        decks = new ArrayList<>();
        mainWindow = new MainWindow();
    }

    public static void showCreateDeckWindow() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: if there is a deck shows editDeckSelector. If there is not a deck,
    //          displays no decks warning.
    public static void showEditDeckSelector() {
        try {
            editDeckSelector = new EditDeckSelector(decks);
            editDeckSelector.setVisible(true);
        } catch (NoDecksException ex) {
            showNoDecksWarning(ex);
        }
    }

    // EFFECTS: shows no decks warning
    private static void showNoDecksWarning(NoDecksException ex) {
        JOptionPane.showMessageDialog(mainWindow,
                ex.getMessage(), "No Decks Warning", JOptionPane.WARNING_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: disposes editDeckSelector and shows editDeckWindow
    public static void showEditDeckWindow(Deck deck) {
        editDeckWindow = new EditDeckWindow(deck);
        editDeckWindow.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: attempts to load data and notifies user of result with a popup
    public static void loadDecksAndNotify() {
        boolean isDataLoaded = loadData();
        showDataDialog("load", isDataLoaded);
    }

    // EFFECTS: attempts to load decks data from file in JSON format. Returns true if successful
    //          and false otherwise.
    private static boolean loadData() {
        try {
            decks = jsonReader.read();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: attempts to save data and notifies user of result with a popup
    public static void saveDecksAndNotify() {
        boolean isDataSaved = saveData();
        showDataDialog("save", isDataSaved);
    }

    // MODIFIES: this
    // EFFECTS: attempts to write decks data to file in JSON format. Returns true if successful
    //          and false otherwise.
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
    public static void showDataDialog(String operation, boolean wasSuccessful) {
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
    // EFFECTS: if there is a deck with cards, instantiates and shows studyDeckSelector.
    //          If there is no such deck, displays no decks with cards warning.
    public static void showStudyDeckSelector() {
        try {
            List<Deck> decksWithCards = decks.stream().filter(Deck::hasCards).collect(Collectors.toList());
            studyDeckSelector = new StudyDeckSelector(decksWithCards);
            studyDeckSelector.setVisible(true);
        } catch (NoDecksException ex) {
            showNoDecksWithCardsWarning(ex);
        }
    }

    // EFFECTS: shows no decks with cards warning
    private static void showNoDecksWithCardsWarning(NoDecksException ex) {
        JOptionPane.showMessageDialog(mainWindow,
                ex.getMessage(), "No Decks with Cards Warning", JOptionPane.WARNING_MESSAGE);
    }

    // MODIFIES: deck
    // EFFECTS: disposes studyDeckSelector and instantiates and shows studyDeckWindow.
    //          Shows deck has no cards error if DeckHasNoCardsException caught.
    public static void showStudyDeckWindow(Deck deck) {
        studyDeckSelector.dispose();
        try {
            studyDeckWindow = new StudyDeckWindow(deck);
            studyDeckWindow.setVisible(true);
        } catch (DeckHasNoCardsException ex) {
            showStudyDeckHasNoCardsError(ex);
        }
    }

    // EFFECTS: shows study deck has no cards error
    private static void showStudyDeckHasNoCardsError(DeckHasNoCardsException ex) {
        JOptionPane.showMessageDialog(mainWindow,
                ex.getMessage(), "No Decks with Cards Warning", JOptionPane.ERROR_MESSAGE);
    }

    // EFFECTS: shows study session completed message
    public static void showStudySessionCompleteMessage() {
        studyDeckWindow.dispose();
        JOptionPane.showMessageDialog(mainWindow,
                DialogMessage.STUDY_SESSION_COMPLETE.getMessage(),
                "Study Session Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: shows cat loaf photo in popup window
    public static void showCatLoaf() {
        String path = PhotoPath.CAT_LOAF.getPath();
        new PhotoPopupWindow(path);
    }

    // EFFECTS: shows Toby photo in popup window
    public static void showToby() {
        String path = PhotoPath.TOBY.getPath();
        new PhotoPopupWindow(path);
    }

    // MODIFIES: this
    // EFFECTS: updates name of given deck to given name and shows deck updated message
    public static void updateDeck(String name, Deck deck) {
        editDeckWindow.dispose();
        deck.setName(name);
        JOptionPane.showMessageDialog(mainWindow,
                DialogMessage.DECK_UPDATED.getMessage(), "Deck Updated", JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: removes given deck from decks and shows deck deleted message
    public static void deleteDeck(Deck deck) {
        editDeckWindow.dispose();
        decks.remove(deck);
        JOptionPane.showMessageDialog(mainWindow,
                DialogMessage.DECK_DELETED.getMessage(), "Deck Deleted", JOptionPane.INFORMATION_MESSAGE);
    }
}