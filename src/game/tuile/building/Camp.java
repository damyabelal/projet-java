package game.tuile.building;

import java.util.HashMap;

import game.tuile.Earth;
import game.tuile.Ressource;

/*
 * A class to create a Camp
 */
public class Camp extends Army{
    
    private static final String SYMBOL = "c";     //"  "

    /**
     * creates a Camp on the given tile with a set number of warriors
     *
     * @param tuile The tile where the Camp is placed
     * @param nbWarriors The number of warriors in the Camp
     */
    public Camp(Earth tuile, int nbWarriors){
        super(tuile, nbWarriors);
        this.resourceMultiplier = 2;
        this.symbol = SYMBOL;
        this.cost = new HashMap<>();
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.ORE, 3);

    }

    /**
     * returns the number of additional warriors that were added beyond the normal Army limit
     *
     * @return additional warriors 
     */
    public int getAdditionalWarriors() {
        return this.getNbWarriors() - 5;
    }

    /** 
     * returns the name of the building
     */
    public String getName(){
        return "Camp";
    }

}