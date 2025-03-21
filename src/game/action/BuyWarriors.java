package game.action;
import game.PlayerAres;
import game.tuile.Ressource;
import game.NoMoreRessourcesException;

/**
 * This class is used to buy warriors
 * it extends ActionManager and implements Action<PlayerAres>
 */
<<<<<<< HEAD
public class BuyWarriors<T extends PlayerAres> extends ActionManager implements Action<T> {

    public BuyWarriors(T player) {
=======
public class BuyWarriors extends ActionManager implements Action<PlayerAres> {


    public BuyWarriors(PlayerAres player){
>>>>>>> 76a233f8ecf1b06fe069cfd6aa07cd432d2dfba5
        super(player);
        this.cost.put(Ressource.WEALTH, 2);
        this.cost.put(Ressource.SHEEP, 2);
        this.cost.put(Ressource.ORE, 1);
    }

    /**
     * Buy 5 warriors if the player has enough resources
     * @param player the player who makes the action    
     */
    public void act(PlayerAres player) throws NoMoreRessourcesException {

        // Check if player has enough resources to buy 5 warriors
        if (!this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough resources to add 5 warriors");
        }
        this.removeRessources();
        
        // Add 5 warriors to the player's warriors stock
        player.addWarriors(5);
    }
}

