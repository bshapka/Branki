package ui.gui;

import exceptions.DeckHasNoCardsException;
import exceptions.NoDecksException;
import exceptions.NoDecksWithCardsException;
import model.Card;
import model.Deck;
import ui.App;
import ui.gui.enums.DialogMessage;
import ui.gui.enums.PhotoPath;
import ui.gui.views.selectors.*;
import ui.gui.views.selectors.card.EditCardCardSelector;
import ui.gui.views.selectors.deck.CreateCardDeckSelector;
import ui.gui.views.selectors.deck.EditCardDeckSelector;
import ui.gui.views.selectors.deck.EditDeckSelector;
import ui.gui.views.selectors.deck.StudyDeckSelector;
import ui.gui.views.windows.card.CreateCardWindow;
import ui.gui.views.windows.card.EditCardWindow;
import ui.gui.views.windows.deck.CreateDeckWindow;
import ui.gui.views.windows.deck.EditDeckWindow;
import ui.gui.views.windows.main.MainWindow;
import ui.gui.views.windows.photo.PhotoPopupWindow;
import ui.gui.views.windows.study.StudyDeckWindow;

import javax.swing.*;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;

// represents the Branki application GUI UI
public class GUI extends App {

    private static MainWindow mainWindow;
    private static CreateDeckWindow createDeckWindow;
    private static Selector studyDeckSelector;
    private static Selector editDeckSelector;
    private static Selector createCardDeckSelector;
    private static Selector editCardDeckSelector;
    private static Selector editCardCardSelector;
    private static CreateCardWindow createCardWindow;
    private static EditCardWindow editCardWindow;
    private static StudyDeckWindow studyDeckWindow;
    private static EditDeckWindow editDeckWindow;
    private static boolean isUnsaved;

    // EFFECTS: initializes decks to empty list, isUnsaved to false, and instantiates mainWindow
    public GUI() {
        decks = new ArrayList<>();
        isUnsaved = false;
        mainWindow = new MainWindow();
    }

    // MODIFIES: this
    // EFFECTS: sets isUnsaved to true
    public static void setUnsaved() {
        isUnsaved = true;
    }

    // MODIFIES: this
    // EFFECTS: instantiates and shows createDeckWindow
    public static void showCreateDeckWindow() {
        createDeckWindow = new CreateDeckWindow();
        createDeckWindow.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: disposes createDeckWindow, creates deck with given name, adds deck to decks,
    //          sets isUnsaved to true, and shows deck created message
    public static void createDeck(String name) {
        createDeckWindow.dispose();
        Deck deck = new Deck(name);
        decks.add(deck);
        isUnsaved = true;
        JOptionPane.showMessageDialog(mainWindow,
                DialogMessage.DECK_CREATED.getMessage(), "Deck Created", JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: if NoDecksException is thrown displays error, otherwise instantiates and shows editDeckSelector
    public static void showEditDeckSelector() {
        try {
            editDeckSelector = new EditDeckSelector(decks);
            editDeckSelector.setVisible(true);
        } catch (NoDecksException ex) {
            showNoDecksError(ex);
        }
    }

    // EFFECTS: shows no decks error
    private static void showNoDecksError(NoDecksException ex) {
        JOptionPane.showMessageDialog(mainWindow, ex.getMessage(), "No Decks Error", JOptionPane.ERROR_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: disposes editDeckSelector and instantiates and shows editDeckWindow
    public static void showEditDeckWindow(Deck deck) {
        editDeckSelector.dispose();
        editDeckWindow = new EditDeckWindow(deck);
        editDeckWindow.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: disposes editDeckWindow, updates name of given deck to given name, sets isUnsaved to true,
    //          and shows deck updated message
    public static void updateDeck(String name, Deck deck) {
        editDeckWindow.dispose();
        deck.setName(name);
        isUnsaved = true;
        JOptionPane.showMessageDialog(mainWindow,
                DialogMessage.DECK_UPDATED.getMessage(), "Deck Updated", JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: disposes editDeckWindow, removes given deck from decks, sets isUnsaved to true,
    //          and shows deck deleted message
    public static void deleteDeck(Deck deck) {
        editDeckWindow.dispose();
        decks.remove(deck);
        isUnsaved = true;
        JOptionPane.showMessageDialog(mainWindow,
                DialogMessage.DECK_DELETED.getMessage(), "Deck Deleted", JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: if NoDecksException is thrown displays error, else instantiates and shows createCardDeckSelector.
    public static void showCreateCardDeckSelector() {
        try {
            createCardDeckSelector = new CreateCardDeckSelector(decks);
            createCardDeckSelector.setVisible(true);
        } catch (NoDecksException ex) {
            showNoDecksError(ex);
        }
    }

    // MODIFIES: this
    // EFFECTS: disposes createCardDeckSelector and instantiates and shows createCardWindow
    public static void showCreateCardWindow(Deck deck) {
        createCardDeckSelector.dispose();
        createCardWindow = new CreateCardWindow(deck);
        createCardWindow.setVisible(true);
    }

    // MODIFIES: deck
    // EFFECTS: disposes createCardWindow, creates card with given question and answer, adds it to the given deck,
    //          sets isUnsaved to true, and shows card created message
    public static void createCard(Deck deck, String question, String answer) {
        createCardWindow.dispose();
        Card card = new Card(question, answer);
        deck.addCard(card);
        isUnsaved = true;
        JOptionPane.showMessageDialog(mainWindow,
                DialogMessage.CARD_CREATED.getMessage(), "Card Created", JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: if NoDecksException or NoDecksWithCardsException is thrown displays error,
    //          else instantiates and shows editCardDeckSelector.
    public static void showEditCardDeckSelector() {
        try {
            editCardDeckSelector = new EditCardDeckSelector(decks);
            editCardDeckSelector.setVisible(true);
        } catch (NoDecksException ex) {
            showNoDecksError(ex);
        } catch (NoDecksWithCardsException ex) {
            showNoDecksWithCardsError(ex);
        }
    }

    // MODIFIES: this
    // EFFECTS: disposes editCardDeckSelector and instantiates and shows editCardCardSelector
    public static void showEditCardCardSelector(Deck deck) {
        editCardDeckSelector.dispose();
        editCardCardSelector = new EditCardCardSelector(deck);
        editCardCardSelector.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: disposes editCardCardSelector and instantiates and shows editCardWindow
    public static void showEditCardWindow(Deck deck, Card card) {
        editCardCardSelector.dispose();
        editCardWindow = new EditCardWindow(deck, card);
        editCardWindow.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: disposes editCardWindow, updates question and answer of given card to given question and answer,
    //          sets isUnsaved to true, and shows card updated message
    public static void updateCard(String question, String answer, Card card) {
        editCardWindow.dispose();
        card.setQuestion(question);
        card.setAnswer(answer);
        isUnsaved = true;
        JOptionPane.showMessageDialog(mainWindow,
                DialogMessage.CARD_UPDATED.getMessage(), "Card Updated", JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: deck
    // EFFECTS: disposes editCardWindow, deletes given card from given deck, sets isUnsaved to true,
    //          and shows card deleted message
    public static void deleteCard(Deck deck, Card card) {
        editCardWindow.dispose();
        deck.removeCard(card);
        isUnsaved = true;
        JOptionPane.showMessageDialog(mainWindow,
                DialogMessage.CARD_DELETED.getMessage(), "Card Deleted", JOptionPane.INFORMATION_MESSAGE);
    }

    // MODIFIES: this
    // EFFECTS: attempts to load data and notifies user of result with a popup
    public static void loadDecksAndNotify() {
        boolean isDataLoaded = loadData();
        showDataDialog("load", isDataLoaded);
    }

    // EFFECTS: attempts to load decks data from file in JSON format. Sets isUnsaved to false and returns true
    //          if successful, otherwise just returns false.
    private static boolean loadData() {
        try {
            decks = jsonReader.read();
            isUnsaved = false;
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
    // EFFECTS: attempts to write decks data to file in JSON format. Sets isUnsaved to false and returns true
    //          if successful, otherwise just returns false.
    private static boolean saveData() {
        try {
            jsonWriter.write(decks);
            isUnsaved = false;
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
    // EFFECTS: if NoDecksException or NoDecksWithCardsException is thrown displays error,
    //          else instantiates and shows studyDeckSelector.
    public static void showStudyDeckSelector() {
        try {
            studyDeckSelector = new StudyDeckSelector(decks);
            studyDeckSelector.setVisible(true);
        } catch (NoDecksException ex) {
            showNoDecksError(ex);
        } catch (NoDecksWithCardsException ex) {
            showNoDecksWithCardsError(ex);
        }
    }

    // EFFECTS: shows no decks with cards error
    private static void showNoDecksWithCardsError(NoDecksWithCardsException ex) {
        JOptionPane.showMessageDialog(mainWindow,
                ex.getMessage(), "No Decks with Cards Error", JOptionPane.ERROR_MESSAGE);
    }

    // MODIFIES: deck
    // EFFECTS: disposes studyDeckSelector. If DeckHasNoCardsException is thrown displays error,
    //          else instantiates and shows editCardDeckSelector.
    public static void showStudyDeckWindow(Deck deck) {
        studyDeckSelector.dispose();
        try {
            studyDeckWindow = new StudyDeckWindow(deck);
            studyDeckWindow.setVisible(true);
        } catch (DeckHasNoCardsException ex) {
            showDeckHasNoCardsError(ex);
        }
    }

    // EFFECTS: shows study deck has no cards error
    private static void showDeckHasNoCardsError(DeckHasNoCardsException ex) {
        JOptionPane.showMessageDialog(mainWindow,
                ex.getMessage(), "No Decks with Cards Error", JOptionPane.ERROR_MESSAGE);
    }

    // EFFECTS: disposes studyDeckWindow and shows study session completed message
    public static void showStudySessionCompleteMessage() {
        studyDeckWindow.dispose();
        JOptionPane.showMessageDialog(mainWindow,
                DialogMessage.STUDY_SESSION_COMPLETE.getMessage(),
                "Study Session Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    // EFFECTS: shows cat loaf photo in popup window
    public static void showCatLoaf() {
        String path = PhotoPath.CAT_LOAF.getPath();
        new PhotoPopupWindow("Cat Loaf!", path);
    }

    // EFFECTS: shows Toby photo in popup window
    public static void showToby() {
        String path = PhotoPath.TOBY.getPath();
        new PhotoPopupWindow("Toby!", path);
    }

    // MODIFIES: this
    // EFFECTS: if there is unsaved data, shows an option pane with Save & Quit and Quit Only options.
    //          The former option saves the data before quitting, while the latter just quits.
    public static void quitApp() {
        if (isUnsaved) {
            String[] options = { "Save & Quit", "Quit Only"};
            int result = JOptionPane.showOptionDialog(mainWindow, DialogMessage.CLOSE_APP.getMessage(), "Quit App",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if (result == 0) {
                saveDecksAndNotify();
            }
        }
        System.exit(0);
    }

}