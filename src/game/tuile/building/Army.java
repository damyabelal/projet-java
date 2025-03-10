package game.tuile.building;

import java.util.*;
import game.Board;
import game.CantBuildException;
import game.Player;
import game.PlayerAres;
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
    * @throws CantBuildException 
    */

    public Army(Earth tuile, int nbWarriors, Player player) throws CantBuildException{
        super(tuile, player); 
        if (nbWarriors > nbWarriorsMax){
            throw new CantBuildException("You cant build Army with nbWarriors  > 5");
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
    
    public String getName(){
        return "Army";
    }
   
    /**
     * return true if the army can be a camp
     * @param player the person who play
     * @return boolean
     */
    public boolean canBeCamp(PlayerAres player){
        return this.getNbWarriors() >= 5 || player.hasEnoughRessources(new Camp(tuile, nbWarriors, player));
    }
    
    /**
     * 
     * @param earth
     * @param player
     * @return
     */
    public boolean canBuildArmy(Earth earth, PlayerAres player) {
        Set<Earth> visited = new HashSet<>();
        visited.add(earth);
        int cptPort = 0;
        int cptBuild =0;

        List<Earth> island = Board.exploreIsland(earth, visited);

        for (Earth tuile : island){
            if(tuile.haveBuild()){
                cptBuild++;
            }
            if(tuile.getBuilding() instanceof Port){
                cptPort++;
            }

        }
        return cptBuild >=2 && cptPort >= 1 && player.hasEnoughRessources(earth.getBuilding());
  
        
    }

    
    /** evolves the army into a camp
    * @param player the player who wants to upgrade the army
    * @return the new camp if the army can be upgraded null otherwise
    */
    public Camp upGradeToCamp(PlayerAres player) {
    if (this.canBeCamp(player)) {
        Camp camp = new Camp(this.getTuile(), this.getNbWarriors(), this.getPlayer());
        System.out.println("Army evolved into a camp");
        return camp;
    } else {
        System.out.println("Not enough warriors or not enough resources");
    }
    return null;
}




}

