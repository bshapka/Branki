package exceptions;

import java.text.MessageFormat;

public class InvalidResultDifficultyException extends Exception {

    public InvalidResultDifficultyException(int difficulty, int minDifficulty, int maxDifficulty) {
        super(getErrorMessage(difficulty, minDifficulty, maxDifficulty));
    }

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
