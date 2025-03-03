package game.action;

import java.util.HashMap;

import game.NoMoreRessourcesException;
import game.Player;
import game.tuile.Ressource;

public class BuySecretWeapon implements Action{

  public HashMap<Ressource,Integer> cost=new HashMap<>();
  cost.put(Ressource.ORE);
  cost.put(Ressource.WODD);

  public boolean canBuy(HashMap<Ressource, Integer> inventory) {
        for (Ressource ressource: cost.key()){
            if (ressource.getOrDefault(ressource,0)< cost.get(ressource)){
                return false;
            }
        }
        return true;
    }
@Override
public void act(Player player) throws NoMoreRessourcesException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'act'");
}

    
}
