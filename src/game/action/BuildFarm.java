package game.action;

import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Farm;

public class BuildFarm extends ActionManager implements Action<PlayerDemeter> {

    private Earth tuile;

    public BuildFarm(PlayerDemeter player, Earth tuile) {
        super(player);  
        this.tuile = tuile;
        this.cost.put(Ressource.WOOD, 1);  
        this.cost.put(Ressource.ORE, 1);   
    }

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
        player.addTile(tuile);
    }

}

   
