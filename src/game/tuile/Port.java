package game.tuile;

import java.util.HashMap;

public class Port extends Building{
    
    private static final String SYMBOL = " -> ";        //" ⚓ "

    public Port(Earth tuile){
        super(tuile);
        this.symbol = SYMBOL;
        this.cost= new HashMap<>(); 
        this.cost.put(Ressource.WOOD,1);
        this.cost.put(Ressource.SHEEP,2);
    }

    /** 
     * returns the name of the building
     */
    public String getName(){
        return "Port";
    }
    

    
}