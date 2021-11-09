package ui.gui.views.windows.card;

import model.Card;
import model.Deck;
import ui.gui.GUI;
import ui.gui.views.windows.main.MainWindow;

import javax.swing.*;
import java.awt.*;

// represents an edit card window that displays the question, answer, and size of the results of a card.
// Also allows users to update the question and/or answer of or delete the card.
public class EditCardWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel textFieldsPanel;
    private JPanel buttonsPanel;
    private JLabel questionLabel;
    private JLabel answerLabel;
    private JLabel numberOfResultsLabel;
    private JTextField questionTextField;
    private JTextField answerTextField;
    private JTextField numberOfResultsTextField;
    private JButton updateButton;
    private JButton deleteButton;
    private final Deck deck;
    private final Card card;

    private static final int LAYOUT_GAP = 10;

    // EFFECTS: initializes deck and card, sets up the frame, adds mainPanel to frame, and packs and sizes the frame
    public EditCardWindow(Deck deck, Card card) {
        this.deck = deck;
        this.card = card;
        setupFrame();
        add(mainPanel);
        packAndSetSize();
    }

    // MODIFIES: this
    // EFFECTS: sets the closing behaviour, sets up buttons, labels, text fields, and associated panels
    private void setupFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Edit Card");
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
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        updateButton.addActionListener(e -> GUI.updateCard(
                questionTextField.getText(), answerTextField.getText(), card
        ));
        deleteButton.addActionListener(e -> GUI.deleteCard(deck, card));
    }

    // MODIFIES: this
    // EFFECTS: instantiates all of the labels
    private void setupLabels() {
        questionLabel = new JLabel("Question:");
        answerLabel = new JLabel("Answer:");
        numberOfResultsLabel = new JLabel("Number of Results:");
    }

    // MODIFIES: this
    // EFFECTS: instantiates all of the text areas and disables all fields other than name
    private void setupTextFields() {
        questionTextField = new JTextField();
        answerTextField = new JTextField();
        numberOfResultsTextField = new JTextField();
        questionTextField.setEnabled(true);
        answerTextField.setEnabled(true);
        numberOfResultsTextField.setEnabled(false);
        questionTextField.setText(card.getQuestion());
        answerTextField.setText(card.getAnswer());
        numberOfResultsTextField.setText(Integer.toString(card.getResults().size()));
    }

    // MODIFIES: this
    // EFFECTS: adds all of the labels and text fields to a panel
    private void setupTextFieldsPanel() {
        textFieldsPanel = new JPanel(new GridLayout(3, 2));
        textFieldsPanel.add(questionLabel);
        textFieldsPanel.add(questionTextField);
        textFieldsPanel.add(answerLabel);
        textFieldsPanel.add(answerTextField);
        textFieldsPanel.add(numberOfResultsLabel);
        textFieldsPanel.add(numberOfResultsTextField);
    }

    // MODIFIES: this
    // EFFECTS: adds all of the buttons to a panel
    private void setupButtonsPanel() {
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(updateButton);
        buttonsPanel.add(deleteButton);
    }

    // MODIFIES: this
    // EFFECTS: instantiates main panel and adds the text fields and buttons panels to the main panel
    private void setupMainPanel() {
        mainPanel = new JPanel(new BorderLayout(LAYOUT_GAP, LAYOUT_GAP));
        mainPanel.add(textFieldsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: packs the frame and sets the size
    private void packAndSetSize() {
        pack();
        setSize(MainWindow.WIDTH, MainWindow.HEIGHT);
    }

}
