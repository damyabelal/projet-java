package game.action;

import game.PlayerAres;
import game.tuile.Ressource;
import game.NoMoreRessourcesException;

public class BuySecretWeapon implements Action<PlayerAres>{

    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException {

        // the cost of buying an secret weapon 
        int costOre = 1;
        int costWood = 1;

        // we check if the player hqs enough ressources to buy a secret weapon
        if (player.getRessourceAmount(Ressource.ORE) < costOre ||
            player.getRessourceAmount(Ressource.WOOD) < costWood ){
            throw new NoMoreRessourcesException("Not enough ressources to buy a secret weapon");
        }
        
        // remove the ressources from the player
        player.removeRessource(Ressource.ORE, costOre);
        player.removeRessource(Ressource.WOOD, costWood);

        // add a secret weapon to the player 
        player.addSecretWeapon();
    }


}