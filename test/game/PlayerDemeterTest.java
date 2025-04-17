package game;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.action.actionDemeter.BuildFarm;
import game.listchooser.RandomListChooser;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Exploitation;
import game.tuile.building.Farm;
import game.tuile.building.Port;
import game.util.InvalidChoiceException;
import game.util.NoMoreRessourcesException;

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
        player.addThief();
        assertEquals(player.getNbThief(), 1);
    }

    @Test
    void testBuild() throws NoMoreRessourcesException, IOException, InvalidChoiceException{
        BuildFarm buildfarm=new BuildFarm(board, player, new RandomListChooser<>());
        // a player trying to build with enough resources should return True
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.ORE, 1);
        assertTrue(player.getFarms().isEmpty());
        buildfarm.act(player);
        buildfarm.act(player);
        buildfarm.act(player);
        assertEquals( player.getFarms().size(),3);
        // after building a farm  using one wood and one ore , the player's ressources should diminish
        assertTrue(player.getResources().get(Ressource.WOOD)==0);
        assertTrue(player.getResources().get(Ressource.ORE) == 0);
        }

    @Test
    void testRemoveFarm() {
        Earth earth = new Earth(Ressource.SHEEP, null);
        Farm farm = new Farm(earth, player);
        player.addFarm(farm);
        assertTrue(player.getFarms().contains(farm));

        player.removeFarm(farm);
        assertFalse(player.getFarms().contains(farm));
        assertFalse(player.getTiles().contains(farm.getTuile()));
    }



    @Test
    void testAddExploitation() {
        Earth earth = new Earth(Ressource.SHEEP, null);
        Exploitation ex = new Exploitation(earth, player);
        player.addExploitation(ex);

        assertTrue(player.getExploitations().contains(ex));
        assertTrue(player.getTiles().contains(ex.getTuile()));
    }

    @Test
    void testHasPort() {
        Earth earth = new Earth(Ressource.SHEEP, null);
        earth.setPosition(new game.util.Position(1, 1));
        Port port = new Port(earth, player);
        earth.setBuilding(port);

        player.addPort(port); 

        assertTrue(player.hasPort());
    }

    








    
}
