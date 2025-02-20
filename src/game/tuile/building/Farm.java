package game.tuile.building;
import java.util.HashMap;
import game.Player;
import game.tuile.Earth;
import game.tuile.Ressource;

/*
 * A class to create a Farm
 */
public class Farm extends Building{

    private static final String SYMBOL= "f";

    /** Build a Farm
     * @param tuile the tile where we build the farm
     */
    public Farm(Earth tuile) {
        super(tuile);
        this.dimension = 1;
        this.cost = new HashMap<>();
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.ORE, 1);
        this.symbol= SYMBOL; 
    }

    /** 
     * returns the name of the building
     */
    public String getName(){
        return "Farm";
    }
    

    /**
     * return true if the farm can be a exploitation
     * @param player the person who play
     * @return boolean
     */
    public boolean canBeExploitation(Player player){
        return player.hasEnoughRessources(this);
    }
    
    /** evolve the farm into a exploitation
    * @param player the player who wants to upgrade the farm
    * @return the new exploitation if the farm can be upgraded null otherwise
    */
    public Exploitation upGradeToExploitation(Player player) {
        if (this.canBeExploitation(player)) {
            Exploitation exploitation = new Exploitation(this.getTuile());
            System.out.println("Farm evolve into exploitation");
            return exploitation;
        } else {
            System.out.println("Not enough resources");
        }
        return null;
    }
}
