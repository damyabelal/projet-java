package game.action;
import game.NoMoreRessourcesException;

/**
 * represents an action that can be done by a player
 */
public interface Action<T> {

    /**
     * executes the action for the given player
     * 
     * @param player the player performing the action
     * @throws NoMoreRessourcesException 
     */
    void act(T player) throws NoMoreRessourcesException;
}
