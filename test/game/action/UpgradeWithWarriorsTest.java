package game.action;


import game.action.actionAres.UpgradeWithWarriors;
import game.PlayerAres;
import game.listchooser.RandomListChooser;
import game.tuile.Earth;
import game.tuile.Forest;
import game.tuile.building.Army;
import game.tuile.building.Camp;
import game.util.CantBuildException;
import game.util.InvalidChoiceException;
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
    player.addWarriors(5);
    tuile = new Forest();
    army = new Army(tuile, 5, player);
    tuile.setBuilding(army);
    army.collectRessource(player);
    player.addArmy(army);
  }

  @Test
  void testUpgradeArmyToCampSuccessfully() throws Exception {
    UpgradeWithWarriors action = new UpgradeWithWarriors(
        player,
        new RandomListChooser<>(),
        new RandomListChooser<>()
    );

    int warriorsBefore = player.getWarriors();

    action.act(player);

    assertTrue(tuile.getBuilding() instanceof Camp);
    assertEquals(0, player.getArmies().size());
    assertEquals(1, player.getCamps().size());

    int warriorsAfter = player.getWarriors();
    assertEquals(warriorsBefore - 2, warriorsAfter);

    Camp camp = (Camp) tuile.getBuilding();
    assertEquals(7, camp.getNbWarriors());
  }

  @Test
  void testFailsIfNoEligibleArmy() throws CantBuildException {
    Army weakArmy = new Army(new Forest(), 3, player);
    player.addArmy(weakArmy);

    UpgradeWithWarriors action = new UpgradeWithWarriors(
        player,
        new RandomListChooser<>(),
        new RandomListChooser<>()
    );

    assertThrows(InvalidChoiceException.class, () -> action.act(player));
  }
}
