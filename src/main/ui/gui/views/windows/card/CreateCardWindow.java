package ui.gui.views.windows.card;

import model.Deck;
import ui.gui.GUI;
import ui.gui.views.windows.main.MainWindow;

import javax.swing.*;
import java.awt.*;

// represents a create card window that allows users to create a card by giving a question and answer
public class CreateCardWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel textFieldsPanel;
    private JPanel buttonsPanel;
    private JLabel questionLabel;
    private JLabel answerLabel;
    private JTextField questionTextField;
    private JTextField answerTextField;
    private JButton createButton;
    private final Deck deck;

    private static final int LAYOUT_GAP = 10;

    // EFFECTS: initializes deck, sets up frame, adds mainPanel, packs and sets size
    public CreateCardWindow(Deck deck) {
        this.deck = deck;
        setupFrame();
        add(mainPanel);
        packAndSetSize();
    }

    // MODIFIES: this
    // EFFECTS: sets the closing behaviour, sets up buttons, labels, text field, and associated panels
    private void setupFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Create Card");
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
        createButton = new JButton("Create");
        createButton.addActionListener(e ->
                GUI.createCard(deck, questionTextField.getText(), answerTextField.getText()));
    }

    // MODIFIES: this
    // EFFECTS: instantiates all of the labels
    private void setupLabels() {
        questionLabel = new JLabel("Question:");
        answerLabel = new JLabel("Answer:");
    }

    // MODIFIES: this
    // EFFECTS: instantiates all of the text areas and disables all fields other than name
    private void setupTextFields() {
        questionTextField = new JTextField("", 10);
        answerTextField = new JTextField("", 10);
        questionTextField.setEnabled(true);
        answerTextField.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: adds all of the labels and text fields to a panel
    private void setupTextFieldsPanel() {
        textFieldsPanel = new JPanel(new GridLayout(2, 2));
        textFieldsPanel.add(questionLabel);
        textFieldsPanel.add(questionTextField);
        textFieldsPanel.add(answerLabel);
        textFieldsPanel.add(answerTextField);
    }

    // MODIFIES: this
    // EFFECTS: adds all of the buttons to a panel
    private void setupButtonsPanel() {
        buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(createButton);
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
