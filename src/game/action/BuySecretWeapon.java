package game.action;

import game.PlayerAres;
import game.tuile.Ressource;
import game.NoMoreRessourcesException;

public class BuySecretWeapon extends ActionManager implements Action<PlayerAres>{


    public BuySecretWeapon(){
        super(player);
        this.cost.put(Ressource.ORE, 1);
        this.cost.put(Ressource.WOOD, 1);
    }

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