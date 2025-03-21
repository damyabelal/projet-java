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
        assertTrue(player.getFarms().isEmpty());
        assertThrows(NoMoreRessourcesException.class, () -> action.act(player)); 
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.ORE, 1);
        action.act(player);
        assertTrue(! player.getFarms().isEmpty());
    }

    
}