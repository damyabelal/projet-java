package game.tuile;

import java.util.HashMap;

public class Camp extends Army{
    
    private static final String SYMBOL = " -> ";     //" 🏕️ "

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
        this.cost.put(2, Ressource.WOOD);
        this.cost.put(3, Ressource.ORE);

    }
}