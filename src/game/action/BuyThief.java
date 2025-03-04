package game.action;
import java.util.HashMap;

import game.Player;
import game.PlayerDemeter;
import game.tuile.Ressource;

public class BuyThief implements Action<PlayerDemeter>{

    public HashMap<Ressource, Integer> cost = new HashMap<>();
    cost.put(Ressource.WOOD, 1);
    cost.put(Ressource.ORE, 1);
    cost.put(Ressource.WEALTH, 1);

    public boolean canBuy(HashMap<Ressource, Integer> inventory) {
        for (Ressource ressource: cost.key()){
            if (ressource.getOrDefault(ressource,0)< cost.get(ressource)){
                return false;
            }
        }
        return true;
    }

    public void act(PlayerDemeter player) {
        if (canBuy(player.getResources())){
            // à voir comment on veut ajouter le voleur, si c'est un boolean ou autre chose... 
        }
    }
}
