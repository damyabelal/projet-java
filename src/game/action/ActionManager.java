package game.action;
import java.util.HashMap;

import game.NoMoreRessourcesException;
import game.Player;
import game.tuile.Ressource;

public abstract class ActionManager {

    protected HashMap<Ressource, Integer> cost;
    protected Player player;
    
    /**
     * create an ActionManager
     * @param player
     */
        public ActionManager(Player player){
            this.cost = new HashMap<>();
            this.player = player;
    }

    /**
     * check if the the player have at least the cost in his inventary
     * @return true if he have enough, false otherwise
     */
    protected boolean hasEnoughRessources(){
        for (Ressource ressource : cost.keySet()){
            if (player.getRessourceAmount(ressource) < cost.get(ressource)){
                return false;
            }
        }
        return true;
    }

    /**
     * remove the ressource from the player inventary
     * @throws NoMoreRessourcesException
     */
    protected void removeRessources() throws NoMoreRessourcesException{
        for (Ressource ressource : cost.keySet()){
            player.removeRessource(ressource, cost.get(ressource));
        }
    }




}