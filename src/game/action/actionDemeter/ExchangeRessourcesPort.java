package game.action.actionDemeter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.PlayerDemeter;
import game.action.Action;
import game.action.ActionManager;
import game.listchooser.ListChooser;
import game.tuile.Ressource;
import game.util.CantBuildException;
import game.util.NoMoreRessourcesException;

public class ExchangeRessourcesPort extends ActionManager<PlayerDemeter> implements Action<PlayerDemeter>{

    public ListChooser<Ressource> lc; 

    public ExchangeRessourcesPort(PlayerDemeter player, ListChooser<Ressource> lc){
        super(player);
        this.lc = lc; 
    }


    /**
    * @return the description of the action
    */
    public String toString(){
        return "Exchange ressources by port"; 
    }



     /**
     * asks the player which ressource he wants to exchange
     * @return the ressource the player wants to exchange
     */
    public Ressource askExchangeRessources() {
        List<Ressource> ressources = new ArrayList<>();
        for (Ressource r : Ressource.values()) {
            if (player.getRessourceAmount(r) > 1) {
                ressources.add(r);
            }
        }
        Ressource chosenRessource = lc.choose("What resources do you want to exchange?", ressources);
        while (chosenRessource == null) {
            System.out.println("Invalid choice. Please choose a valid resource.");
            chosenRessource = lc.choose("What resources do you want to exchange?", ressources);
        }
        return chosenRessource;
    }
    

    /**
     * asks the player which ressource he wants to receive in exchange
     * @return the ressource the player wants to receive in exchange
     */
    public Ressource askReceiveRessources() {
        Ressource chosen = lc.choose("What resource do you want in exchange?", Arrays.asList(Ressource.values()));
        while (chosen == null) {
            System.out.println("Invalid choice. Please choose a valid resource.");
            chosen = lc.choose("What resource do you want in exchange?", Arrays.asList(Ressource.values()));
        }
        return chosen;
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
