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

public class PlayerAresTest {
    
    private PlayerAres player;

    @BeforeEach
    // Create a player and a field tile
    void setUp() {
        player = new PlayerAres("TestPlayer");
    }

    @Test
    void testPlayerAddWarriors() {
        player.addWarriors(10);
        assertEquals(40, player.getWarriors());
    }
    /// test the player removing warriors
    @Test
    void testRemoveWarriors() throws NoMoreRessourcesException {
        player.removeWarriors(10);
        assertEquals(20, player.getWarriors());
    }

    
    @Test // it should throw an exception
    void TestNoWarriorsToRemove() throws NoMoreRessourcesException{
        assertEquals(30, player.getWarriors());
        player.removeWarriors(30); // removing the initial 30 warriors the player has
        assertEquals(0, player.getWarriors());
        assertThrows(NoMoreRessourcesException.class ,()->{player.removeWarriors(1);});//removing a warrior from player having 0 warriors should throw an exception
        

    }
}
