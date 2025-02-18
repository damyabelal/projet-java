package game.tuile;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Player;




public class PlayerTest {
    private Player player;
    private Earth earth;

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
    void testRemoveWarriors() {
        player.removeWarriors(10);
        assertEquals(30, player.getWarriors());
    }
    // test the player adding resources
    @Test
    void testPlayerAddResources() {
        player.addRessource(Ressource.WOOD, 10);
        assertEquals(10, player.getResources().get(Ressource.WOOD));
    }

    // test the player removing resources
    @Test
    void testPlayerRemoveResources() {
        player.addRessource(Ressource.WOOD, 10);
        player.removeRessource(Ressource.WOOD, 5);
        assertEquals(5, player.getResources().get(Ressource.WOOD));
    }
    // test the player has enough resources
    @Test
    void testHasEnoughRessources(){
        player.addRessource(Ressource.WOOD, 2);
        player.addRessource(Ressource.ORE, 1);
        Farm farm = new Farm(earth);
        assertTrue(player.hasEnoughRessources(farm));
    }
    // test the player does not have enough resources
    @Test
    void testHasEnoughRessourcesFail(){
        player.addRessource(Ressource.WOOD, 2);
        player.addRessource(Ressource.ORE, 1);
        Exploitation exploitation = new Exploitation(earth);
        assertFalse(player.hasEnoughRessources(exploitation));
    }
    // test the player building a farm
    @Test
    void testBuild(){
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.ORE, 1);
        Farm farm = new Farm(earth);
        assertTrue(player.build(farm, earth));
        assertEquals(farm, earth.getBuilding());
    }









    
}
