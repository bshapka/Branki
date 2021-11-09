package ui.gui.views.windows.study;

import exceptions.DeckHasNoCardsException;
import exceptions.InvalidResultDifficultyException;
import model.Card;
import model.Deck;
import model.Result;
import ui.gui.GUI;
import ui.gui.enums.DialogMessage;
import ui.gui.views.windows.main.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

// represents a study deck window that displays a question, shows the answer when asked, and logs a result
public class StudyDeckWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel textAreasPanel;
    private JPanel buttonsPanel;
    private JLabel questionLabel;
    private JLabel answerLabel;
    private JTextArea questionTextArea;
    private JTextArea answerTextArea;
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
        setupTextAreas();
        setupTextAreasPanel();
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
    private void setupTextAreas() {
        questionTextArea = new JTextArea();
        answerTextArea = new JTextArea();
        questionTextArea.setEnabled(false);
        answerTextArea.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: adds all of the labels and text areas to a panel
    private void setupTextAreasPanel() {
        textAreasPanel = new JPanel(new GridLayout(2, 2));
        textAreasPanel.add(questionLabel);
        textAreasPanel.add(questionTextArea);
        textAreasPanel.add(answerLabel);
        textAreasPanel.add(answerTextArea);
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
        mainPanel.add(textAreasPanel, BorderLayout.CENTER);
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
        answerTextArea.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: disables show answer button, enables all other buttons, and shows the answer text area
    private void showAnswer() {
        showAnswerButton.setEnabled(false);
        veryEasyButton.setEnabled(true);
        easyButton.setEnabled(true);
        hardButton.setEnabled(true);
        veryHardButton.setEnabled(true);
        answerTextArea.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:
    private void logResultAndShowNextCard(int difficulty) {
        try {
            currentCard.addResult(new Result(difficulty));
            boolean nextCardShown = showNextCard();
            if (!nextCardShown) {
                GUI.showStudySessionCompleteMessage();
            }
        } catch (InvalidResultDifficultyException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "Invalid Difficulty Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: if cardIterator has a next card, updates text areas to show next card, hides the answer, repaints
    //          and returns true. If cardIterator does not have a next card, returns false
    private boolean showNextCard() {
        if (cardIterator.hasNext()) {
            currentCard = cardIterator.next();
            questionTextArea.setText(currentCard.getQuestion());
            answerTextArea.setText(currentCard.getAnswer());
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
