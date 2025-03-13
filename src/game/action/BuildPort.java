package game.action;
import java.util.HashMap;
import game.NoMoreRessourcesException;
import game.Player;
import game.tuile.Earth;
import game.tuile.building.Port;
import game.tuile.Ressource;

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

       new Port(tuile , player);


    }
    
}
