package game.listchooser;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import game.Board;
import game.util.Position; 

public class RandomListChooser<T> implements ListChooser<T>{

    private Random random = new Random();

    /**
	 * Allows one to choose a random item from a list of items of type T.
	 * If the list of items is empty, null is returned.
	 * The list of items is presented as numbers followed by the 
	 * string representation of the item. 
	 * The possibility not to make a choice is automatically added (choice number 0), 
	 * in this case, null is returned.
	 * 
	 * @param msg The asked question.
	 * @param list The list of items of type T from which one must choose one.
	 * @return The chosen item. null if the list of items is empty or if no choice has been made
	 */
    public T choose(String msg, List<? extends T> list) {
        if (list.isEmpty()) {
			return null;
		}
		System.out.println(msg);
		System.out.print("[0 - none");
		int index = 1;
		for (T element : list) {
			System.out.print(", " + (index++) + " - " + element);
		}
		System.out.println("]");
        int i= random.nextInt(list.size()); 
        //while (i==0){
        //    i= random.nextInt(list.size()); 
        //}
		return list.get(i);
    }

    /**
     * ask the player on which tile he want to act 
     * @param msg the prompt message
     * @param board the board for this game
     * @return the choosen position
     * @throws IOException
     */
    public Position chooseCoordinate(String msg, Board board) throws IOException {
        int x;
        int y; 
        Position pos = null;
        System.out.println(msg); 
        x= random.nextInt(board.getWidth());
        y= random.nextInt(board.getHeight());
        pos= new Position(x,y);
        //c'est fait de manière un peu bête peut être revoir ca plus tard
        while (!board.isValidPosition(pos) || !board.isBuildable(pos)){
            x= random.nextInt(board.getWidth());
            y= random.nextInt(board.getHeight());
            pos= new Position(x,y);
        }
		return pos;
	}
    

}
