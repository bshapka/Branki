package model;

import java.util.ArrayList;
import java.util.List;

// represents a card with a question, an answer, and a list of results
public class Card {

    private String question;
    private String answer;
    private List<Result> results;

    // EFFECTS: constructs a Card. Sets question and answer to given values, and results to an empty list.
    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
        results = new ArrayList<>();
    }

    // EFFECTS: returns question
    public String getQuestion() {
        return question;
    }

    // EFFECTS: returns answer
    public String getAnswer() {
        return answer;
    }

    // EFFECTS: returns results
    public List<Result> getResults() {
        return results;
    }

    // MODIFIES: this
    // EFFECTS: sets question to given value
    public void setQuestion(String question) {
        this.question = question;
    }

    // MODIFIES: this
    // EFFECTS: sets answer to given value
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // MODIFIES: this
    // EFFECTS: adds given result to results
    public void addResult(Result result) {
        results.add(result);
    }

    // EFFECTS: returns true if the majority of results indicate difficulty, else returns false
    public boolean isDifficult() {
        int resultCount = 0;
        int difficultCount = 0;
        for (Result result : results) {
            resultCount++;
            if (result.wasDifficult()) {
                difficultCount++;
            }
        }
        return difficultCount > resultCount / 2;
    }

}
