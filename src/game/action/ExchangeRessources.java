package game.action;

import java.util.Arrays;

import game.NoMoreRessourcesException;
import game.Player;
import game.listchooser.RandomListChooser;
import game.tuile.Ressource;

public class ExchangeRessources <T extends Player> extends ActionManager implements Action<T> {

    private static RandomListChooser<Ressource> lc; 

    /**
     * creates an exchange action between two resources
     * @param toExchange the resource to exchange (3 will be removed)
     * @param toReceive the resource to receive (1 will be added)
     */
    public ExchangeRessources(Player player) {
        super(player); 
        lc= new RandomListChooser<>(); 
    }

    /**
     * ask the player which ressource he wants to exchange
     * @return the ressource
     */
    public Ressource askExchangeRessources()  {
        return lc.choose("What ressources do you want to exhange?", Arrays.asList(Ressource.values()));
    }

    /**
     * ask the player which ressource he wants to receive in exchange
     * @return the ressource
     */
    public Ressource askReceiveRessources(){
        return lc.choose("What ressource do you want in exchange?", Arrays.asList(Ressource.values())); 
    }


    /**
     * First, ask the player which ressource he wants to exchange, then ask him which one he
     * wants te receive in exhange. If all the conditions are met, the exchange take place.  
     */
    @Override
    public void act(T player) throws NoMoreRessourcesException {
        Ressource toExchange= askExchangeRessources();
        Ressource toReceive= askReceiveRessources();

        if ( toExchange == toReceive) {
            throw new IllegalArgumentException("You cannot exchange a ressource for itself");
        }

        if (player.getRessourceAmount(toExchange) < 3) {
            throw new NoMoreRessourcesException("Not enough " + toExchange + " to exchange you need at least 3");
        }
        player.removeRessource(toExchange,3);
        player.addRessource(toReceive, 1);
        System.out.println("3 "+ toExchange+ " were exchanged far 1 "+ toReceive);
    }
}
