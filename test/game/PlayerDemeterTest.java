package game;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.tuile.Earth;
import game.tuile.Field;
import game.tuile.Ressource;

public class PlayerDemeterTest {

    private PlayerDemeter player;
    private Earth earth;

    @BeforeEach
    // Create a player and a field tile
    void setUp() {
        player = new PlayerDemeter("TestPlayer");
        earth = new Field();
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

    
}
