package game.action;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Board;
import game.CantBuildException;
import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Ressource;

public class BuildFarmTest{

    private Board board; 
    private PlayerDemeter player; 
    private Action<PlayerDemeter> action; 

    @BeforeEach
    void setUp(){
        player = new PlayerDemeter("kiwi");
        board= new Board(5, 5); 
        action= new BuildFarm(board, player); 
    }

    @Test
    void BuildTest() throws NoMoreRessourcesException, CantBuildException, IOException {
        // at the beginning , the player has no farms
        assertTrue(player.getFarms().isEmpty());
        // the player has no ressources , so if he attemps to build a farm it should throw an exception
        assertThrows(NoMoreRessourcesException.class, () -> action.act(player)); 
        // adding enough ressources so the player could build a farm
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.ORE, 1);
        action.act(player);
        // after building a farm , the player should have a farm in his inventory
        assertTrue(! player.getFarms().isEmpty());
    }

    
}