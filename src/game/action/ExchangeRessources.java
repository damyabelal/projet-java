package game.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.NoMoreRessourcesException;
import game.Player;
import game.listchooser.ListChooser;
import game.tuile.Ressource;

public class ExchangeRessources <T extends Player> extends ActionManager implements Action<T> {

    private ListChooser<Ressource> lc; 

    /**
     * creates an exchange action between two resources
     * @param player the player who proceed the exchange
     */
    public ExchangeRessources(Player player, ListChooser<Ressource> lc) {
        super(player); 
        this.lc= lc; 
    }

    /**
     * asks the player what type of  ressource he wants to exchange
     * @return the ressource
     */
    public Ressource askExchangeRessources()  {
        List<Ressource> ressources= new ArrayList<>();
        for (Ressource r : Arrays.asList(Ressource.values())){
            if (player.getRessourceAmount(r)>2){
                ressources.add(r); 
            }
        }
        return lc.choose("What ressource do you want to exchange?", ressources);
    }

    /**
     * asks the player which ressource he wants to receive in exchange
     * @return the ressource
     */
    public Ressource askReceiveRessources(){
        return lc.choose("What ressource do you want in exchange?", Arrays.asList(Ressource.values())); 
    }


    /**
     * first, asks the player which ressource he wants to exchange, then asks  which one he
     * wants te receive in exchange. If all the conditions are met, the exchange takes place.  
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
        System.out.println("3 "+ toExchange+ " were exchanged for 1 "+ toReceive);
    }
}
