package game;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Board;
import game.NoMoreRessourcesException;
import game.Player;
import game.PlayerAres;
import game.PlayerDemeter;
import game.action.BuildFarm;
import game.tuile.Earth;
import game.tuile.Field;
import game.tuile.Ressource;
import game.tuile.building.Exploitation;
import game.tuile.building.Farm;
import game.util.Position;




public class PlayerTest {
    private Player player;
    private Earth earth;
    private PlayerAres playerAres;

    @BeforeEach
    // Create a player and a field tile
    void setUp() {
        player = new Player("TestPlayer");
        earth = new Field();
    }

    // Test the player's initialization
    @Test
    void testPlayerInit() {
        assertEquals("TestPlayer", player.getName());
        assertEquals(0, player.getResources().get(Ressource.WOOD));// puisque la tuile est une field 

    }
    // test the player adding warriors
    
    
    // test the player adding resources
    @Test
    void testPlayerAddResources() {
        player.addRessource(Ressource.WOOD, 10);
        assertEquals(10, player.getResources().get(Ressource.WOOD));
    }

    // test the player removing resources
    @Test
    void testPlayerRemoveResources()  throws NoMoreRessourcesException{
        player.addRessource(Ressource.WOOD, 10);
        player.removeRessource(Ressource.WOOD, 5);
        assertEquals(5, player.getResources().get(Ressource.WOOD));
    }
    // test the player has enough resources
    @Test
    void testHasEnoughRessources(){
        player.addRessource(Ressource.WOOD, 2);
        player.addRessource(Ressource.ORE, 1);
        Farm farm = new Farm(earth, null);
        assertTrue(player.hasEnoughRessources(farm));
    }
    // test the player does not have enough resources
    @Test
    void testHasEnoughRessourcesFail(){
        player.addRessource(Ressource.WOOD, 2);
        player.addRessource(Ressource.ORE, 1);
        Exploitation exploitation = new Exploitation(earth, null);
        assertFalse(player.hasEnoughRessources(exploitation));
    }
    // test the player building a farm
    @Test
    void testBuild() throws NoMoreRessourcesException, IOException{
        
        Farm farm = new Farm(earth, null);
        BuildFarm buildfarm=new BuildFarm(null, null);
        PlayerDemeter playerDemeter=new PlayerDemeter("nun");

        // a player trying to build with enough resources should return True
        playerDemeter.addRessource(Ressource.WOOD, 1);
        playerDemeter.addRessource(Ressource.ORE, 1);
        buildfarm.act(playerDemeter);
        assertEquals(playerDemeter.getFarms(),farm);
        assertEquals(farm, earth.getBuilding());
        // after building a farm  using one wood and one ore , the player's ressources should diminish
        assertTrue(player.getResources().get(Ressource.WOOD)==0);
        assertTrue(player.getResources().get(Ressource.ORE)==0);

    
    }
    /*
     * 
     */
    




     






    


    









    
}
