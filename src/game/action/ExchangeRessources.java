package game.action;

import game.NoMoreRessourcesException;
import game.Player;
import game.tuile.Ressource;

public class ExchangeRessources <T extends Player> implements Action<T> {

    private Ressource toExchange;
    private Ressource toReceive;

    /**
     * creates an exchange action between two resources
     * @param toExchange the resource to exchange (3 will be removed)
     * @param toReceive the resource to receive (1 will be added)
     */
    public ExchangeRessources(Ressource toExchange , Ressource toReceive) {
        if ( toExchange == toReceive) {
            throw new IllegalArgumentException("You cannot exchange a ressource for itself");
        }
        this.toExchange = toExchange;
        this.toReceive = toReceive;
    }


    /**
     * 
     */
    @Override
    public void act(T player) throws NoMoreRessourcesException {
        if (player.getRessourceAmount(toExchange) < 3) {
            throw new NoMoreRessourcesException("Not enough " + toExchange + " to exchange you need at least 3");
        }
        player.removeRessource(toExchange,3);
        player.addRessource(toReceive, 1);
    }
}
