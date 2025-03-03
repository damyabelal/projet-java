package game.action;

import game.Player;
import game.PlayerAres;
import game.tuile.Ressource;
import game.NoMoreRessourcesException;

public class AddWarriors implements Action {

    @Override
    public void act(Player player) throws NoMoreRessourcesException {

        // the cost of adding 5 warriors :
        int costWealth = 2;
        int costSheep = 2;
        int costOre = 1;

        // we check if player has enough ressource to buy 5 warriors
        if (player.getRessourceAmount(Ressource.WEALTH) < costWealth ||
            player.getRessourceAmount(Ressource.SHEEP) < costSheep ||
            player.getRessourceAmount(Ressource.ORE) < costOre) {
            throw new NoMoreRessourcesException("Not enough ressources to add 5 warriors");
        }
        // remove the ressources from the player
        player.removeRessource(Ressource.WEALTH, costWealth);
        player.removeRessource(Ressource.SHEEP, costSheep);
        player.removeRessource(Ressource.ORE, costOre);
        // add 5 warriors to the player's  warriors stock
        player.addWarriors(5); 
    }
}
