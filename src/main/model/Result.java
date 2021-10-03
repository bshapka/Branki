package model;

import java.time.LocalDateTime;

// represents a result with a difficulty, min and max difficulty, and datetime
public class Result {

    public static final int MIN_DIFFICULTY = 1;
    public static final int MAX_DIFFICULTY = 4;
    private int difficulty;
    private int minDifficulty;
    private int maxDifficulty;
    private LocalDateTime dateTime;

    // REQUIRES: MIN_DIFFICULTY <= difficulty <= MAX_DIFFICULTY
    // EFFECTS: constructs a Result. Sets difficulty to given value, minDifficulty and
    //          maxDifficulty to the values of corresponding constants, and dateTime to now
    public Result(int difficulty) {
        this.difficulty = difficulty;
        minDifficulty = MIN_DIFFICULTY;
        maxDifficulty = MAX_DIFFICULTY;
        dateTime = LocalDateTime.now();
    }

    // EFFECTS: returns difficulty
    public int getDifficulty() {
        return difficulty;
    }

    // EFFECTS: returns minDifficulty
    public int getMinDifficulty() {
        return minDifficulty;
    }

    // EFFECTS: returns maxDifficulty
    public int getMaxDifficulty() {
        return maxDifficulty;
    }

    // EFFECTS: returns dateTime
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // EFFECTS: returns true if the difficulty is in the upper half of the difficulty interval,
    //          else returns false
    public boolean wasDifficult() {
        return difficulty > (minDifficulty + maxDifficulty) / 2;
    }

}
