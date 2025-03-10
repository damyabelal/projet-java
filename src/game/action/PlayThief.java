package game.action;

import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Ressource;

public class PlayThief implements Action<PlayerDemeter>{

    private Ressource ressource; // le type de ressource que this player veut voler
  
  
    /** steals the Ressource ressource from every player and adds them to the given  player's inventory 
   * @param ressource the type of ressource that the thief will steal and give to the given player
  */
  @Override
  public void act(PlayerDemeter[] player) throws NoMoreRessourcesException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'act'"); 
    for (int i=0 ; i<player.length ;i++){
      


    }
  }

}