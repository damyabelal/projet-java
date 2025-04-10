package game.action;
import java.util.HashMap;

import game.NoMoreRessourcesException;
import game.Player;
import game.tuile.Ressource;

public abstract class ActionManager <T extends Player>{

    protected HashMap<Ressource, Integer> cost;
    protected T player;
    
    /**
     * creates an ActionManager for the given player
     * @param player
     */
        public ActionManager(T player){
            this.cost = new HashMap<>();
            this.player = player;
    }

    /**
     * checks if the the player has enough ressources in his inventory
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
     * removes the ressource from the player's inventary
     * @throws NoMoreRessourcesException
     */
    protected void removeRessources() throws NoMoreRessourcesException{
        for (Ressource ressource : cost.keySet()){
            player.removeRessource(ressource, cost.get(ressource));
        }
    }




}