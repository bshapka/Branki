package exceptions;

import java.text.MessageFormat;

// represents exception thrown to indicate that the intended difficulty of a result is not within the valid
// interval defined by minDifficulty and maxDifficulty
public class InvalidResultDifficultyException extends Exception {

    // EFFECTS: constructs an InvalidResultDifficultyException using the arguments to get an error message
    public InvalidResultDifficultyException(int difficulty, int minDifficulty, int maxDifficulty) {
        super(getErrorMessage(difficulty, minDifficulty, maxDifficulty));
    }

    // EFFECTS: generates desired error message based on values of arguments
    private static String getErrorMessage(int difficulty, int minDifficulty, int maxDifficulty) {
        String errMsgTemplate = "Difficulty of {0} cannot be {1} than {2}Difficulty of {3}";
        if (difficulty < minDifficulty) {
            return MessageFormat.format(errMsgTemplate, difficulty, "less", "min", minDifficulty);
        } else if (difficulty > maxDifficulty) {
            return MessageFormat.format(errMsgTemplate, difficulty, "greater", "max", maxDifficulty);
        } else {
            return "Exception should not have been thrown!";
        }
    }

}
