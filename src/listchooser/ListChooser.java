package listchooser;

import java.io.IOException;
import java.util.List;

import game.Board;
import game.util.Position;
import listchooser.util.Input;

public class ListChooser<T> {

    /**
    * prompts the user to choose an item from a list of type T
    * if the list is empty or the user chooses not to make a selection, null is returned
    * 
    * @param msg the prompt message
    * @param list the list of items to choose from
    * @return the selected item, or null if no valid choice is made
    */
    
    @SuppressWarnings("hiding")
    public <T> T choose(String msg, List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        int choice = -1;
        while ((choice < 0) || (choice > list.size())) {
            System.out.println(msg);
            System.out.println("      0 - none");
            int index = 1;
            for (T element : list) {
                System.out.println("      " + (index++) + " - " + element);
            }
            System.out.println("            choice ?");
            try {
                choice = Input.readInt(); 
            } catch (java.io.IOException e) {
                System.out.println("Please, enter a number between 0 and " + (index-1));
            }
        }
        if (choice == 0) {
            return null;
        }
        return list.get(choice - 1);
    }
    

    public Position chooseCoordinate(String msg, Board board) throws IOException {
		int choice = -1;
        int x;
        int y; 
        Position pos = null; 
		while ((choice == 0) ) {
			System.out.println(msg);
			x = Input.readInt();
            y= Input.readInt(); 
            pos= new Position(x,y);
            if (!board.isValidPosition(pos) || !board.isBuildable(pos)){
				System.out.println("Please, enter a valid coordinate " );
			}
            else{
                choice= 1; 
            }
		}
		return pos;
	}
}
