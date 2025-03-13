package game.action;
import game.PlayerAres;
import game.tuile.Ressource;
import game.NoMoreRessourcesException;

public class BuyWarriors extends ActionManager implements Action<PlayerAres> {


    public BuyWarriors(){
        super(player);
        this.cost.put(Ressource.WEALTH, 2);
        this.cost.put(Ressource.SHEEP, 2);
        this.cost.put(Ressource.ORE, 1);
    }

    public void act(PlayerAres player) throws NoMoreRessourcesException {

        // we check if player has enough ressource to buy 5 warriors
        if (! this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough ressources to add 5 warriors");
        }
        this.removeRessources();
        
        // add 5 warriors to the player's  warriors stock
        player.addWarriors(5); 
    }
}

