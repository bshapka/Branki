package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// test class for InvalidResultDifficultyException
public class TestInvalidResultDifficultyException {

    @Test
    void testExceptionErrMsgDiffTooLow() {
        String errMsg = new InvalidResultDifficultyException(0, 1, 2).getMessage();
        assertEquals(errMsg, "Difficulty of 0 cannot be less than minDifficulty of 1");
    }

    @Test
    void testExceptionErrMsgDiffTooHigh() {
        String errMsg = new InvalidResultDifficultyException(3, 1, 2).getMessage();
        assertEquals(errMsg, "Difficulty of 3 cannot be greater than maxDifficulty of 2");
    }

    @Test
    void testExceptionErrMsgUnexpected() {
        String errMsg = new InvalidResultDifficultyException(2, 1, 3).getMessage();
        assertEquals(errMsg, "Exception should not have been thrown!");
    }

}
