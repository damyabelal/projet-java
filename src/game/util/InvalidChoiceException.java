package game.util;

/**
 * A exception for when you make a invalid choice
 */
public class InvalidChoiceException extends Exception {
    /**
     * the exception
     * @param message
     */
    public InvalidChoiceException(String message) {
        super(message);
    }
    
}
