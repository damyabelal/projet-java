package game.action;

import java.io.IOException;
import game.Board;
import game.NoMoreRessourcesException;
import game.Player;
import game.listchooser.RandomListChooser;
import game.tuile.*;
import game.tuile.building.*;
import game.util.*;

/*
 * 
 * This class is used to build a port on a tile
 * It extends ActionManager and implements Action<Player>
 */
public class BuildPort <T extends Player > extends ActionManager implements Action<T>{

    private Board board; 
    public static RandomListChooser<Position> lc;

    public BuildPort(T player, Board board){
        super(player); 
        this.board= board; 
        this.cost.put(Ressource.WOOD,1);
        this.cost.put(Ressource.SHEEP,2);
        lc= new RandomListChooser<>(); 
    }

    public Position askCoordinate() throws IOException {
        return lc.chooseCoordinate("Where do you want to build a Port?", this.board);
    }

    /**
     * return true if the port can be placed at the given position , false otherwise
     * @param pos the position where we want to place it on 
     * @param board the board of the game
     * @return true if the port can be placed at the given position , false otherwise
     */
    public boolean canPlacePort(Position pos, Board board) {
        if (!(board.getTile(pos) instanceof Earth)) {
            return false;
        }
        return board.nbSeaTiles(pos) >= 2;
   
    }
    
    @Override
    public void act(T player) throws NoMoreRessourcesException, IOException {
        Position choosenPosition= askCoordinate();
        Earth tile= (Earth) this.board.getTile(choosenPosition);


        if (! this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough ressources to build the farm");
        }
        if(!canPlacePort(choosenPosition, board)){
            throw new NoMoreRessourcesException("il faut avoir au moins deux 2 tuiles mer autour");
        }

        this.removeRessources();
        Port port = new Port((Earth) tile, player);
        tile.setBuilding(port);
        player.addPort(port);

        System.out.println(player.getName() +": "+player.getResources()+ " build a port on position "+ choosenPosition);
    
    }
    
}
