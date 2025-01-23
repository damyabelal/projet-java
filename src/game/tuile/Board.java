package game.tuile;
import java.util.*;
import game.util.*;


public class Board{
    private int width;
    private int height;
    private Tuile[][] grid;

/** initialises a new board for the game  with the given height and the given width
 * then fills every case of the board with a sea tile. 
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
        


/** displays the board with the isles placed randomly  */
public void display(){}



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
public void setTile(Tuile tile, Position pos){
    this.grid[pos.getX()][pos.getY()]= tile;

}


/** return true if the given position is the sea, false otherwise
 * @param Position the position
 * @return true if a earth tile is on the given position
 */
public boolean isEmpty(Position pos){
    if ( this.grid[pos.getX()][pos.getY()] instanceof Sea){
        return true;
    }
    return false;
}

/** return true of this tile have a neighbor, false otherwise 
 * @param int x
 * @param int y
 * @return true if the tile have a neighbor 
*/
public boolean haveNeighbor(Position pos){
    for (Direction d: Direction.values()){
        Position neighbor= pos.next(d);
        if (!isEmpty(neighbor)){
            return true ;
        }
    }
    return false ;
}

/** creates a new board for the game randomly */
public void createBoard(){}



/** put the tile on the given position+
 * @param Tuile t
 * @param Position pos
 */
public void put(Tuile t, Position pos){
    this.grid[pos.getX()][pos.getY()]= t;
}



/* return a random position on the board 
 * @return a position on the board
 * 
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

/** returns the number of the earth tiles that we should place on the board using the width and the height
 * of the said board
 * @return number of earth tiles to be palced on the board
 * 
 */
public int tileNumber(){
    return Math.round(this.getHeight()*this.getWidth()*1/3);
}

/** places half of the number of tiles randomly on the board */
public void placeHalfEarthTiles(){
    int nbretuile= this.tileNumber();
    int nbretuilesinitiale=nbretuile/2;
    for (int i=0 ; i< nbretuilesinitiale ;i++){
        Position randomcoord = this.randomCoord();
        Tuile tuileRandom = randomTuile();
        
    }
}

/** return one  random position on the board    */
public Position randomPosition(){
    int w= this.getWidth();
    int h=this.getHeight();
    int xalea=(int) (Math.random()*w);
    int yalea=(int) (Math.random()*h);

    return new Position(xalea,yalea);

}


/** return the list of all the empty neighbor around a position
 * @param Position
 * @return ArrayList<Position> the list of the empty neighbor
 */
public ArrayList<Position> haveEmptyNeighboorList(Position pos){
    ArrayList<Position> res= new ArrayList<>();
    for (Direction d: Direction.values()){
        Position neighbor= pos.next(d);
        if (isEmpty(neighbor)){
            res.add(neighbor) ;
        }
    }
    return res ;
}

/** place a random tile next to a tile who doesn't have a neighbor
 */
public void placeNeighboorEarthTiles(){
    for (int x=0; x<this.getWidth(); x++){
        for (int y=0; y<this.getHeight(); y++){
            Position currentPos= new Position(x, y);
            if (!isEmpty(currentPos) && !haveNeighbor(currentPos)){
                ArrayList<Position> Npos= this.haveEmptyNeighboorList(currentPos);
                Random choiceRandom = new Random();
                int random= choiceRandom.nextInt(Npos.size());
                put( this.randomTuile(), Npos.get(random));
            }
        }
    }

}
/*
 * @return a random tile
 */
private Tuile randomTuile (){
    Map<Integer,Tuile> tuileTypes= new HashMap<Integer,Tuile>();
    tuileTypes.put(0,new Forest() );
    tuileTypes.put(1,new Mountain());
    tuileTypes.put(2,new Pasture()) ;
    tuileTypes.put(3,new Field());

    Random choiceRandom = new Random();
    int random = choiceRandom.nextInt(4);

    return tuileTypes.get(random);
}

}







