package model;

import java.time.LocalDateTime;

// represents a result with a difficulty, min and max difficulty, and datetime
public class Result {

    // REQUIRES: MIN_DIFFICULTY <= difficulty <= MAX_DIFFICULTY
    // EFFECTS: constructs a Result. Sets difficulty to given value, minDifficulty and
    //          maxDifficulty to the values of corresponding constants, and dateTime to now
    public Result(int difficulty) {

    }

    // EFFECTS: returns difficulty
    public int getDifficulty() {
        return 0;
    }

    // EFFECTS: returns minDifficulty
    public int getMinDifficulty() {
        return 0;
    }

    // EFFECTS: returns maxDifficulty
    public int getMaxDifficulty() {
        return 0;
    }

    // EFFECTS: returns dateTime
    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    // EFFECTS: returns true if the difficulty is in the upper half of the difficulty interval,
    //          else returns false
    public boolean wasDifficult() {
        return false;
    }

}
