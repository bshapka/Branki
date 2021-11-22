package model;

import org.json.JSONPropertyIgnore;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

// represents a card with a question, an answer, and a list of results
public class Card implements Selectable {

    private String question;
    private String answer;
    private final List<Result> results;

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

    @Override
    @JSONPropertyIgnore
    // EFFECTS: returns question as description
    public String getDescription() {
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
        String oldQuestion = this.question;
        this.question = question;
        String description = MessageFormat.format(
                "Card question changed from {0} to {1}", oldQuestion, question);
        EventLog.getInstance().logEvent(new Event(description));
    }

    // MODIFIES: this
    // EFFECTS: sets answer to given value
    public void setAnswer(String answer) {
        String oldAnswer = this.answer;
        this.answer = answer;
        String description = MessageFormat.format(
                "Card answer changed from {0} to {1}", oldAnswer, answer);
        EventLog.getInstance().logEvent(new Event(description));
    }

    // MODIFIES: this
    // EFFECTS: adds given result to results
    public void addResult(Result result) {
        results.add(result);
        String description = MessageFormat.format(
                "Result with difficulty {0} added to card with question {1}", result.getDifficulty(), question);
        EventLog.getInstance().logEvent(new Event(description));
    }

    // EFFECTS: returns true if the majority of results indicate difficulty, else returns false
    @JSONPropertyIgnore
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
