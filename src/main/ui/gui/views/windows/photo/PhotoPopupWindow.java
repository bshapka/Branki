package ui.gui.views.windows.photo;

import javax.swing.*;

// represents a popup window that displays a photo
public class PhotoPopupWindow {

    // EFFECTS: constructs PhotoPopup using given title and filePath
    public PhotoPopupWindow(String title, String filePath) {
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        JLabel label = new JLabel(new ImageIcon(filePath));
        dialog.add(label);
        dialog.pack();
        dialog.setVisible(true);
    }

}
