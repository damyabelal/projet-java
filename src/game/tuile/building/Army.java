package game.tuile.building;
import java.util.HashMap;

import game.NoMoreRessourcesException;
import game.Player;
import game.tuile.Earth;
import game.tuile.Ressource;

/*
 * A class to create a Army
 */
public class Army extends Building{

    protected int nbWarriors  ; //the number of warriors in the army
    private static final String SYMBOL = "a";
    private static int nbWarriorsMax = 5; //the max number of warriors in a army 

    /** create an army on a given tile
    * @param tuile the tile where we build the building
    * @param nbWarriors the number of warriors
    */
    public Army(Earth tuile, int nbWarriors){
        super(tuile); 
        if (nbWarriors > nbWarriorsMax){
            this.nbWarriors = nbWarriorsMax;
        }
        else{
            this.nbWarriors = nbWarriors;
        }
        this.dimension = this.nbWarriors;
        this.symbol = SYMBOL;
        this.cost = new HashMap<>();
        this.cost.put(Ressource.WOOD,1);
        this.cost.put(Ressource.SHEEP, 1);
        this.cost.put(Ressource.WEALTH, 1);

    }

    /** return the number of warriors
     * @return the number of warriors
     */
    public int getNbWarriors(){
        return this.nbWarriors;
    }
    
     /** 
     * returns the name of the building
     */
    public String getName(){
        return "Army";
    }

    /**
     * return true if the army can be a camp
     * @param player the person who play
     * @return boolean
     */
    public boolean canBeCamp(Player player){
        return this.getNbWarriors() >= 5 || player.hasEnoughRessources(new Camp(tuile, nbWarriors));
    }


    /** adds a given number of warriors, 
    * @param newWarriors the number of warriors to add
    * @param player the player who adds the warriors
    */
    public void addWarriors(int newWarriors, Player player) throws NoMoreRessourcesException {
        if (player.getWarriors() < newWarriors) { 
            throw new NoMoreRessourcesException("Not enough warriors in stock");
        }
        this.nbWarriors += newWarriors;
        player.removeWarriors(newWarriors);
    }
    

    /** evolves the army into a camp
    * @param player the player who wants to upgrade the army
    * @return the new camp if the army can be upgraded null otherwise
    */
    public Camp upGradeToCamp(Player player) {
    if (this.canBeCamp(player)) {
        Camp camp = new Camp(this.getTuile(), this.getNbWarriors());
        System.out.println("Army evolved into a camp");
        return camp;
    } else {
        System.out.println("Not enough warriors or not enough resources");
    }
    return null;
}

}

