package game.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.CantBuildException;
import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.listchooser.ListChooser;
import game.tuile.Ressource;

public class ExchangeRessourcesPort extends ActionManager<PlayerDemeter> implements Action<PlayerDemeter>{

    public ListChooser<Ressource> lc; 

    public ExchangeRessourcesPort(PlayerDemeter player, ListChooser<Ressource> lc){
        super(player);
        this.lc = lc; 
    }

     /**
     * asks the player which ressource he wants to exchange
     * @return the ressource the player wants to exchange
     */
    public Ressource askExchangeRessources()  {
        List<Ressource> ressources= new ArrayList<>();
        for (Ressource r : Arrays.asList(Ressource.values())){
            if (player.getRessourceAmount(r)>1){
                ressources.add(r); 
            }
        }
        return lc.choose("What ressources do you want to exhange?",ressources);
    }

    /**
     * asks the player which ressource he wants to receive in exchange
     * @return the ressource the player wants to receive in exchange
     */
    public Ressource askReceiveRessources(){
        return lc.choose("What ressource do you want in exchange?", Arrays.asList(Ressource.values())); 
    }


    /**
     * First, asks the player which ressource he wants to exchange, then ask him which one he
     * wants te receive in exhange. If all the conditions are met, the exchange takes place. 
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

        player.removeRessource(toExchange, 2);
        player.addRessource(toReceive, 1);
    
        System.out.println("2 "+ toExchange+ " were exchanged for 1 "+ toReceive);

    }
    
}
