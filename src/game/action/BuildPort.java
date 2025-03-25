package game.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.NoMoreRessourcesException;
import game.Player;
import game.listchooser.RandomListChooser;
import game.tuile.*;
import game.tuile.building.*;
import game.util.*;

/*
 * 
 * builds a port on a tile
 * It extends ActionManager and implements Action<Player>
 */
public class BuildPort <T extends Player > extends ActionManager implements Action<T>{

    private Board board; 
    public static RandomListChooser<Earth> lc;

    public BuildPort(T player, Board board){
        super(player); 
        this.board= board; 
        this.cost.put(Ressource.WOOD,1);
        this.cost.put(Ressource.SHEEP,2);
        lc= new RandomListChooser<>(); 
    }

    public Earth askCoordinate() throws IOException {
        return lc.choose("Where do you want to build a Port?",this.board.coastalTiles());
    }

    /**
     * returns true if the port can be placed on the given position , false otherwise
     * @param pos the position where we want to place the port on 
     * @param board the board of the game
     * @return true if the port can be placed on the given position , false otherwise
     */
    public boolean canPlacePort(Position pos, Board board) {
        if (!(board.getTile(pos) instanceof Earth)) {
            return false;
        }
        return board.nbSeaTiles(pos) >= 2;
   
    }
    


    /**
     */
    @Override
    public void act(T player) throws NoMoreRessourcesException, IOException {
        Earth choosenTile= askCoordinate();

        if (! this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough ressources to build a farm.");
        }
        if(!canPlacePort(choosenTile.getPosition(), board)){
            throw new NoMoreRessourcesException("There should be at least two neighboring sea tiles.");
        }

        this.removeRessources();
        Port port = new Port(choosenTile, player);
        choosenTile.setBuilding(port);
        player.addPort(port);

        System.out.println(player.getName() +": "+player.getResources()+ " build a port on the position "+ choosenTile.getPosition());
    
    }
    
}
