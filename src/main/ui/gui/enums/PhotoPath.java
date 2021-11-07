package ui.gui.enums;

// represents various file paths for photos
public enum PhotoPath {

    CAT_LOAF("cat_loaf.png"),
    TOBY("tobs.png");

    private final String path;

    // EFFECTS: constructs instance using given fileName by prepending directory path
    PhotoPath(String fileName) {
        this.path = "./images/" + fileName;
    }

    // EFEFCTS: returns path
    public String getPath() {
        return path;
    }

}
