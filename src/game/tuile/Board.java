package tuile;
import java.util.*;

import util.Direction;
import util.Position;

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
    for (int x=0; x<width; x++){
        for (int y=0; y<height; y++){
            this.grid[x][y]= new Sea();
        }
    }
}

/** return the board
 * @return the grid of the board
 */
public Tuile[][] getGrid(){
    return this.grid;
}

/** return true if the given position is the sea, false otherwise
 * @param Position the position
 * @return true if a earth tile is on the given position
 */
public boolean isEmpty(Position pos){
    if ( this.grid[pos.getX()][pos.getY()] instanceof Sea){
        return false;
    }
    return true;
}

/** return true of this tile have a neighbor, false otherwise 
 * @param int x
 * @param int y
 * @return true if the tile have a neighbor 
*/
public boolean haveNeighbor(Position pos){
    boolean res= false;
    for (Direction d: Direction.values()){
        Position neighbor= pos.next(d);
        if (!isEmpty(neighbor)){
            res= true;
            break;
        }
    }
    return res;
}

/** creates a new board for the game randomly */
public void createBoard(){}

/** put the tile to the given coordinate
 * @param Tuile t
 * @param Position pos
 */
public void put(Tuile t, Position pos){
    this.grid[pos.getX()][pos.getY()]= t;
};

/** put the tile randomly next to the given position
 * @param Tuile t
 * @param Position pos
 */
public void putNeighbor(Tuile t, Position pos){};

/** return a random position on the board 
 * @return a position on the board
*/
public Position randomCoord(){
    Random randomNumbers = new Random();
    int x= randomNumbers.nextInt(this.width);
    int y= randomNumbers.nextInt(this.height);
    Position RandomPos= new Position(x,y);
    while (!this.isEmpty(RandomPos)){
        x= randomNumbers.nextInt(this.width);
        y= randomNumbers.nextInt(this.height);
        RandomPos= new Position(x,y);
    }
    return RandomPos;
}

}
