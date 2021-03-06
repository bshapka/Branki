package ui.console;

import exceptions.InvalidResultDifficultyException;
import model.Card;
import model.Deck;
import model.Result;
import org.json.JSONException;
import ui.App;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.IntStream;

// represents the Branki application console UI
public class Console extends App {

    // the first id for listing objects in tabular form
    private static final int ID_START = 1;

    private boolean isUnsaved;

    // MODIFIES: this
    // EFFECTS: initializes decks to empty list, isUnsaved to false, and starts UI
    Console() {
        decks = new ArrayList<>();
        isUnsaved = false;
        printWelcomeMessage();
        printMainMenuAndProcessSelection();
    }

    // EFFECTS: prints welcome message
    private void printWelcomeMessage() {
        System.out.print(getWelcomeMessage());
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
        System.out.println("Enter 'w' to write (save) data.");
        System.out.println("Enter 'l' to load data.");
        System.out.println("Enter anything else to quit.");
    }

    // EFFECTS: gets a string from the user via the console, trims the string, and
    //          then returns the string
    private String getStringFromUser() {
        Scanner scn = new Scanner(System.in);
        return scn.nextLine().trim();
    }

    // MODIFIES: this
    // EFFECTS: processes user's main menu selection
    private void processMainMenuSelection(String selection) {
        switch (selection.toLowerCase()) {
            case "c":
                routeDeckConfigMenu();
                break;
            case "s":
                routeStudyMenu();
                break;
            case "w":
                routeSaveData();
                break;
            case "l":
                routeLoadData();
                break;
            default:
                routeQuit();
        }
    }

    // MODIFIES: this
    // EFFECTS: routes user to deck configuration menu if decks is not empty.
    //          If decks is empty, routes user to empty decks handler first.
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
    //          given name to decks, sets isUnsaved to true, and returns true.
    //          If name is blank, returns false.
    private boolean createDeck() {
        System.out.println("Please enter a name for the new deck, or type enter to cancel:");
        String deckName = getStringFromUser();
        if (deckName.isEmpty()) {
            return false;
        }
        Deck deck = new Deck(deckName);
        decks.add(deck);
        isUnsaved = true;
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

    // MODIFIES: this
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

    // EFFECTS: prints out all the decks in csv format
    private void printDecks(List<Deck> decks) {
        if (decks.isEmpty()) {
            System.out.println("There are no decks to print!");
            return;
        }
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
    //          If new name is not blank, changes name of deck to the new name, sets isUnsaved to true,
    //          and returns true. If name is blank, returns false.
    private boolean modifyDeck() {
        Deck deck = getSelectedDeck(decks);
        System.out.println("Please enter a new name for the deck, or type enter to cancel:");
        String newName = getStringFromUser();
        if (newName.isEmpty()) {
            return false;
        }
        deck.setName(newName);
        isUnsaved = true;
        return true;
    }

    // REQUIRES: decks is not empty
    // EFFECTS: acquires deck from decks using id supplied by user. Re-prompts if given an
    //          id that results in invalid index in decks.
    private Deck getSelectedDeck(List<Deck> decks) {
        System.out.println("Please select a deck using its id.");
        Deck deck = null;
        while (deck == null) {
            printDecks(decks);
            int deckIndex = getIntFromUser() - ID_START;
            try {
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
    //          If confirmation is 'y', deletes the deck, sets isUnsaved to true, and returns true.
    //          If confirmation is anything else, returns false.
    private boolean deleteDeck() {
        Deck deck = getSelectedDeck(decks);
        System.out.println("Please enter 'y' to delete the deck, or anything else to cancel:");
        String response = getStringFromUser();
        if (!response.equalsIgnoreCase("y")) {
            return false;
        }
        decks.remove(deck);
        isUnsaved = true;
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

    // MODIFIES: deck
    // EFFECTS: performs card creation, informing user of result (i.e. success or cancelled)
    private void createCardAndNotify(Deck deck) {
        boolean cardCreated = createCard(deck);
        if (cardCreated) {
            System.out.print("The card has been created. ");
        } else {
            System.out.print("Card creation cancelled. ");
        }
    }

    // MODIFIES: deck
    // EFFECTS: gets a question and answer for a new card from the user, creates
    //          a new card, adds to given deck, sets isUnsaved to true, and returns true
    //          if question and answer are not blank. Cancels operation if given question
    //          or answer are blank.
    private boolean createCard(Deck deck) {
        Map<String, String> fields = new HashMap<>();
        Arrays.asList(new String[] {"question", "answer"})
                .forEach(fieldName -> fields.put(fieldName, null));
        String promptTemplate = "Please enter a(n) {0} for the new card, or type enter to cancel:";
        setFieldValues(fields, true, promptTemplate);
        boolean fieldsAllSetByUser = fields.values().stream().allMatch(Objects::nonNull);
        if (!fieldsAllSetByUser) {
            return false;
        }
        Card card = new Card(fields.get("question"), fields.get("answer"));
        deck.addCard(card);
        isUnsaved = true;
        return true;
    }

    // MODIFIES: fields
    // EFFECTS: iterates through keys of fields, prompting user to enter a value for the field
    //          with name equal to this key. If this given value is not blank, enters this value
    //          in fields under that key. If breakOnBlank is true, will break if given value is
    //          blank.
    private void setFieldValues(Map<String, String> fields, boolean breakOnBlank, String promptTemplate) {
        for (String name : fields.keySet()) {
            String prompt = MessageFormat.format(promptTemplate, name);
            System.out.println(prompt);
            String value = getStringFromUser();
            if (!value.isEmpty()) {
                fields.put(name, value);
            }
            if (breakOnBlank && value.isEmpty()) {
                break;
            }
        }
    }

    // MODIFIES: deck
    // EFFECTS: prints card configuration menu, gets user selection, and processes selection
    private void printCardConfigMenuAndProcessSelection(Deck deck) {
        printCardConfigMenu();
        String selection = getStringFromUser();
        processCardConfigMenuSelection(selection, deck);
    }

    // EFFECTS: prints card configuration menu
    private void printCardConfigMenu() {
        System.out.println("Please select one of the following options:");
        System.out.println("Enter 'v' to view the list of cards.");
        System.out.println("Enter 'c' to create a card.");
        System.out.println("Enter 'm' to modify a card's fields.");
        System.out.println("Enter 'd' to delete a card.");
        System.out.println("Enter 'b' to return to the deck configuration menu.");
        System.out.println("Enter anything else to return to the main menu.");
    }

    // MODIFIES: deck
    // EFFECTS: processes card configuration menu user selection
    private void processCardConfigMenuSelection(String selection, Deck deck) {
        switch (selection.toLowerCase()) {
            case "v":
                viewCards(deck);
                break;
            case "c":
                createCardAndNotify(deck);
                break;
            case "m":
                routeModifyCard(deck);
                break;
            case "d":
                routeDeleteCard(deck);
                break;
            case "b":
                printDeckConfigMenuAndProcessSelection();
                return;
            default:
                printMainMenuAndProcessSelection();
                return;
        }
        System.out.println("Returning to card configuration menu.\n");
        printCardConfigMenuAndProcessSelection(deck);
    }

    // MODIFIES: deck
    // EFFECTS: prints cards if cards is not empty. If cards is empty, routes user
    //          to empty cards handler.
    private void viewCards(Deck deck) {
        if (!deck.hasCards()) {
            handleNoCards(deck);
        } else {
            printCards(deck);
            System.out.println("Please enter anything to continue:");
            getStringFromUser();
        }
    }

    // EFFECTS: prints the cards in the given deck in csv format
    private void printCards(Deck deck) {
        if (!deck.hasCards()) {
            System.out.println("There are no cards to print!");
            return;
        }
        System.out.println("Here are all of the cards:\n");
        System.out.println("id, question, answer, number of results");
        System.out.println("---------------------------------------");
        IntStream.range(0, deck.getSize()).forEach(i -> {
            int cardId = i + ID_START;
            Card card = deck.getCard(i);
            String line = MessageFormat.format("{0}, {1}, {2}, {3}",
                    cardId, card.getQuestion(), card.getAnswer(), card.getResults().size());
            System.out.println(line);
        });
        System.out.println();
    }

    // MODIFIES: deck
    // EFFECTS: performs card selection and modification, informing user of result
    //          (i.e. success or cancelled) if deck is not empty. If deck is empty,
    //          routes user to empty deck handler.
    private void routeModifyCard(Deck deck) {
        if (!deck.hasCards()) {
            handleNoCards(deck);
        } else {
            Card card = getSelectedCard(deck);
            boolean cardModified = modifyCard(card);
            if (cardModified) {
                System.out.print("The card has been modified. ");
            } else {
                System.out.print("Card modification cancelled. ");
            }
        }
    }

    // MODIFIES: card
    // EFFECTS: gets a question and answer for given card from the user,
    //          updates question and answer, sets isUnsaved to true, and returns true
    //          if these fields are not both blank. Cancels operation if given question
    //          and answer are blank.
    private boolean modifyCard(Card card) {
        Map<String, String> fields = new HashMap<>();
        Arrays.asList(new String[] {"question", "answer"})
                .forEach(fieldName -> fields.put(fieldName, null));
        String promptTemplate = "Please enter a new {0} for the card, or type enter to skip:";
        setFieldValues(fields, false, promptTemplate);
        boolean anyFieldLoadedByUser = fields.values().stream().anyMatch(Objects::nonNull);
        if (!anyFieldLoadedByUser) {
            return false;
        }
        if (fields.get("question") != null) {
            card.setQuestion(fields.get("question"));
        }
        if (fields.get("answer") != null) {
            card.setAnswer(fields.get("answer"));
        }
        isUnsaved = true;
        return true;
    }

    // REQUIRES: deck.hasCards()
    // EFFECTS: acquires card from deck using index supplied by user. Re-prompts if
    //          invalid index supplied.
    private Card getSelectedCard(Deck deck) {
        System.out.println("Please select a card using its id.");
        Card card = null;
        while (card == null) {
            printCards(deck);
            try {
                int cardIndex = getIntFromUser() - ID_START;
                card = deck.getCard(cardIndex);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("The id you provided is invalid. Please provide a valid id.");
            }
        }
        return card;
    }

    // MODIFIES: deck
    // EFFECTS: performs card selection and deletion, informing user of result
    //          (i.e. success or cancelled) if deck is not empty. If deck is empty,
    //          routes user to empty deck handler.
    private void routeDeleteCard(Deck deck) {
        if (!deck.hasCards()) {
            handleNoCards(deck);
        } else {
            boolean cardWasDeleted = deleteCard(deck);
            if (cardWasDeleted) {
                System.out.print("The card has been deleted. ");
            } else {
                System.out.print("Card deletion cancelled. ");
            }
        }
    }

    // MODIFIES: deck
    // EFFECTS: gets a user selected card from given deck. Then gets a confirmation from the user.
    //          If the confirmation is 'y', deletes the card from the given deck, sets isUnsaved to true
    //          and returns true. If confirmation is blank, returns false.
    private boolean deleteCard(Deck deck) {
        Card card = getSelectedCard(deck);
        System.out.println("Please enter 'y' to delete the card, or type anything else to cancel:");
        String response = getStringFromUser();
        if (!response.equalsIgnoreCase("y")) {
            return false;
        }
        deck.removeCard(card);
        isUnsaved = true;
        return true;
    }

    // EFFECTS: routes user to deck creation if decks is empty, routes user onward if
    //          decks is not empty
    private void routeStudyMenu() {
        if (decks.isEmpty()) {
            handleNoDecks();
            System.out.println("Returning to main menu.\n");
            printMainMenuAndProcessSelection();
        } else {
            routeStudyMenuDecksNotEmpty();
        }
    }

    // MODIFIES: this
    // EFFECTS: gets a user selected deck from decks. If selected deck is empty, then
    //          routes user to empty deck handler. If selected deck is not empty, then
    //          routes user to study session.
    private void routeStudyMenuDecksNotEmpty() {
        Deck deck = getSelectedDeck(decks);
        if (!deck.hasCards()) {
            handleNoCards(deck);
            System.out.println("Returning to card configuration menu.\n");
            printCardConfigMenuAndProcessSelection(deck);
        } else {
            System.out.println("Starting study session!");
            studySession(deck);
            System.out.println("Study session complete! Returning to main menu.\n");
            printMainMenuAndProcessSelection();
        }
    }

    // MODIFIES: deck
    // EFFECTS: for each card in the given deck, prints card data in specific order, and then
    //          updates the card with a result based on user supplied rating. Also sets
    //          isUnsaved to false.
    private void studySession(Deck deck) {
        if (!deck.hasCards()) {
            System.out.println("The selected deck has no cards to study!");
            return;
        }
        boolean isFirstCard = true;
        for (Card card: deck.getCards()) {
            if (sessionShouldEnd(isFirstCard)) {
                break;
            }
            isFirstCard = false;
            printCardForStudySession(card);
            Result result = getResultFromUser();
            card.addResult(result);
            isUnsaved = true;
            System.out.println();
        }
    }

    // MODIFIES: isFirstCard
    // EFFECTS: if ifFirstCard is false, prompts user to choose between quitting and continuing. If user
    //          chooses quit, returns true. Otherwise, sets isFirstCard to false and returns false.
    private boolean sessionShouldEnd(Boolean isFirstCard) {
        if (!isFirstCard) {
            System.out.println("Enter q to quit the study session or anything else to continue");
            String response = getStringFromUser();
            return response.equalsIgnoreCase("q");
        }
        return false;
    }

    // EFFECTS: prints question, pauses until user enters output, then prints answer
    private void printCardForStudySession(Card card) {
        Map<String, String> fields = new HashMap<>();
        fields.put("question", card.getQuestion());
        fields.put("answer", card.getAnswer());
        fields.forEach((k, v) -> {
            String prompt = MessageFormat.format("Here is the {0}:", k);
            System.out.println(prompt);
            System.out.println(v);
            System.out.println();
            if (k.equals("question")) {
                System.out.println("Enter anything to see the answer:");
                getStringFromUser();
            }
        });
    }

    // EFFECTS: gets a result based on user supplied difficulty rating
    private Result getResultFromUser() {
        Result result = null;
        while (result == null) {
            String prompt = MessageFormat.format(
                    "Enter a difficulty between {0} (highest difficulty) and {1} (lowest difficulty).",
                    Result.MAX_DIFFICULTY, Result.MIN_DIFFICULTY);
            System.out.println(prompt);
            int difficulty = getIntFromUser();
            try {
                result = new Result(difficulty);
            } catch (InvalidResultDifficultyException e) {
                System.out.println("Please enter a valid difficulty.");
            }
        }
        return result;
    }

    // EFFECTS: attempts to save data. Notifies user of status and returns to main menu.
    private void routeSaveData() {
        boolean wasDataSaved = saveData();
        if (wasDataSaved) {
            System.out.print("Data has been saved. ");
        } else {
            System.out.print("Error: unable to save data. ");
        }
        System.out.println("Returning to main menu.");
        printMainMenuAndProcessSelection();
    }

    // MODIFIES: this
    // EFFECTS: attempts to write decks data to file in JSON format. Returns true if successful
    //          and false otherwise.
    private boolean saveData() {
        try {
            jsonWriter.write(decks);
            isUnsaved = false;
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: attempts to load data. Notifies user of status and returns to main menu.
    private void routeLoadData() {
        boolean wasDataLoaded = loadData();
        if (wasDataLoaded) {
            System.out.print("Data has been loaded. ");
        } else {
            System.out.print("Error: unable to read data. ");
        }
        System.out.println("Returning to main menu.");
        printMainMenuAndProcessSelection();
    }

    // EFFECTS: attempts to load decks data from file in JSON format. Returns true if successful
    //          and false otherwise.
    private boolean loadData() {
        try {
            decks = jsonReader.read();
            isUnsaved = false;
            return true;
        } catch (IOException | JSONException ex) {
            return false;
        }
    }

    // EFFECTS: Notifies the user if there are unsaved changes and allows user to save
    //          these changes. Then quits the application.
    private void routeQuit() {
        if (isUnsaved) {
            System.out.println("Warning: you have unsaved data!");
            System.out.println("Please enter 'y' to save and quit, or enter anything else to quit without saving.");
            String response = getStringFromUser();
            if (response.equalsIgnoreCase("y")) {
                boolean wasDataSaved = saveData();
                if (wasDataSaved) {
                    System.out.println("Data has been saved.");
                } else {
                    System.out.println("Error: unable to save data.");
                }
            }
        }
        System.out.println("Bye for now!");
        System.exit(0);
    }

}
