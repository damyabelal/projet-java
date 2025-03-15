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

/*
 * 
 * This class is used to build a port on a tile
 * It extends ActionManager and implements Action<Player>
 */
public class BuildPort implements Action<Player>{

    private Earth tuile;// the tile that we will place the port on
    protected HashMap<Ressource,Integer> cost;
    private Board board; 
    public static ListChooser<Position> lc;

    public BuildPort(Earth tile, Board board){
        this.tuile=tile;
        this.board= board; 
        this.cost=new HashMap<>(){ { put(Ressource.WOOD,1);
        put(Ressource.SHEEP,2);
        }};
        }

    public Position askCoordinate() throws IOException {
        return lc.chooseCoordinate("Where do you want to build a Farm?", this.board);
    }
    
    @Override
    public void act(Player player) throws NoMoreRessourcesException {
       // si le joueurs a les ressources nécessaires alors on construit un port  

       new Port(tuile , player);


    }
    
}
