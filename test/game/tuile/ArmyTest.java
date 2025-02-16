package game.tuile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.*;

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

    @Test
    void canBeCampWithWarriorsTest(){
        assertFalse(army.canBeCamp(player));
        army.addWarriors(5, player);
        assertTrue(army.canBeCamp(player));
    }

    @Test
    void canBeCampWithRessourcesTest(){
        assertFalse(army.canBeCamp(player));
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.SHEEP, 1);
        player.addRessource(Ressource.WEALTH, 1);
        assertTrue(army.canBeCamp(player));
    }

    @Test
    void addWarriorsTest(){
        assertTrue(army.getNbWarriors() ==0 );
        army.addWarriors(1, player);
        assertTrue(army.getNbWarriors()==1);
    }

    @Test
    void upGradeToCampTest(){}


}
