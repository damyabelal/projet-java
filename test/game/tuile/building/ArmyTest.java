package game.tuile.building;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import game.*;
import game.tuile.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArmyTest {

    private Forest tuile;
    private Player player;
    private Army army;

    @BeforeEach
    void setUp() {
        tuile = new Forest();
        player = new Player("Scott");
        army = new Army(tuile, 0);
    }

    /**
     * Ensures the number of warriors does not exceed the maximum limit
     */
    @Test
    void armyMaxWarriorsTest() {
        Army army1 = new Army(tuile, 10);
        assertEquals(5, army1.getNbWarriors(), "Army should have at most 5 warriors");
    }

    /**
     * Test if the army can become a camp based on warrior count
     */
    @Test
    void canBeCampWithWarriorsTest() throws NoMoreRessourcesException {
        assertFalse(army.canBeCamp(player));
        player.addWarriors(5);
        army.addWarriors(5, player);
        assertTrue(army.canBeCamp(player));
    }

    /**
     * Test if the army can become a camp based on resources
     */
    @Test
    void canBeCampWithRessourcesTest() {
        assertFalse(army.canBeCamp(player));
        player.addRessource(Ressource.WOOD, 2);
        player.addRessource(Ressource.ORE, 3);
        assertTrue(army.canBeCamp(player));
    }

    /**
     * Test if the number of warriors is correctly added
     */
    @Test
    void addWarriorsTest() throws NoMoreRessourcesException {
        player.addWarriors(3);
        assertEquals(0, army.getNbWarriors());
        army.addWarriors(3, player);
        assertEquals(3, army.getNbWarriors());
    }

    /**
     * Ensures that an army cannot add more warriors than available in stock */
    @Test 
    void addMoreWarriorsThanPossibleTest() throws NoMoreRessourcesException {
        player.removeWarriors(30); // On met à zéro les warriors du joueur
        assertThrows(NoMoreRessourcesException.class, () -> army.addWarriors(6, player));
        assertEquals(0, army.getNbWarriors());
    }
    

    /**
     * Checks if an army can be upgraded to a camp when conditions are met
     */
    @Test
    void upGradeToCampTest() throws NoMoreRessourcesException {
        player.addWarriors(5);
        army.addWarriors(5, player);
        assertTrue(army.canBeCamp(player));
        assertNotNull(army.upGradeToCamp(player));
        assertTrue(army.upGradeToCamp(player) instanceof Camp);
    }

    /**
     * Ensures that upgrading to a camp fails when a player does not have enough resources
     */
    @Test
    void upgradeToCampFailedTest() {
        assertFalse(army.canBeCamp(player));
        assertNull(army.upGradeToCamp(player));
    }

    /**
     * Ensures that upgrading to a camp is successful when a player has enough resources
     */
    @Test
    void upgradeToCampWithRessourcesTest() throws NoMoreRessourcesException {
        player.addRessource(Ressource.WOOD, 2);
        player.addRessource(Ressource.ORE, 3);
        assertTrue(army.canBeCamp(player)); 
        Camp camp = army.upGradeToCamp(player);
        assertNotNull(camp); 
        assertTrue(camp instanceof Camp); 
}

}
