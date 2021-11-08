package ui.gui.views;

import model.Deck;
import ui.gui.GUI;

import javax.swing.*;
import java.awt.*;

// represents an edit deck window that displays the name, size, and difficulty of a deck. Also allows
// users to update the name or delete the deck.
public class CreateDeckWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel textFieldsPanel;
    private JPanel buttonsPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JButton createButton;

    private static final int LAYOUT_GAP = 10;

    // EFFECTS:
    public CreateDeckWindow() {
        setupFrame();
        add(mainPanel);
        packAndSetSize();
    }

    // MODIFIES: this
    // EFFECTS: sets the closing behaviour, sets up buttons, labels, text field, and associated panels
    private void setupFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        createButton.addActionListener(e -> GUI.createDeck(nameTextField.getText()));
    }

    // MODIFIES: this
    // EFFECTS: instantiates all of the labels
    private void setupLabels() {
        nameLabel = new JLabel("Name:");
    }

    // MODIFIES: this
    // EFFECTS: instantiates all of the text areas and disables all fields other than name
    private void setupTextFields() {
        nameTextField = new JTextField("", 10);
        nameTextField.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: adds all of the labels and text fields to a panel
    private void setupTextFieldsPanel() {
        textFieldsPanel = new JPanel(new FlowLayout());
        textFieldsPanel.add(nameLabel);
        textFieldsPanel.add(nameTextField);
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
