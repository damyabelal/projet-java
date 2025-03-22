package game.action;

import game.*;
import game.tuile.Forest;
import game.tuile.Ressource;
import game.tuile.building.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UpgradeArmyTest {

    private PlayerAres player;
    private UpgradeArmy upgradeArmyAction;

    @BeforeEach
    void setUp() throws CantBuildException {
        player = new PlayerAres("cool");
        player.addRessource(Ressource.WOOD, 2);
        player.addRessource(Ressource.ORE, 3);
        Army army = new Army(new Forest(), 5, player);
        player.addArmy(army);
        upgradeArmyAction = new UpgradeArmy(player);
    }

    @Test
    void testUpgradeArmyWithResources() throws NoMoreRessourcesException, CantBuildException {
        assertEquals(0, player.getCamps().size());
        upgradeArmyAction.act(player);
        assertEquals(1, player.getCamps().size());
        assertTrue(player.getCamps().get(0) instanceof Camp);
    }

    @Test
    void testUpgradeArmyNotEnoughResources() throws NoMoreRessourcesException {
        player.removeRessource(Ressource.WOOD, 2);
        player.removeRessource(Ressource.ORE, 3);
        assertThrows(NoMoreRessourcesException.class, () -> upgradeArmyAction.act(player));
    }

    @Test
    void testUpgradeArmyWithWarriors() throws NoMoreRessourcesException, CantBuildException {
        Army army = player.getArmies().get(0);
        assertEquals(5, army.getNbWarriors());
        upgradeArmyAction.act(player);
        assertTrue(army.getNbWarriors() > 5);
        assertEquals(1, player.getCamps().size());  
        assertTrue(player.getCamps().get(0) instanceof Camp);  
    }

    @Test
    void testUpgradeArmyWithTooFewWarriors() throws CantBuildException {
        Army army = new Army(new Forest(), 4, player);
        player.addArmy(army);
        assertThrows(CantBuildException.class, () -> upgradeArmyAction.act(player));
    }
}
