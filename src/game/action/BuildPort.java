package game.action;
import java.io.IOException;
import java.util.HashMap;

import game.Board;
import game.NoMoreRessourcesException;
import game.Player;
import game.listchooser.RandomListChooser;
import game.tuile.Earth;
import game.tuile.building.Port;
import game.util.Direction;
import game.util.Position;
import game.tuile.Ressource;
import game.tuile.Sea;
import game.tuile.Tuile;

/*
 * 
 * This class is used to build a port on a tile
 * It extends ActionManager and implements Action<Player>
 */
public class BuildPort <T extends Player > extends ActionManager implements Action<T>{

    protected HashMap<Ressource,Integer> cost;
    private Board board; 
    public static RandomListChooser<Position> lc;

    public BuildPort(T player, Board board){
        super(player); 
        this.board= board; 
        this.cost=new HashMap<>(){ { put(Ressource.WOOD,1);
        put(Ressource.SHEEP,2);
        }};
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
        for (Direction d : Direction.values()) {
            Position neighbor = pos.next(d);
            Tuile neighborTile = board.getTile(neighbor);
            if (neighborTile instanceof Sea && board.nbSeaTiles(pos) >= 2) {
                return true;
            }
        }
        return false;
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
    }
    
}
