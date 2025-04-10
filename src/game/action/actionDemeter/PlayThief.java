package game.action.actionDemeter;

import game.PlayerDemeter;
import game.action.Action;
import game.tuile.Ressource;
import game.util.NoMoreRessourcesException;
/**
 * This class is used to play a thief
 * its implements Action<PlayerDemeter>
 */
public class PlayThief implements Action<PlayerDemeter> {

    private Ressource ressource;
    private PlayerDemeter[] players;
    //builds a new PlayThief action with the given players demeter on the board and the given ressource type that the thief will steal
    public PlayThief(Ressource ressource, PlayerDemeter[] players) {
        this.ressource = ressource;
        this.players = players;
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

        for (int i = 0; i < this.players.length; i++) {
            if (this.players[i] != playerOfThief) {
                int stolen = this.players[i].getRessourceAmount(this.ressource);
                if (stolen > 0) {
                    totalStolen += stolen;
                    this.players[i].removeRessource(this.ressource, stolen);
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
