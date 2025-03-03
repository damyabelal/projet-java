package game.action;

import game.NoMoreRessourcesException;
import game.Player;

/**
 * represents an action that can be done by a player
 */
public interface Action {

    /**
     * executes the action for the given player
     * 
     * @param player the player performing the action
     * @throws NoMoreRessourcesException 
     */
    void act(Player player) throws NoMoreRessourcesException;
}
