package ui;

import model.Deck;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DeckSelector extends JFrame {

    private JPanel panel;
    private JList list;
    private JButton submitButton;

    // EFFECTS: sets name and close behaviour of frame, sets up list, submit button, and panel, adds panel,
    //          and sets size of frame
    DeckSelector(List<Deck> decks) {
        setName("Study Deck Selection");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupList(decks);
        setupSubmitButton();
        setupPanel();
        add(panel);
        pack();
        setSize(MainWindow.WIDTH / 2, MainWindow.HEIGHT / 2);
    }

    // MODIFIES: this
    // EFFECTS: instantiates panel and sets its layout
    private void setupPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(BorderLayout.NORTH, new JScrollPane(list));
        panel.add(BorderLayout.SOUTH, submitButton);
    }

    // MODIFIES: this
    // EFFECTS: instantiates button with set text, and adds action listener
    private void setupSubmitButton() {
        submitButton = new JButton("Select");
        submitButton.addActionListener(e -> GUI.startStudySession(list.getSelectedIndex()));
    }

    // MODIFIES: this
    // EFFECTS: instantiates list with deck names in decks and sets some configuration options
    private void setupList(List<Deck> decks) {
        list = new JList(decks.stream().map(Deck::getName).toArray());
        list.setVisibleRowCount(5);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setFont(new Font("Times New Roman", Font.PLAIN, 16));
    }

}
