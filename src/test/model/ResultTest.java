package model;

import exceptions.InvalidResultDifficultyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

// test class for Result class
public class ResultTest {

    Result result;
    static final int DEFAULT_DIFFICULTY = (Result.MAX_DIFFICULTY + Result.MIN_DIFFICULTY) / 2;

    @BeforeEach
    void setup() throws InvalidResultDifficultyException {
        result = new Result(DEFAULT_DIFFICULTY);
    }

    @Test
    void testExceptionThrownWhenDifficultyIsBelowMin() {
        int invalidDifficulty = Result.MIN_DIFFICULTY - 1;
        assertThrows(InvalidResultDifficultyException.class, () -> new Result(invalidDifficulty));
    }

    @Test
    void testExceptionNotThrownWhenDifficultyIsMin() {
        assertDoesNotThrow(() -> new Result(Result.MIN_DIFFICULTY));
    }

    @Test
    void testExceptionThrownWhenDifficultyIsAboveMax() {
        int invalidDifficulty = Result.MAX_DIFFICULTY + 1;
        assertThrows(InvalidResultDifficultyException.class, () -> new Result(invalidDifficulty));
    }

    @Test
    void testExceptionNotThrownWhenDifficultyIsMax() {
        assertDoesNotThrow(() -> new Result(Result.MAX_DIFFICULTY));
    }

    @Test
    void testExceptionNotThrownWhenDifficultyIsBetweenMinAndMax() {
        assertDoesNotThrow(() -> new Result((Result.MAX_DIFFICULTY - Result.MIN_DIFFICULTY) / 2));
    }

    @Test
    void testExceptionNotThrownSecondConstructor() {
        int min = Result.MIN_DIFFICULTY;
        int max = Result.MAX_DIFFICULTY;
        LocalDateTime now = LocalDateTime.now();
        assertDoesNotThrow(() -> new Result(min - 1, min, max, now));
        assertDoesNotThrow(() -> new Result(min, min, max, LocalDateTime.now()));
        assertDoesNotThrow(() -> new Result((max - min) / 2, min, max, now));
        assertDoesNotThrow(() -> new Result(max, min, max, LocalDateTime.now()));
        assertDoesNotThrow(() -> new Result(max + 1, min, max, now));
    }

    @Test
    void testGetDifficulty() {
        assertEquals(DEFAULT_DIFFICULTY, result.getDifficulty());
    }

    @Test
    void testGetMinDifficulty() {
        assertEquals(Result.MIN_DIFFICULTY, result.getMinDifficulty());
    }

    @Test
    void testGetMaxDifficulty() {
        assertEquals(Result.MAX_DIFFICULTY, result.getMaxDifficulty());
    }

    @Test
    void testGetDateTime() {
        assertEquals(LocalDateTime.now().toLocalDate(), result.getDateTime().toLocalDate());
        assertEquals(LocalDateTime.now().toLocalTime().getHour(), result.getDateTime().toLocalTime().getHour());
        assertEquals(LocalDateTime.now().toLocalTime().getMinute(), result.getDateTime().toLocalTime().getMinute());
        assertEquals(LocalDateTime.now().toLocalTime().getSecond(), result.getDateTime().toLocalTime().getSecond(), 10);
    }

    @Test
    void testWasDifficultTrue() throws InvalidResultDifficultyException {
        result = new Result(Result.MAX_DIFFICULTY);
        assertTrue(result.wasDifficult());
    }

    @Test
    void testWasDifficultFalse() throws InvalidResultDifficultyException {
        result = new Result(Result.MIN_DIFFICULTY);
        assertFalse(result.wasDifficult());
    }

}
