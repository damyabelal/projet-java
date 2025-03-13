package game.action;

import game.NoMoreRessourcesException;
import game.Player;
import game.tuile.Earth;
import game.tuile.building.Port;

public class BuildPort implements Action<Player>{

    private Earth tuile;// the tile that we will place the port on


    public BuildPort(Earth tile){
        this.tuile=tile ;

    }
    @Override
    public void act(Player player) throws NoMoreRessourcesException {
       // si le joueurs a les ressources nécessaires alors on construit un port  

       new Port(tuile , player);


    }
    
}
