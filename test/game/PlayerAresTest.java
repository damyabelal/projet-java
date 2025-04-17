package game;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.tuile.building.Army;
import game.tuile.building.Camp;
import game.util.NoMoreRessourcesException;

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
    @Test
    void testSecretWeaponAddRemove() {
        assertEquals(0, player.getNbSecretWeapon());

        player.addSecretWeapon();
        assertEquals(1, player.getNbSecretWeapon());

        player.removeSecretWeapon();
        assertEquals(0, player.getNbSecretWeapon());
    }


    @Test
    void testAddArmy() throws Exception {
        Army army = new Army(null, 3, player);
        player.addArmy(army);

        assertTrue(player.getArmies().contains(army));
        assertTrue(player.getTiles().contains(army.getTuile()));
    }



    @Test
    void testRemoveArmy() throws Exception{
        Army army = new Army(null, 2, player);
        player.addArmy(army);
        assertEquals(1, player.getArmies().size());

        player.removeArmy(army);
        assertEquals(0, player.getArmies().size());
        assertFalse(player.getTiles().contains(army.getTuile()));
    }

    @Test
    void testAddCamp()throws Exception {
        Camp camp = new Camp(null, 5, player);
        player.addCamp(camp);

        assertTrue(player.getCamps().contains(camp));
        assertTrue(player.getTiles().contains(camp.getTuile()));
    }

    
    // tets si  givePlayersObjective() cree bien un objectif
    @Test
    void testGivePlayersObjectives(){
        
        player.givePlayersObjective();
        assertNotNull(player.getObjective());
    }

    @Test
    void testHasConqueredTilesObjective() throws Exception {
        Board board = new Board(5, 5);
        // On force un objectif "conquérir au moins 1 tuile"
        player.setObjective( new AresGameObjectives(AresGameObjectives.ObjectiveType.CONQUER_TILES, 1));

        // On ajoute une armée sur une tuile factice (null ici car on ne teste que la quantité)
        Army army = new Army(null, 1, player);
        player.addArmy(army);

        // On vérifie que l’objectif est atteint
        assertTrue(player.isObjectiveAchieved(board));
    }










}
