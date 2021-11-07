package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// test class for NoDecksWithCardsException
public class TestNoDecksWithCardsException {

    private static final String TEST_ERROR_MESSAGE = "Test Message";

    @Test
    void testExceptionErrMsg() {
        String errMsg = new NoDecksWithCardsException(TEST_ERROR_MESSAGE).getMessage();
        assertEquals(TEST_ERROR_MESSAGE, errMsg);
    }

}
