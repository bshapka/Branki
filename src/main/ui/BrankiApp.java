package ui;

import model.Card;
import model.Deck;
import model.Result;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.util.*;
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
    //          a new card and adds to given deck if question and answer are not
    //          blank. Cancels operation if given question or answer are blank.
    private boolean createCard(Deck deck) {
        Map<String, String> fields = new HashMap<String, String>();
        Arrays.asList(new String[] {"question", "answer"})
                .forEach(fieldName -> fields.put(fieldName, null));
        String promptTemplate = "Please enter a(n) {0} for the new card, or type enter to cancel:";
        setFieldValues(fields, true, promptTemplate);
        boolean fieldsAllSetByUser = fields.values().stream().allMatch(s -> s != null);
        if (!fieldsAllSetByUser) {
            return false;
        }
        Card card = new Card(fields.get("question"), fields.get("answer"));
        deck.addCard(card);
        return true;
    }

    // REQUIRES: fields is not null
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

    // MODIFIES: this
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

    // REQUIRES: deck.cards is not empty
    // EFFECTS: prints the cards in the given deck in csv format
    private void printCards(Deck deck) {
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
    // EFFECTS: gets a question and answer for given card from the user, and
    //          updates question and answer if these fields are not blank.
    //          Cancels operation if given question and answer are blank.
    private boolean modifyCard(Card card) {
        Map<String, String> fields = new HashMap<String, String>();
        Arrays.asList(new String[] {"question", "answer"})
                .forEach(fieldName -> fields.put(fieldName, null));
        String promptTemplate = "Please enter a new {0} for the card, or type enter to skip:";
        setFieldValues(fields, false, promptTemplate);
        boolean anyFieldLoadedByUser = fields.values().stream().anyMatch(s -> s != null);
        if (!anyFieldLoadedByUser) {
            return false;
        }
        if (fields.get("question") != null) {
            card.setQuestion(fields.get("question"));
        }
        if (fields.get("answer") != null) {
            card.setAnswer(fields.get("answer"));
        }
        return true;
    }

    // REQUIRES: deck is not null, cards is not empty
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
    //          If the confirmation is 'y', deletes the card from the given deck and returns true.
    //          If confirmation is blank, does nothing but return false.
    private boolean deleteCard(Deck deck) {
        Card card = getSelectedCard(deck);
        System.out.println("Please enter 'y' to delete the card, or type anything else to cancel:");
        String response = getStringFromUser();
        if (!response.toLowerCase().equals("y")) {
            return false;
        }
        deck.removeCard(card);
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

    // REQUIRES: deck is not null, deck is not empty
    // MODIFIES: deck
    // EFFECTS: for each card in the given deck, prints card data in specific order, and then
    //          updates the card with a result based on user supplied rating.
    private void studySession(Deck deck) {
        for (Card card: deck.getCards()) {
            printCardForStudySession(card);
            Result result = getResultFromUser();
            card.addResult(result);
            System.out.println();
        }
    }

    // EFFECTS: prints question, pauses until user enters output, then prints answer
    private void printCardForStudySession(Card card) {
        Map<String, String> fields = new HashMap<String, String>();
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
                    "Enter a rating between {0} (highest difficulty) and {1} (lowest difficulty).",
                    Result.MAX_DIFFICULTY, Result.MIN_DIFFICULTY);
            System.out.println(prompt);
            int rating = getIntFromUser();
            if (rating > Result.MAX_DIFFICULTY || rating < Result.MIN_DIFFICULTY) {
                System.out.println("Please enter a valid rating.");
            } else {
                result = new Result(rating);
            }
        }
        return result;
    }

    // EFFECTS: quits the application
    private void routeQuit() {
        System.out.println("Bye for now!");
        System.exit(0);
    }

}
