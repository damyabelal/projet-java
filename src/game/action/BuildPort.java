package game.action;
import java.io.IOException;
import java.util.HashMap;

import game.Board;
import game.NoMoreRessourcesException;
import game.Player;
import game.tuile.Earth;
import game.tuile.building.Port;
import game.util.Position;
import listchooser.ListChooser;
import game.tuile.Ressource;
import game.tuile.Tuile;

/*
 * 
 * This class is used to build a port on a tile
 * It extends ActionManager and implements Action<Player>
 */
public class BuildPort extends ActionManager implements Action<Player>{

    private Earth tuile;// the tile that we will place the port on
    protected HashMap<Ressource,Integer> cost;
    private Board board; 
    public static ListChooser<Position> lc;

    public BuildPort(Player player, Board board){
        super(player); 
        this.board= board; 
        this.cost=new HashMap<>(){ { put(Ressource.WOOD,1);
        put(Ressource.SHEEP,2);
        }};
        }

    public Position askCoordinate() throws IOException {
        return lc.chooseCoordinate("Where do you want to build a Port?", this.board);
    }
    
    @Override
<<<<<<< HEAD
    public void act(Player player) throws NoMoreRessourcesException {
       // si le joueurs a les ressources nécessaires alors on construit un port
       Port port =new Port(null,player)  ;
       // if the player has enough ressources to build a port then a port for this player will be build
        if (player.hasEnoughRessources(port)){
       new Port(tuile , player);
        }


        else{
            throw new NoMoreRessourcesException("Not enough ressources to build a port.");

        }

=======
    public void act(Player player) throws NoMoreRessourcesException, IOException {
        Position choosenPosition= askCoordinate();
        Tuile tile= this.board.getTile(choosenPosition);
        if (! this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough ressources to build the farm");
        }
        this.removeRessources();
        Port port = new Port((Earth) tile, player);
        tuile.setBuilding(port);
        player.addPort(port);
>>>>>>> 5015482bc6f3f69e79ab15d14c027d8d41ca4342
    }
    
}
