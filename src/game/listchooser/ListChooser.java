package game.listchooser;

import java.io.IOException;
import java.util.List;

import game.Board;
import game.util.Position;

public interface ListChooser<T> {

	/**
	 * Allows one to choose an item from a list of items of type T. 
	 * 
	 * @param msg The asked question.
	 * @param list The list of items of type T from which one must choose one.
	 * @return The chosen item.
	 */
	public T choose(String msg, List<? extends T> list);

	/**
     * ask the player on which tile he want to act 
     * @param msg the prompt message
     * @param board the board for this game
     * @return the choosen position
     * @throws IOException
     */
    public Position chooseCoordinate(String msg, Board board) throws IOException ;
} 
