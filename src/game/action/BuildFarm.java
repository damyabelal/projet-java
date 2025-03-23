package game.action;

import java.io.IOException;

import game.*;
import game.tuile.*;
import game.tuile.building.*;
import game.util.Position;
import game.listchooser.RandomListChooser;

/*
 * This class is used to build a farm on a tile
 * It extends ActionManager and implements Action<PlayerDemeter>
 */
public class BuildFarm extends ActionManager implements Action<PlayerDemeter> {

    private Board board; 
    public static RandomListChooser<Position> lc;

    public BuildFarm(Board board, PlayerDemeter player) {
        super(player);  
        this.board= board;
        this.cost.put(Ressource.WOOD, 1);  
        this.cost.put(Ressource.ORE, 1);   
        lc = new RandomListChooser<>();
    }

    public Position askCoordinate() throws IOException {
        return lc.chooseCoordinate("Where do you want to build a Farm?", this.board);
    }

    /**
     * This method is used to build a farm on a tile
     * @param player the player who wants to build the farm
     * @throws NoMoreRessourcesException if the player doesn't have enough ressources to build the farm
     * @throws IOException 
    */
    public void act(PlayerDemeter player) throws NoMoreRessourcesException, IOException {
        Position choosenPosition= askCoordinate();
        Earth tile= (Earth) this.board.getTile(choosenPosition); 
        //check if player has enough ressources to buy a farm
        if (! this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough ressources to build the farm");
        }
        // if he can we remove the cost from the player
        this.removeRessources();

        // we build the farm on the tile he wants
        Farm farm = new Farm((Earth) tile, player);
        // we set the farm on the tile
        tile.setBuilding(farm);
        // we add the tile to the player's tile list
        player.addFarm(farm);

        System.out.println(player.getName() +": "+player.getResources()+ " build a farm on position "+ choosenPosition);
    }

}

   
