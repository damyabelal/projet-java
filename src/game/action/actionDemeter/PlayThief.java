package game.action.actionDemeter;

import java.util.Arrays;
import java.util.List;

import game.PlayerDemeter;
import game.action.Action;
import game.listchooser.ListChooser;
import game.tuile.Ressource;
import game.util.NoMoreRessourcesException;
/**
 * This class is used to play a thief
 * its implements Action<PlayerDemeter>
 */
public class PlayThief implements Action<PlayerDemeter> {

    private List<PlayerDemeter> players;
    public ListChooser<Ressource> lc; 
    private Ressource ressource; 

    //builds a new PlayThief action with the given players demeter on the board and the given ressource type that the thief will steal
    public PlayThief(ListChooser<Ressource> lc, List<PlayerDemeter> players) {
        this.ressource = askRessource();
        this.players = players;
    }

    /**
     * asks the player which ressource he wants to receive in exchange
     * @return the ressource the player wants to receive in exchange
     */
    public Ressource askRessource() {
        Ressource chosen = lc.choose("What resource do you want to steal?", Arrays.asList(Ressource.values()));
        if (chosen == null){
            System.out.println("Action cancelled. No ressource to steal was selected");
        }
        return chosen;
    }


    /**
    * @return the description of the action
    */
    public String toString(){
        return "Play thief" ; 
    }


    /**
     * the thief steals the type of ressource in the constructor from every player on the board and gives it the given playerOfThief
     * @param playerOfThief 
     * @throws NoMoreRessourcesException if the player doesn't have enough ressources to play the thief
     */
    @Override
    public void act(PlayerDemeter playerOfThief) throws NoMoreRessourcesException {
        int totalStolen = 0;
        // check if the player has a thief to play
        if (playerOfThief.getNbThief() == 0) {
            throw new NoMoreRessourcesException("You don't have any thief to play");
        }

        for (int i = 0; i < this.players.size(); i++) {
            if (this.players.get(i) != playerOfThief) {
                int stolen = this.players.get(i).getRessourceAmount(this.ressource);
                if (stolen > 0) {
                    totalStolen += stolen;
                    this.players.get(i).removeRessource(this.ressource, stolen);
                }
            }
        }

        if (totalStolen > 0) {
            playerOfThief.addRessource(this.ressource, totalStolen);
        } else {
            throw new NoMoreRessourcesException("There is not enough of this type of ressource : " + this.ressource + " to be able to steal");
        }
    }
}
