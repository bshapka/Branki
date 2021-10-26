package model;

import exceptions.InvalidResultDifficultyException;

import java.time.LocalDateTime;

// represents a result with a difficulty, min and max difficulty, and date/time
public class Result {

    public static final int MIN_DIFFICULTY = 1;
    public static final int MAX_DIFFICULTY = 4;

    private int difficulty;
    private int minDifficulty;
    private int maxDifficulty;
    private LocalDateTime dateTime;

    // EFFECTS: constructs a Result. Sets difficulty to given value, minDifficulty and
    //          maxDifficulty to MIN_DIFFICULTY and MAX_DIFFICULTY respectively, and dateTime to now
    public Result(int difficulty) throws InvalidResultDifficultyException {
        if (difficulty < MIN_DIFFICULTY || difficulty > MAX_DIFFICULTY) {
            throw new InvalidResultDifficultyException(difficulty, MIN_DIFFICULTY, MAX_DIFFICULTY);
        }
        this.difficulty = difficulty;
        minDifficulty = MIN_DIFFICULTY;
        maxDifficulty = MAX_DIFFICULTY;
        dateTime = LocalDateTime.now();
    }

    // EFFECTS: constructs a Result instance, setting difficulty, minDifficulty, maxDifficulty,
    //          and dateTime.
    // NOTE: unlike the other constructor, this constructor does not enforce
    //       MIN_DIFFICULTY <= difficulty <= MAX_DIFFICULTY. This is because this constructor
    //       is used to load previous data. Previous data will be valid in the sense that
    //       minDifficulty <= difficulty <= maxDifficulty, but MIN_DIFFICULTY and MAX_DIFFICULTY
    //       may have changed so that MIN_DIFFICULTY <= difficulty <= MAX_DIFFICULTY is not true.
    public Result(int difficulty, int minDifficulty, int maxDifficulty, LocalDateTime dateTime) {
        this.difficulty = difficulty;
        this.minDifficulty = minDifficulty;
        this.maxDifficulty = maxDifficulty;
        this.dateTime = dateTime;
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

    // EFFECTS: returns true if difficulty is in the upper half of the difficulty interval,
    //          defined by minDifficulty and maxDifficulty, else returns false
    public boolean wasDifficult() {
        return difficulty > (minDifficulty + maxDifficulty) / 2;
    }

}
