package game.action;

import game.Player;

/**
 * represents an action that can be performed by a player
 */
public interface Action {

    /**
     * executes the action for the given player
     * 
     * @param player the player performing the action
     */
    void act(Player player);
}
