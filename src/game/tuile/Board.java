package tuile;
import java.util.*;
import util.*;

public class Board{
    private int width;
    private int height;
    private Tuile[][] grid;

/** initialises a new board for the game  with the given height and the given width
 * what this method does exactly is creating a 2 dimension board with the given width and height and 
 * then fills every case of the board with a sea tile. The board at this point is not done yet because we
 * still have to place islands randomly (by replacing a sea tile by an earth tile)
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



public int getWidth(){
    return this.width;
}
public int getHeight(){
    return this.height;
}

/** places the given tile  on the board on the given position*/
public void setTile(Position pos, Terrestre tile){
    this.grid[pos.getDx()][pos.getDy]= tile;



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


/** return one  random position on the board    */
public Position randomPosition(){
    int w= this.getWidth();
    int h=this.getHeight();
    int xalea=Math.random()*w;
    int yalea=Math.random()*h;

    return new Position(xalea,yalea);

}

/** returns the number of the earth tiles that we should place on the board using the width and the height
 * of the said board
 * @return number of earth tiles to be palced on the board
 * 
 */
public int tileNumber(){
    return round(this.getHeight()*this.getWidth()*1/3);
}

/** places half of the number of tiles randomly on the board */
public void placeInitialeTiles(){
    int nbretuile= this.tileNumber();
    int nbretuilesinitiale=nbretuile/2;

    for (i=0 , i< nbretuilesinitiale,i++){
        Position randomcoord=this.randomPosition();
        //this.setTile(randomcoord, il manque la tuile); 
        //du coup il faut creer une methode qui renvoie des tuiles  de type aleatoire 



    }


}




}
