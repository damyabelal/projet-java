package game.action;

import game.*;
import game.action.actionAres.UpgradeWithRessources;
import game.listchooser.RandomListChooser;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Army;
import game.util.CantBuildException;
import game.util.NoMoreRessourcesException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpgradeWithRessourcesTest {

  private PlayerAres player;
  private Earth tuile;
  private Army army;
  private UpgradeWithRessources action;

  @BeforeEach
  void setUp() throws CantBuildException {

    player = new PlayerAres("Player1");
    player.addRessource(Ressource.WOOD, 2);
    player.addRessource(Ressource.ORE, 3);

    tuile = new Earth(null, null);
    army = new Army(tuile, 5, player);
    tuile.setBuilding(army);
    player.addArmy(army);

    action = new UpgradeWithRessources(player, new RandomListChooser<>());
  }

  @Test
  void testUpgradeArmyToCampSuccessfully() throws Exception {
    action.act(player);
    assertTrue(tuile.getBuilding() instanceof game.tuile.building.Camp, "building should now be a camp");
    assertEquals(0, player.getArmies().size(), "army should be removed from player");
    assertEquals(1, player.getCamps().size(), "one camp should be added to player");
  }

  @Test
  void testUpgradeFailsWithoutResources() throws NoMoreRessourcesException {
    player.removeRessource(Ressource.WOOD, 2);
    player.removeRessource(Ressource.ORE, 3);
    assertThrows(NoMoreRessourcesException.class, () -> action.act(player));
  }
}
