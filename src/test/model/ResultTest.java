package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

// test class for Result class
public class ResultTest {

    Result result;
    static final int DEFAULT_DIFFICULTY = (Result.MAX_DIFFICULTY + Result.MIN_DIFFICULTY) / 2;

    @BeforeEach
    void setup() {
        result = new Result(DEFAULT_DIFFICULTY);
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
    void testWasDifficultTrue() {
        result = new Result(Result.MAX_DIFFICULTY);
        assertTrue(result.wasDifficult());
    }

    @Test
    void testWasDifficultFalse() {
        result = new Result(Result.MIN_DIFFICULTY);
        assertFalse(result.wasDifficult());
    }

}
