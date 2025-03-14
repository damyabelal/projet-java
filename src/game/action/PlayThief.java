package game.action;

import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Ressource;
/**
 * This class is used to play a thief
 * its implements Action<PlayerDemeter>
 */
public class PlayThief implements Action<PlayerDemeter> {

    private Ressource ressource;
    private PlayerDemeter[] players;

    public PlayThief(Ressource ressource, PlayerDemeter[] players) {
        this.ressource = ressource;
        this.players = players;
    }


    /**
     * 
     * @param playerOfThief 
     * @throws NoMoreRessourcesException if the player doesn't have enough ressources to play the thief
     */
    @Override
    public void act(PlayerDemeter playerOfThief) throws NoMoreRessourcesException {
        int totalStolen = 0;

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
            throw new NoMoreRessourcesException("There's not enough of this type ressource : " + this.ressource + " to steel");
        }
    }
}
