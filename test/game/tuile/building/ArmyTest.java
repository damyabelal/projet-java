package game.tuile.building;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.*;
import game.tuile.Field;
import game.tuile.Forest;
import game.tuile.Ressource;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyTest {

    private Forest tuile;
    private Player player; 
    private Army army; 
    
    @BeforeEach
    void setUp() {
        tuile = new Forest();
        player = new Player("Scott");
        army = new Army(tuile,0);
    }
    /* ensures the numbers of warriors does not exceed the maximum number of warriors */ 
   @Test
   void armyMaxWarriorsTest(){
        Army army = new Army(tuile, 10);
        assertTrue(army.getNbWarriors() == 5);
     
   }
    // test if the army can be a camp
    @Test
    void canBeCampWithWarriorsTest() throws NoMoreRessourcesException{
        assertFalse(army.canBeCamp(player));
        army.addWarriors(5, player);
        assertTrue(army.canBeCamp(player));
    }
    // test if the army can be a camp with ressources
    @Test
    void canBeCampWithRessourcesTest(){
        assertFalse(army.canBeCamp(player));
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.SHEEP, 1);
        player.addRessource(Ressource.WEALTH, 1);
        assertTrue(army.canBeCamp(player));
    }
    // test the addWarriors method if the number of warriors is added
    @Test
    void addWarriorsTest() throws NoMoreRessourcesException{
        assertTrue(army.getNbWarriors() ==0 );
        army.addWarriors(1, player);
        assertTrue(army.getNbWarriors()==1);
    }
    // Checks if an army can be upgraded to a camp when conditions are met
    @Test
    void upGradeToCampTest() throws NoMoreRessourcesException{
        army.addWarriors(5, player);
        assertTrue(army.canBeCamp(player));
        assertTrue(army.upGradeToCamp(player) instanceof Camp);
    }
    /*
     * ensures that a player can not add more warriors than they have resources for
     */
    
    @Test 
    void addMoreWarriorsThanPossibleTest() throws NoMoreRessourcesException{
        Player player = new Player("player 1");
        player.removeWarriors(30); // ensure the player has no warriors

        Field tuile = new Field();
        Army army = new Army(tuile, 0);
        
        assertThrows(NoMoreRessourcesException.class, () -> army.addWarriors(6, player));
        
        // ensures that the number of warriors in the army is still 0
        assertEquals(0,army.getNbWarriors());
    }
    // ensures that upgrading to a camp fails when a player does not have enough resources
    @Test
    void upgradeToCampFailedTest(){
        assertFalse(army.canBeCamp(player));
        assertNull(army.upGradeToCamp(player));
    }
    /*
     * ensures that a player can not add more warriors than they have resources for
     */
    @Test
    void upgradeToCampWithRessourcesTest() throws NoMoreRessourcesException{
        // add resources to the player
        player.addRessource(Ressource.WOOD, 2);
        player.addRessource(Ressource.SHEEP, 3);

        // xheck if the player has the correct amount of resources
       

        assertTrue(army.canBeCamp(player));
        assertTrue(army.upGradeToCamp(player) instanceof Camp);
    }


}
