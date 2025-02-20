package game.tuile;
import java.util.HashMap;

import game.Player;

public class Army extends Building{

    protected int nbWarriors  ;
    private static final String SYMBOL = "a";        //"  "
    private static int nbWarriorsMax = 5;

    /** create an army on a given tile
    * @param tuile the tile where we build the building
    * @param nbWarriors the number of warriors
    */
    public Army(Earth tuile, int nbWarriors){
        super(tuile); 
        if (this.nbWarriors > nbWarriorsMax){
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
     * @return boolean
     */
    public boolean canBeCamp(Player player){
        // on verifie si l'armee a plus de 5 guerriers ou si le joueur a suffisament de ressource
        return this.getNbWarriors() >= 5 || player.hasEnoughRessources(this);
    }


    /** adds a given number of warriors, 
    * @param newWarriors the number of warriors to add
    * @param player the player who adds the warriors
    */
    public void addWarriors(int newWarriors, Player player) {
        if (player.getWarriors() >= newWarriors) {
        this.nbWarriors += newWarriors;
        // haha stella la blague des guerriers qui se perdent au milieu du chemin je leur ai trouvé une solution
        // ici on reduit le nb de guerriers du stock du joueur 
        player.removeWarriors(newWarriors);
    } else {
        System.out.println("Not enough warriors in stock");
    }
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
