package game.action;

import java.util.Arrays;

import game.CantBuildException;
import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import listchooser.RandomListChooser;
import game.tuile.Ressource;

public class ExchangeRessourcesPort extends ActionManager implements Action<PlayerDemeter>{

    public static RandomListChooser<Ressource> lc; 

    public ExchangeRessourcesPort(PlayerDemeter player){
        super(player);
        lc = new RandomListChooser<>(); 
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
     * The conditions are different from ExchangeRessources
     */
    public void act(PlayerDemeter player) throws NoMoreRessourcesException , CantBuildException{
        Ressource toExchange= askExchangeRessources();
        Ressource toReceive= askReceiveRessources(); 

        if (toExchange==toReceive){
            throw new  IllegalArgumentException("2 ressources being exchanged must be different");
        }

        if (!player.hasPort()){
            throw new CantBuildException("You don't have a port to exchange ressources"); 
        }

        if (player.getRessourceAmount(toExchange)<2){
           throw new NoMoreRessourcesException("Not enough Ressources to be able to exchange.");
        }

        player.removeRessource(toExchange, 0);
        player.addRessource(toReceive, 0);
    


    }
    
}
