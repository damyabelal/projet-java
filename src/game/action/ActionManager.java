package game.action;
import java.util.HashMap;

import game.NoMoreRessourcesException;
import game.Player;
import game.tuile.Ressource;

public abstract class ActionManager {

    protected HashMap<Ressource, Integer> cost;
    protected static Player player;
    
        public ActionManager(Player player){
            this.cost = new HashMap<>();
            ActionManager.player = player;
    }

    protected boolean hasEnoughRessources(){
        for (Ressource ressource : cost.keySet()){
            if (player.getRessourceAmount(ressource) < cost.get(ressource)){
                return false;
            }
        }
        return true;
    }

    protected void removeRessources() throws NoMoreRessourcesException{
        for (Ressource ressource : cost.keySet()){
            player.removeRessource(ressource, cost.get(ressource));
        }
    }




}