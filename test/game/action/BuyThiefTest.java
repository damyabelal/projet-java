package game.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.CantBuildException;
import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Ressource;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class BuyThiefTest{

    private PlayerDemeter player; 
    private Action<PlayerDemeter> action; 

    @BeforeEach
    void setUp(){
        player = new PlayerDemeter("elijah");
        action= new BuyThief(player); 
    }

    @Test
    void actTest() throws NoMoreRessourcesException, CantBuildException, IOException{
        assertTrue(player.getNbThief()==0);
        assertThrows(NoMoreRessourcesException.class, () -> action.act(player)); 
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.ORE, 1);
        player.addRessource(Ressource.WEALTH, 1);
        action.act(player);
        assertTrue(player.getNbThief()==1);
    }


    
}