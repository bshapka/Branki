package ui.gui.views.windows.deck;

import ui.gui.GUI;
import ui.gui.views.windows.main.MainWindow;

import javax.swing.*;
import java.awt.*;

// represents a create deck window that allows users to create a deck by giving a name
public class CreateDeckWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel textFieldsPanel;
    private JPanel buttonsPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JButton createButton;

    private static final int LAYOUT_GAP = 10;

    // EFFECTS: sets up the frame, adds mainPanel to frame, and packs and sizes the frame
    public CreateDeckWindow() {
        setupFrame();
        add(mainPanel);
        packAndSetSize();
    }

    // MODIFIES: this
    // EFFECTS: sets the closing behaviour, sets up buttons, labels, text field, and associated panels
    private void setupFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Create Deck");
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
        GridBagConstraints gbc = new GridBagConstraints();
        textFieldsPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        textFieldsPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        textFieldsPanel.add(nameTextField, gbc);
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
