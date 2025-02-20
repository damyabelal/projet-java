package game.tuile;
import game.Board;
import game.util.*;
import java.util.HashMap;

public class Port extends Building{
    
    private static final String SYMBOL = "p";        //" ⚓ "

    public Port(Earth tuile){
        super(tuile);
        this.symbol = SYMBOL;
        this.cost= new HashMap<>(); 
        this.cost.put(Ressource.WOOD,1);
        this.cost.put(Ressource.SHEEP,2);
    }
    /**
     * return the number of sea tiles around the port
     * @param pos
     * @param board
     * @return int the number of sea tiles around the port
     */
    private int nbSeaTiles(Position pos, Board board) {
        int nbSeaTiles = 0;
        for (Direction d : Direction.values()) {
            Position neighbor = pos.next(d);
            if (board.isValidPosition(neighbor)) {
                Tuile neighborTile = board.getTile(neighbor);
                if (neighborTile instanceof Sea) {
                    nbSeaTiles += 1;
                }
            }
        }
        return nbSeaTiles;
    }

    /**
     * return true if the port can be placed at the given position , false otherwise
     * @param pos
     * @param board
     * @return boolean true if the port can be placed at the given position , false otherwise
     */
    public boolean canPlacePort(Position pos, Board board) {
        if (!(board.getTile(pos) instanceof Earth)) {
            return false;
        }
        for (Direction d : Direction.values()) {
            Position neighbor = pos.next(d);
            Tuile neighborTile = board.getTile(neighbor);
            if (neighborTile instanceof Sea && nbSeaTiles(pos, board) >= 2) {
                return true;
            }
        }
        return false;
    }

    /** 
     * returns the name of the building
     */
    public String getName(){
        return "Port";
    }


}
   




    
    

    
