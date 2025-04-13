package game.action.actionDemeter;

import game.*;
import game.action.Action;
import game.action.ActionManager;
import game.tuile.*;
import game.tuile.building.*;
import game.util.InvalidChoiceException;
import game.util.NoMoreRessourcesException;
import game.util.Position;
import game.listchooser.ListChooser;

/*
 * This class is used to build a farm on a tile
 * It extends ActionManager and implements Action<PlayerDemeter>
 */
public class BuildFarm extends ActionManager <PlayerDemeter> implements Action<PlayerDemeter> {

    private Board board; 
    public ListChooser<Earth> lc;

    public BuildFarm(Board board, PlayerDemeter player, ListChooser<Earth> lc ) {
        super(player);  
        this.board= board;
        this.cost.put(Ressource.WOOD, 1);  
        this.cost.put(Ressource.ORE, 1);   
        this.lc = lc;
    }

    public Earth askCoordinate() throws InvalidChoiceException {
        return lc.choose("Where do you want to build a Farm?", this.board.buildableTiles());
    }

    /**
     * builds a farm on a tile for the given player
     * @param player the player who wants to build the farm
     * @throws NoMoreRessourcesException if the player doesn't have enough ressources to build the farm
     * @throws InvalidChoiceException
    */
    public void act(PlayerDemeter player) throws NoMoreRessourcesException, InvalidChoiceException {
        Position choosenPosition= askCoordinate().getPosition();
        Earth tile= (Earth) this.board.getTile(choosenPosition); 
        int numberofBuilding= player.getFarms().size() + player.getExploitations().size() +player.getPorts().size(); 
        //checks if player has enough ressources to build a farm
        if ((!this.hasEnoughRessources()) && ( numberofBuilding > 2 )) {
            throw new NoMoreRessourcesException("Not enough ressources to build the farm\n cost: "+this.cost);
        }
        // if a farm is built, removes the farm's cost from the player's inventory
        if (numberofBuilding>2){
            this.removeRessources();
        }

        // builds the farm on the tile the player wants to build on
        Farm farm = new Farm((Earth) tile, player);
        // sets the farm on the tile
        tile.setBuilding(farm);
        // adds the built tile to the player's list of tiles
        player.addFarm(farm);
        
        player.addPoints(1); 
        
        System.out.println(player.getName() +": "+player.getResources()+ " build a farm on position "+ choosenPosition);
    }

}

   
