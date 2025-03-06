package game.action;
import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Ressource;

public class BuyThief implements Action<PlayerDemeter>{

    /*
     * buy a thief if the player have enough ressources
     * @param the player who make the action
     */
    public void act(PlayerDemeter player) throws NoMoreRessourcesException{
        int costWood = 1;
        int costOre= 1;
        int costWealth = 1;
        if (player.getRessourceAmount(Ressource.ORE) < costOre ||
            player.getRessourceAmount(Ressource.WOOD) < costWood ||
            player.getRessourceAmount(Ressource.WEALTH) < costWealth){
            throw new NoMoreRessourcesException("Not enough ressources to buy a secret weapon");
        }
        player.removeRessource(Ressource.ORE, costOre);
        player.removeRessource(Ressource.WOOD, costWood);
        player.addThiefs(1);
    }
    
}
