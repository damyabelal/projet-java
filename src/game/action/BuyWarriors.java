package game.action;
import game.PlayerAres;
import game.tuile.Ressource;
import game.NoMoreRessourcesException;
import game.Player;

/**
 * This class is used to buy warriors
 * it extends ActionManager and implements Action<PlayerAres>
 */
public class BuyWarriors <T extends Player > extends ActionManager implements Action<T> {


    public BuyWarriors(T player){
        super(player);
        this.cost.put(Ressource.WEALTH, 2);
        this.cost.put(Ressource.SHEEP, 2);
        this.cost.put(Ressource.ORE, 1);
    }
    /**
     * buy 5 warriors if the player have enough ressources
     * @param player the player who make the action    
     */
    public void act(T player) throws NoMoreRessourcesException {

        // we check if player has enough ressource to buy 5 warriors
        if (! this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough ressources to add 5 warriors");
        }
        this.removeRessources();
        
        // add 5 warriors to the player's  warriors stock
        player.addWarriors(5); 
    }
}

