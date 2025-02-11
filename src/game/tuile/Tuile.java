package game.tuile;
import game.util.*;

public abstract class Tuile {
    public String symbol ; /// symbol of the tile
   
    private Position pos; // to be able to get the position of the tile


    /**
     * Constructor for Tuile.
     *
     * @param symbol The symbol representing this tile.
     */    
    public Tuile(String symbol) {
        this.symbol =symbol ;

    }
    /**
     * Gets the symbol of the tile.
     *
     * @return the symbol of the tile.
     */
    public String getSymbol() {
        return symbol;
    }   


    public Position getPosition(){
        return this.pos;

    }







}
