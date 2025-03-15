package game.tuile.building;

import game.CantBuildException;

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
    protected PlayerAres player;

    /** create an army on a given tile
    * @param tuile the tile where we build the building
    * @param nbWarriors the number of warriors
    * @throws CantBuildException 
    */

    public Army(Earth tuile, int nbWarriors, PlayerAres player) throws CantBuildException{
        super(tuile, player); 
        if (nbWarriors > nbWarriorsMax){
            throw new CantBuildException("You cant build Army with nbWarriors  > 5");
        }
        else{
            this.nbWarriors = nbWarriors;
        }
        this.dimension = this.nbWarriors;
        this.symbol = SYMBOL;
        this.cost.put(Ressource.WOOD,1);
        this.cost.put(Ressource.SHEEP, 1);
        this.cost.put(Ressource.WEALTH, 1);
        this.player = player;

    }
    
    public String getName(){
        return "Army";
    }
    /**
     * return the number of warriors in the army
     * @return int
     */
    public int getNbWarriors(){
        return this.nbWarriors;
    }

    public void addWarriors(int nb){
        this.nbWarriors += nb;
    }
   
    /**
     * return true if the army can be a camp
     * @param player the person who play
     * @return boolean
     */
    public boolean canBeCamp(PlayerAres player){
        try {
            return this.getNbWarriors() >= 5 || player.hasEnoughRessources(new Camp(tuile, nbWarriors, player));
        } catch (CantBuildException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public PlayerAres getPlayerAres(){
        return this.player;
    }               
    
    /** evolves the army into a camp
    * @param player the player who wants to upgrade the army
    * @return the new camp if the army can be upgraded null otherwise
    */
    public Camp upGradeToCamp(PlayerAres player) {
    if (this.canBeCamp(player)){
        try{
            Camp camp = new Camp(this.getTuile(), this.getNbWarriors(), this.getPlayerAres());
            System.out.println("Army evolved into a camp");
            return camp;
        }catch(CantBuildException e){
            System.out.println("Failed ");

        }
    } else {
        System.out.println("Not enough warriors or not enough resources");
    }
    return null;
}




}

