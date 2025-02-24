package game.tuile;
import java.util.*;
import game.util.*;


/**
 * A class to create a Building 
 */
public abstract class Building{
    public static final String SYMBOL = " B ";
    protected String symbol;
    protected int dimension;
    protected Earth tuile ; 
    protected HashMap<Integer, Ressource> cost;
   
   
   /** create a building on a given tile
    * @param tuile the tile where we build the building
    */
    public Building(Earth tuile){
        this.symbol = SYMBOL;
        this.dimension = 1;
        this.tuile = tuile ;
        this.cost = new HashMap<>();
    }

    /** return the tile where the build is
     * @return the tile
     */
    public Earth getTuile(){
        return this.tuile;
    }

    /** return the dimension of this building
     * @return the dimension
     */
    public int getDimension(){
     return this.dimension;
    }

    /** return the symbol of this building
     * @return the symbol
     */
    public String getSymbol(){
        return this.symbol;
    }

    /** return the cost of the building
     * @return the cost 
     */
    public HashMap<Integer, Ressource> getCost(){
        return this.cost;
    }

    /** return the ressources
     */
    public Ressource getRessourceBis(){
        return this.tuile.getRessource();
    }

    public boolean canBuild(){

    //TO DO
    }

/** returns the position of this building which is also the position of the tile the building is placed on
 * @return the position on which the building was placed on */
    public Position getPosition(){
        return this.tuile.getPosition();

    }

    
    





}