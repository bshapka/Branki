package ui.gui.views.windows.deck;

import model.Deck;
import ui.gui.GUI;
import ui.gui.views.windows.main.MainWindow;

import javax.swing.*;
import java.awt.*;

// represents an edit deck window that displays the name, size, and difficulty of a deck. Also allows
// users to update the name of or delete the deck.
public class EditDeckWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel textFieldsPanel;
    private JPanel buttonsPanel;
    private JLabel nameLabel;
    private JLabel sizeLabel;
    private JLabel hasDifficultCardsLabel;
    private JTextField nameTextField;
    private JTextField sizeTextField;
    private JTextField hasDifficultCardsTextField;
    private JButton updateButton;
    private JButton deleteButton;
    private final Deck deck;

    private static final int LAYOUT_GAP = 10;

    // EFFECTS: initializes deck, sets up the frame, adds mainPanel to frame, and packs and sizes the frame
    public EditDeckWindow(Deck deck) {
        this.deck = deck;
        setupFrame();
        add(mainPanel);
        packAndSetSize();
    }

    // MODIFIES: this
    // EFFECTS: sets the closing behaviour, sets up buttons, labels, text fields, and associated panels
    private void setupFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Edit Deck");
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
        updateButton.addActionListener(e -> GUI.updateDeck(nameTextField.getText(), deck));
        deleteButton.addActionListener(e -> GUI.deleteDeck(deck));
    }

    // MODIFIES: this
    // EFFECTS: instantiates all of the labels
    private void setupLabels() {
        nameLabel = new JLabel("Name:");
        sizeLabel = new JLabel("Size:");
        hasDifficultCardsLabel = new JLabel("Has Difficult Cards:");
    }

    // MODIFIES: this
    // EFFECTS: instantiates all of the text areas and disables all fields other than name
    private void setupTextFields() {
        nameTextField = new JTextField();
        sizeTextField = new JTextField();
        hasDifficultCardsTextField = new JTextField();
        sizeTextField.setEnabled(false);
        hasDifficultCardsTextField.setEnabled(false);
        nameTextField.setText(deck.getName());
        sizeTextField.setText(Integer.toString(deck.getSize()));
        hasDifficultCardsTextField.setText(deck.hasDifficultCards() ? "Yes" : "No");
    }

    // MODIFIES: this
    // EFFECTS: adds all of the labels and text fields to a panel
    private void setupTextFieldsPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        textFieldsPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        textFieldsPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        textFieldsPanel.add(nameTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        textFieldsPanel.add(sizeLabel, gbc);
        gbc.gridx = 1;
        textFieldsPanel.add(sizeTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        textFieldsPanel.add(hasDifficultCardsLabel, gbc);
        gbc.gridx = 1;
        textFieldsPanel.add(hasDifficultCardsTextField, gbc);
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
