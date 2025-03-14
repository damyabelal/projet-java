package game.action;

import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Farm;

/*
 * This class is used to build a farm on a tile
 * It extends ActionManager and implements Action<PlayerDemeter>
 */
public class BuildFarm extends ActionManager implements Action<PlayerDemeter> {

    private Earth tuile;

    public BuildFarm(PlayerDemeter player, Earth tuile) {
        super(player);  
        this.tuile = tuile;
        this.cost.put(Ressource.WOOD, 1);  
        this.cost.put(Ressource.ORE, 1);   
    }
    /**
     * This method is used to build a farm on a tile
     * @param player the player who wants to build the farm
     * @throws NoMoreRessourcesException if the player doesn't have enough ressources to build the farm
     */
    @Override
    public void act(PlayerDemeter player) throws NoMoreRessourcesException {
        //check if player has enough ressources to buy a farm
        if (! this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough ressources to build the farm");
        }
        // if he can we remove the cost from the player
        this.removeRessources();

        // we build the farm on the tile he wants
        Farm farm = new Farm(tuile, player);
        // we set the farm on the tile
        tuile.setBuilding(farm);
        // we add the tile to the player's tile list
        player.addFarm(farm);
    }

}

   
