package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.PlayerDemeter;
import game.tuile.Ressource;

public class PlayThief implements Action<PlayerDemeter>{

    private Ressource ressource; // le type de ressource que this player veut voler
    private PlayerDemeter[] players;// other players of the game that that the player of the thief wants to steal from



    // en admettant que dans main on stock tout les joueurs dans un tableau
    public PlayThief(Ressource ressource,PlayerDemeter[] playerofboard ){
      this.ressource=ressource;
      this.players=playerofboard;


    }
  
  
    /** steals the Ressource ressource from every player and adds them to the given  player's inventory 
   * @param ressource the type of ressource that the thief will steal and give to the given player
  */
  @Override
  public void act(PlayerDemeter playerOfThief) throws NoMoreRessourcesException {
    // TODO Auto-generated method stub
    
    int amountToAdd=0;
    for (int i=0 ; i<this.players.length ;i++){

      amountToAdd+=this.players[i].getRessourceAmount(this.ressource);
    }

    playerOfThief.addRessource(this.ressource,amountToAdd);
  }

}