package game.tuile;
import java.util.*;
import game.util.*;

public class Board{
    private int width;
    private int height;
    private Tuile[][] grid;


/** initialises a new board for the game  with the given height and the given width
 * then fills every case of the board with a sea tile (it is not the final board it would be modified later to add earth tiles)
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
    createBoard();
}


/** return the board
 * @return the grid of the board
 */
public Tuile[][] getGrid(){
    return this.grid;
}

/** returns the width of the board chosen by the user
 * @return the width  of the board
 */

public int getWidth(){
    return this.width;
}

/** return the height of the board chosen by the user
 * @return height of the board
 */
public int getHeight(){
    return this.height;
}


/**
 * Displays the board with symbols enclosed in brackets [ ].
 */ 

public void display(){
    int w = this.getWidth();
    int h = this.getHeight();
    System.out.println(" Plateau du Jeu :");
    System.out.println(" 🌊 : Mer");
    System.out.println(" 🌲 : Forêt");
    System.out.println(" 🐑 : Pâturage");
    System.out.println(" 🏔 : Montagne");
    System.out.println(" 🌸 : Champ");
    
    System.out.println("        ");
    for (int x = 0; x < w; x++) {
        if(x==0){
            System.out.print("|-----|");
        }
        else {
            System.out.print("------|");
        }
       
    } System.out.println();
    
    for(int  y=0; y < h ; y++){

        System.out.print("|");
        for (int x=0 ; x < w; x++){
            
            String symbole = grid[x][y].getSymbol() ;
            System.out.print(  symbole + " | ");
        }
        System.out.println(); 
        // We put a separating line between the lines but not the last one.
        if (y<h-1){
            System.out.print("|");
            for (int x = 0; x < w; x++) {
                if(x==0){
                System.out.print("_____|");}
                else {
                    System.out.print("______|");}
            }
            System.out.println();
        }
        
    }
    for (int x = 0; x < w; x++) {
            if(x==0){
                System.out.print("|-----|");
            }
            else {
                System.out.print("------|");
            }
        }
        System.out.println();
        System.out.println("\n Légende des tuiles :");

   
}

/** creates a new board for the game randomly */

private void createBoard(){
    this.placeInitialeTiles();
    this.placeNeighboorEarthTiles();
    
}

/** return true if the given position is the sea, false otherwise
 * @param Position the position */
public boolean isEmpty(Position pos) {

    if (pos.getX() >= 0 && pos.getX() < this.width && pos.getY() >= 0 && pos.getY() < this.height) {
        if (this.grid[pos.getX()][pos.getY()] instanceof Sea) {
            return true;
        }
    }
    return false;
}

/** return true if the tile have a neighbor, false otherwise 
 * @param int x
 * @param int y
 * @return true if the tile have a neighbor 
*/
public boolean haveNeighbor(Position pos){
    for (Direction d : Direction.values()){
        Position neighbor = pos.next(d);

        // we check that the neighbor does not exceed the limits of the board
        if (neighbor.getX() >= 0 && neighbor.getX() < this.width &&
            neighbor.getY() >= 0 && neighbor.getY() < this.height) {
            if (!isEmpty(neighbor)){
                return true;
            }
        }
    }
    return false;
}


/** put the tile on the given position+
 * @param Tuile t
 * @param Position pos
 */
public void put(Tuile t, Position pos){
    this.grid[pos.getX()][pos.getY()]= t;

}


/* return a random position on the board 
 * @return a position on the board
 * */
private Position randomPosition(){
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
    return (int)(this.getHeight() * this.getWidth() * 1.0 / 3);
}

/** places half of the 1/6 number of tiles randomly on the board */
private void placeInitialeTiles(){
    int nbTuile= this.tileNumber();
    int nbTuileInit = nbTuile/2;
    for (int i=0 ;i< nbTuileInit;i++){
        Position pos = this.randomPosition();
        Tuile tuile = this.randomTuile();
        put(tuile, pos);
    }
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
 * for the ENTIRETY  of the board it also returns the number of remaining tiles to place
 * @return nbre of remaining tiles to place
 */
private int placeNeighboorEarthTiles(){
    int nbredetuilerestante= this.tileNumber() / 2;
    for (int x=0; x<this.getWidth(); x++){
        for (int y=0; y<this.getHeight(); y++){
            Position currentPos= new Position(x, y);
            if (!this.isEmpty(currentPos) && !this.haveNeighbor(currentPos)){
                ArrayList<Position> Npos= this.haveEmptyNeighboorList(currentPos);
                Random choiceRandom = new Random();
                int random= choiceRandom.nextInt(Npos.size());
                this.put( this.randomTuile(), Npos.get(random));
                nbredetuilerestante-=1;
            }
        }
    }
    return nbredetuilerestante;

}
/* return a random tile of a random type
 * @return a random tile 
*/
private Tuile randomTuile(){
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