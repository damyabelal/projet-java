package game.action;
import java.util.HashMap;
import game.NoMoreRessourcesException;
import game.Player;
import game.tuile.Earth;
import game.tuile.building.Port;
import game.tuile.Ressource;

/*
 * 
 * This class is used to build a port on a tile
 * It extends ActionManager and implements Action<Player>
 */
public class BuildPort implements Action<Player>{

    private Earth tuile;// the tile that we will place the port on
    protected HashMap<Ressource,Integer> cost;

    public BuildPort(Earth tile){
        this.tuile=tile;
        this.cost=new HashMap<>(){ { put(Ressource.WOOD,1);
        put(Ressource.SHEEP,2);
        }};
        }
    
    @Override
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

    }
    
}
