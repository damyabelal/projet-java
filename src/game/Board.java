package game;
import game.util.Direction;

public class Board{
    private int width;
    private int height;
    private Tuile[][] grid;

/** initialises a new board for the game  with the given height and the given width
 * @param int the width of the board
 * @param int the height of the board
*/
public Board(int width , int height){
    this.width=width;
    this.height=height;
    this.grid= new Tuile[width][height];
}

/** return the board
 * @return the grid of the board
 */
public Tuile[][] getGrid(){
    return self.grid;
}

/** return true if the given position is the sea, false otherwise
 * @param int x
 * @param int y
 * @return true if a earth tile is on the given position
 */
public boolean isEmpty(int x, int y){
    if ( self.grid[x][y] instanceof Sea){
        return false;
    }
    return true;
}

/** return true of this tile have a neighbor, false otherwise 
 * @param int x
 * @param int y
 * @return true if the tile have a neighbor 
*/
public boolean haveNeighbor(){
    boolean res= false;
    for (Direction d: Direction.values()){
        if (!this.isEmpty(x,y)){
            res= true;
            break;
        }
    }
    return res;
}

/** creates a new board for the game randomly */
public void createBoard(){}

/** return random coordinate on the board
 * @return Position?  
 */
public /** Position en type??? */ randomCoord(){}

}