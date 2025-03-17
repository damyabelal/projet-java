package game.action;

import game.CantBuildException;
import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Ressource;

public class ExchangeRessourcesPort implements Action<PlayerDemeter>{

    private Ressource toExchange;
    private Ressource toReceive;

    public ExchangeRessourcesPort( Ressource toexchange , Ressource toreceive ){

        if (toexchange==toreceive){
            throw new  IllegalArgumentException("2 ressources being exchanged must be different");
        }
        this.toExchange=toexchange;
        this.toReceive=toreceive;
    }
    // permet d'échanger de ressources identiques identiques contre une autre
    @Override
    public void act(PlayerDemeter player) throws NoMoreRessourcesException , CantBuildException{
        
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
