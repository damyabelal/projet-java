package game.action;
import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Ressource;

/*
 * This class is used to buy a thief
 * it extends ActionManager and implements Action<PlayerDemeter> 
 */
public class BuyThief extends ActionManager implements Action<PlayerDemeter>{

    public BuyThief(PlayerDemeter player){
        super(player);
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.ORE, 1);
        this.cost.put(Ressource.WEALTH, 1);

    }

    /*
     * buy a thief if the player have enough ressources
     * @param the player who make the action
     */
    public void act(PlayerDemeter player) throws NoMoreRessourcesException{

        //check if player has enough ressources to buy a farm
        if (! this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough ressources to buy a thief");
        }
        // if he can we remove the cost from the player
        this.removeRessources();
        player.addThief();

        System.out.println(player.getName()+ " "+ player.getResources()+ " now have a thief");
        
    }
    
    
    
}
