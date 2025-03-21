package game.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import game.CantBuildException;
import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.Ressource;
public class BuyWarriorsTest{

    private PlayerAres player; 
    private Action<PlayerAres> action; 

    @BeforeEach
    void setUp(){
        player = new PlayerAres("cool");
        action= new BuyWarriors(player); 
    }

    @Test
    void actTest() throws NoMoreRessourcesException, CantBuildException, IOException{
        assertTrue(player.getWarriors()==30);
        assertThrows(NoMoreRessourcesException.class, () -> action.act(player)); 
        player.addRessource(Ressource.WEALTH, 2);
        player.addRessource(Ressource.SHEEP, 2);
        player.addRessource(Ressource.ORE, 1);
        action.act(player);
        assertTrue(player.getWarriors()==35);
    }

}