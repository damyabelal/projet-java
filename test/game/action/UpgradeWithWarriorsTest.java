package game.action;

import game.action.actionAres.UpgradeWithWarriors;
import game.PlayerAres;
import game.listchooser.RandomListChooser;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Army;
import game.util.CantBuildException;
import game.util.NoMoreRessourcesException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UpgradeWithWarriorsTest {

  private PlayerAres player;
  private Earth tuile;
  private Army army;

  @BeforeEach
  void setUp() throws CantBuildException {
    player = new PlayerAres("Player1");
    player.addWarriors(3);
    player.addRessource(Ressource.WOOD, 2);
    player.addRessource(Ressource.ORE, 3);

    tuile = new Earth(null, null);
    army = new Army(tuile, 5, player);
  }

  @Test
  void testUpgradeArmyToCampSuccessfully() throws Exception {
    tuile.setBuilding(army);
    player.addArmy(army);

    UpgradeWithWarriors action = new UpgradeWithWarriors(player, new RandomListChooser<>(), new RandomListChooser<>());
    action.act(player);

    assertTrue(tuile.getBuilding() instanceof game.tuile.building.Camp);
    assertEquals(0, player.getArmies().size());
    assertEquals(1, player.getCamps().size());
    assertEquals(0, player.getWarriors());
  }

  @Test
  void testFailsIfArmyTooWeak() throws CantBuildException {
    Earth weakTile = new Earth(null, null);
    Army weakArmy = new Army(weakTile, 3, player);
    weakTile.setBuilding(weakArmy);

    player.removeArmy(army);
    player.addArmy(weakArmy);

    UpgradeWithWarriors action = new UpgradeWithWarriors(
        player,
        new RandomListChooser<>(),
        new RandomListChooser<>()
    );

    assertThrows(CantBuildException.class, () -> action.act(player));
  }

  @Test
  void testFailsIfNotEnoughStockWarriors() throws NoMoreRessourcesException {
    player.removeWarriors(3);

    UpgradeWithWarriors action = new UpgradeWithWarriors(
        player,
        new RandomListChooser<>(),
        new RandomListChooser<>()
    );

    assertThrows(CantBuildException.class, () -> action.act(player));
  }
}
