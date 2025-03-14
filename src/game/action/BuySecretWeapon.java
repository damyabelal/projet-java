package game.action;

import game.PlayerAres;
import game.tuile.Ressource;
import game.NoMoreRessourcesException;

/*
 * This class is used to buy a secret weapon
 * it extends ActionManager and implements Action<PlayerAres>
 */
public class BuySecretWeapon extends ActionManager implements Action<PlayerAres>{


    public BuySecretWeapon(){
        super(player);
        this.cost.put(Ressource.ORE, 1);
        this.cost.put(Ressource.WOOD, 1);
    }
    /**
     * buy a secret weapon if the player have enough ressources
     * @param the player who make the action
     * @throws NoMoreRessourcesException if the player doesn't have enough ressources to
     * buy
     */
    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException {

        // we check if the player has enough ressources to buy a secret weapon
        if (! this.hasEnoughRessources() ){
            throw new NoMoreRessourcesException("Not enough ressources to buy a secret weapon");
        }
        this.removeRessources();
        // we add a secret weapon to the player's weapons stock
        player.addSecretWeapon();
    }


}