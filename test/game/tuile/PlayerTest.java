package game.tuile;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Board;
import game.NoMoreRessourcesException;
import game.Player;
import game.PlayerAres;
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
        playerAres= new PlayerAres("mohamed");
    }

    // Test the player's initialization
    @Test
    void testPlayerInit() {
        assertEquals("TestPlayer", player.getName());
        assertEquals(30, player.getWarriors());
        assertEquals(0, player.getResources().get(Ressource.WOOD));// puisque la tuile est une field 

    }
    // test the player adding warriors
    @Test
    void testPlayerAddWarriors() {
        player.addWarriors(10);
        assertEquals(40, player.getWarriors());
    }
    /// test the player removing warriors
    @Test
    void testRemoveWarriors() throws NoMoreRessourcesException {
        playerAres.removeWarriors(10);
        assertEquals(20, player.getWarriors());
    }

    
    @Test // it should throw an exception
    void TestNoWarriorsToRemove() throws NoMoreRessourcesException{
        assertEquals(30, player.getWarriors());
        playerAres.removeWarriors(30); // removing the initial 30 warriors the player has
        assertEquals(0, player.getWarriors());
        assertThrows(NoMoreRessourcesException.class ,()->{playerAres.removeWarriors(1);});//removing a warrior from player having 0 warriors should throw an exception
        

    }
    
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
    void testBuild() throws NoMoreRessourcesException{
        
        Farm farm = new Farm(earth, null);
        

        // a player trying to build with enough resources should return True
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.ORE, 1);
        assertTrue(player.build(farm, earth));
        assertEquals(farm, earth.getBuilding());
        // after building a farm  using one wood and one ore , the player's ressources should diminish
        assertTrue(player.getResources().get(Ressource.WOOD)==0);
        assertTrue(player.getResources().get(Ressource.ORE)==0);

    
    }
    /*
     * 
     */
    




     






    


    









    
}
