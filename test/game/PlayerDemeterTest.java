package game;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.action.BuildFarm;
import game.tuile.Ressource;

public class PlayerDemeterTest {

    private PlayerDemeter player;
    private Board board; 

    @BeforeEach
    // Create a player and a field tile
    void setUp() {
        player = new PlayerDemeter("TestPlayer");
        board= new Board(5, 5); 
    }

    // Test the player's initialization
    @Test
    void testPlayerInit() {
        assertEquals("TestPlayer", player.getName());
        assertEquals(0, player.getPoints());
        assertEquals(0, player.getNbThief()); 
        assertTrue(player.getFarms().isEmpty());
        assertTrue(player.getExploitations().isEmpty()); 
    }

    @Test
    void pointTest(){
        player.addPoints(5);
        assertEquals(player.getPoints(), 5);
    }

    @Test
    void thiefTest(){
        player.addThiefs(2);
        assertEquals(player.getNbThief(), 2);
    }

     @Test
    void testBuild() throws NoMoreRessourcesException, IOException{
        BuildFarm buildfarm=new BuildFarm(board, player);
        // a player trying to build with enough resources should return True
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.ORE, 1);
        assertTrue(player.getFarms().isEmpty());
        buildfarm.act(player);
        assertTrue(! player.getFarms().isEmpty());
        // after building a farm  using one wood and one ore , the player's ressources should diminish
        assertTrue(player.getResources().get(Ressource.WOOD)==0);
        assertTrue(player.getResources().get(Ressource.ORE)==0);
    }

    
}
