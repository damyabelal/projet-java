package game.action;

import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Ressource;

public class PlayThief implements Action<PlayerDemeter>{


  /** steals the Ressource ressource from every player and adds them to the given  player's inventory 
   * @param ressource the type of ressource that the thief will steal and give to the given player
  */
  public void steal(Ressource ressource, PlayerDemeter player ){
    // il faut trouver un moyen de recuperer tout les joueurs du plateau pour pouvoir 
    //accéder a leur ressources

  }
    
  @Override
  public void act(PlayerDemeter player) throws NoMoreRessourcesException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'act'");
  }

}