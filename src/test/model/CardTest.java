package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    Card card;
    static final String DEFAULT_QUESTION = "Question";
    static final String DEFAULT_ANSWER = "Answer";

    @BeforeEach
    void setup() {
        card = new Card(DEFAULT_QUESTION, DEFAULT_ANSWER);
    }

    @Test
    void testGetQuestion() {
        assertEquals(DEFAULT_QUESTION, card.getQuestion());
    }

    @Test
    void testGetAnswer() {
        assertEquals(DEFAULT_ANSWER, card.getAnswer());
    }

    @Test
    void testGetResultsEmpty() {
        assertTrue(card.getResults().isEmpty());
    }

    @Test
    void testGetResultsNonEmpty() {
        Result r1 = new Result(Result.MIN_DIFFICULTY);
        Result r2 = new Result(Result.MIN_DIFFICULTY);

        card.addResult(r1);
        List<Result> results = card.getResults();
        assertEquals(1, results.size());
        assertEquals(r1, results.get(0));

        card.addResult(r2);
        results = card.getResults();
        assertEquals(2, results.size());
        assertEquals(r1, results.get(0));
        assertEquals(r2, results.get(1));
    }

    @Test
    void testSetQuestion() {
        String expectedQuestion = DEFAULT_QUESTION + " x";
        card.setQuestion(expectedQuestion);
        assertEquals(expectedQuestion, card.getQuestion());
    }

    @Test
    void testSetAnswer() {
        String expectedAnswer = DEFAULT_ANSWER + " x";
        card.setAnswer(expectedAnswer);
        assertEquals(expectedAnswer, card.getAnswer());
    }

    @Test
    void testIsDifficultHasNoResults() {
        assertFalse(card.isDifficult());
    }

    @Test
    void testIsDifficultHasResultsNoneDifficult() {
        Result r1 = new Result(Result.MIN_DIFFICULTY);
        Result r2 = new Result(Result.MIN_DIFFICULTY);
        card.addResult(r1);
        card.addResult(r2);
        assertFalse(card.isDifficult());
    }

    @Test
    void testIsDifficultHasResultsAllDifficult() {
        Result r1 = new Result(Result.MAX_DIFFICULTY);
        Result r2 = new Result(Result.MAX_DIFFICULTY);
        card.addResult(r1);
        card.addResult(r2);
        assertTrue(card.isDifficult());
    }

    @Test
    void testIsDifficultHasResultsEqualNumberOfDifficultAndNotDifficult() {
        Result r1 = new Result(Result.MIN_DIFFICULTY);
        Result r2 = new Result(Result.MAX_DIFFICULTY);
        card.addResult(r1);
        card.addResult(r2);
        assertFalse(card.isDifficult());
    }

    @Test
    void testIsDifficultHasResultsMajorityDifficult() {
        Result r1 = new Result(Result.MIN_DIFFICULTY);
        Result r2 = new Result(Result.MAX_DIFFICULTY);
        Result r3 = new Result(Result.MAX_DIFFICULTY);
        card.addResult(r1);
        card.addResult(r2);
        card.addResult(r3);
        assertTrue(card.isDifficult());
    }

    @Test
    void testIsDifficultHasResultsMajorityNotDifficult() {
        Result r1 = new Result(Result.MAX_DIFFICULTY);
        Result r2 = new Result(Result.MIN_DIFFICULTY);
        Result r3 = new Result(Result.MIN_DIFFICULTY);
        card.addResult(r1);
        card.addResult(r2);
        card.addResult(r3);
        assertFalse(card.isDifficult());
    }

}
