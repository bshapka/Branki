package ui.gui.views.windows.study;

import exceptions.DeckHasNoCardsException;
import model.Card;
import model.Deck;
import ui.gui.GUI;
import ui.gui.enums.DialogMessage;
import ui.gui.views.windows.main.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

// represents a study deck window that displays a question, shows the answer when asked, and logs a result
public class StudyDeckWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel textFieldsPanel;
    private JPanel buttonsPanel;
    private JLabel questionLabel;
    private JLabel answerLabel;
    private JTextField questionTextField;
    private JTextField answerTextField;
    private JPanel answerContainer;
    private JButton showAnswerButton;
    private JButton veryEasyButton;
    private JButton easyButton;
    private JButton hardButton;
    private JButton veryHardButton;
    private final Iterator<Card> cardIterator;
    private Card currentCard;

    private static final int LAYOUT_GAP = 10;

    // EFFECTS: throws DeckHasNoCardsException if given deck has no cards. If given deck has cards,
    //          sets up frame and iterator, shows first card, adds main panel, and packs/sizes frame.
    public StudyDeckWindow(Deck deck) throws DeckHasNoCardsException {
        if (!deck.hasCards()) {
            throw new DeckHasNoCardsException(DialogMessage.STUDY_DECK_HAS_NO_CARDS.getMessage());
        }
        setupFrame();
        cardIterator = deck.getCards().iterator();
        showNextCard();
        add(mainPanel);
        packAndSetSize();
    }

    // MODIFIES: this
    // EFFECTS: sets the closing behaviour, sets up buttons, labels, text areas, and associated panels
    private void setupFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Study Session");
        setupButtons();
        setupLabels();
        setupTextFields();
        setupTextFieldsPanel();
        setupButtonsPanel();
        setupMainPanel();
    }

    // MODIFIES: this
    // EFFECTS: instantiates all of the buttons and adds action listeners to them
    private void setupButtons() {
        showAnswerButton = new JButton("Show Answer");
        veryEasyButton = new JButton("Very Easy");
        easyButton = new JButton("Easy");
        hardButton = new JButton("Hard");
        veryHardButton = new JButton("Very Hard");
        showAnswerButton.addActionListener(e -> showAnswer());
        veryEasyButton.addActionListener(e -> logResultAndShowNextCard(1));
        easyButton.addActionListener(e -> logResultAndShowNextCard(2));
        hardButton.addActionListener(e -> logResultAndShowNextCard(3));
        veryHardButton.addActionListener(e -> logResultAndShowNextCard(4));
    }

    // MODIFIES: this
    // EFFECTS: instantiates all of the labels
    private void setupLabels() {
        questionLabel = new JLabel("Question:");
        answerLabel = new JLabel("Answer:");
    }

    // MODIFIES: this
    // EFFECTS: instantiates all of the text areas and disables them
    private void setupTextFields() {
        questionTextField = new JTextField();
        answerTextField = new JTextField();
        questionTextField.setEnabled(false);
        answerTextField.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: adds all of the labels and text areas to a panel
    private void setupTextFieldsPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        textFieldsPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        textFieldsPanel.add(questionLabel, gbc);
        gbc.gridx = 1;
        textFieldsPanel.add(questionTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldsPanel.add(answerLabel, gbc);
        gbc.gridx = 1;
        answerContainer = new JPanel(new GridLayout(1, 1));
        answerContainer.add(answerTextField);
        textFieldsPanel.add(answerContainer, gbc);
    }

    // MODIFIES: this
    // EFFECTS: instantiates buttons panel and adds all buttons to the panel
    private void setupButtonsPanel() {
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(showAnswerButton);
        buttonsPanel.add(veryEasyButton);
        buttonsPanel.add(easyButton);
        buttonsPanel.add(hardButton);
        buttonsPanel.add(veryHardButton);
    }

    // MODIFIES: this
    // EFFECTS: instantiates main panel and adds the question, answer, and buttons panels to the main panel
    private void setupMainPanel() {
        mainPanel = new JPanel(new BorderLayout(LAYOUT_GAP, LAYOUT_GAP));
        mainPanel.add(textFieldsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: enables show answer button, disables all other buttons, and hides the answer text area
    private void hideAnswer() {
        showAnswerButton.setEnabled(true);
        veryEasyButton.setEnabled(false);
        easyButton.setEnabled(false);
        hardButton.setEnabled(false);
        veryHardButton.setEnabled(false);
        answerContainer.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: disables show answer button, enables all other buttons, and shows the answer text area
    private void showAnswer() {
        showAnswerButton.setEnabled(false);
        veryEasyButton.setEnabled(true);
        easyButton.setEnabled(true);
        hardButton.setEnabled(true);
        veryHardButton.setEnabled(true);
        answerContainer.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: saves result with given difficulty, then attempts to show the next card. If the next card
    //          was not shown, then the study session is over and a message stating this is shown.
    private void logResultAndShowNextCard(int difficulty) {
        GUI.addResult(difficulty, currentCard);
        boolean nextCardShown = showNextCard();
        if (!nextCardShown) {
            GUI.showStudySessionCompleteMessage();
        }
    }

    // MODIFIES: this
    // EFFECTS: if cardIterator has a next card, updates text areas to show next card, hides the answer, repaints
    //          and returns true. If cardIterator does not have a next card, returns false
    private boolean showNextCard() {
        if (cardIterator.hasNext()) {
            currentCard = cardIterator.next();
            questionTextField.setText(currentCard.getQuestion());
            answerTextField.setText(currentCard.getAnswer());
            hideAnswer();
            repaint();
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: packs the frame and sets the size
    private void packAndSetSize() {
        pack();
        setSize((int) (buttonsPanel.getWidth() * 1.10), MainWindow.HEIGHT);
    }

}
