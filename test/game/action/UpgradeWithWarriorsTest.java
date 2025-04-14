package game.action;

import game.action.actionAres.UpgradeWithWarriors;
import game.PlayerAres;
import game.listchooser.RandomListChooser;
import game.tuile.Earth;
import game.tuile.Forest;
import game.tuile.Ressource;
import game.tuile.building.Army;
import game.util.CantBuildException;

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
    player.addRessource(Ressource.WOOD, 2);
    player.addRessource(Ressource.ORE, 3);

    tuile = new Forest();
    army = new Army(tuile, 5, player);
  }

  @Test
  void testUpgradeArmyToCampSuccessfully() throws Exception {
    tuile.setBuilding(army);
    player.addArmy(army);

    int warriorsBefore = player.getWarriors();

    UpgradeWithWarriors action = new UpgradeWithWarriors(player, new RandomListChooser<>(), new RandomListChooser<>());

    action.act(player);

    assertTrue(tuile.getBuilding() instanceof game.tuile.building.Camp);
    assertEquals(0, player.getArmies().size());
    assertEquals(1, player.getCamps().size());

    int warriorsAfter = player.getWarriors();
    int addedToCamp = warriorsBefore - warriorsAfter;

    assertTrue(addedToCamp >= 1 && addedToCamp <= warriorsBefore);
  }

  @Test
  void testFailsIfArmyTooWeak() throws CantBuildException {
    Earth weakTile = new Forest();
    Army weakArmy = new Army(weakTile, 3, player);
    weakTile.setBuilding(weakArmy);

    player.addArmy(weakArmy);

    UpgradeWithWarriors action = new UpgradeWithWarriors(
        player,
        new RandomListChooser<>(),
        new RandomListChooser<>()
    );

    assertThrows(CantBuildException.class, () -> action.act(player));
  }
}
