package ui;

import com.apple.eawt.Application;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

// represents a main window of the GUI application
class MainWindow extends JFrame {

    // the width of the window
    static final int WIDTH = 500;
    // the height of the window
    static final int HEIGHT = WIDTH;

    private JLabel background;
    private MainMenuBar menuBar;

    // MODIFIES: this
    // EFFECTS: sets up frame, icon, background, menu bar, and sets frame visible
    MainWindow() {
        setupFrame();
        setupIcon();
        setupBackground();
        setupMenuBar();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets title, size, close operation of frame and allows resizing
    private void setupFrame() {
        this.setTitle(App.APP_NAME);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
    }

    // MODIFIES: this
    // EFFECTS: sets icon to desired icon image, and sets dock icon to this icon for Macs
    private void setupIcon() {
        ImageIcon icon = new ImageIcon("./images/icon.png");
        this.setIconImage(icon.getImage());
        Application.getApplication().setDockIconImage(icon.getImage()); // sets dock icon for Macs
    }

    // MODIFIES: this
    // EFFECTS: adds background image and message to frame
    private void setupBackground() {
        background = new JLabel();
        String welcomeMessage = MessageFormat.format(
                "<html>{0}<br><br>{1}</html>",
                App.getWelcomeMessage(),
                "Use the above menu bar to interact with the app.");
        ImageIcon image = new ImageIcon("./images/main_menu.png");
        background.setIcon(image);
        background.setText(welcomeMessage);
        background.setFont(new Font("Futura", Font.PLAIN, 20));
        this.add(background);
    }

    // MODIFIES: this
    // EFFECTS: adds menu bar to frame
    private void setupMenuBar() {
        menuBar = new MainMenuBar();
        this.setJMenuBar(menuBar);
    }

}