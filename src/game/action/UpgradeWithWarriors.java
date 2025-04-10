package game.action;

import game.NoMoreRessourcesException;
import game.Player;
import game.PlayerAres;
import game.listchooser.ListChooser;
import game.tuile.building.Army;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import game.CantBuildException;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Camp;

public class UpgradeWithWarriors extends ActionManager implements Action<PlayerAres> {

  private ListChooser<Integer> lnumb;
  private ListChooser<Army> lc;
  private Earth tuile;

  // army is the army that the given player player wants to upgrade
  public UpgradeWithWarriors(Player player, ListChooser<Integer> lnumb, ListChooser<Army> lc) {
    super(player);
    this.cost.put(Ressource.WOOD, 2);
    this.cost.put(Ressource.ORE, 3);
    this.lnumb = lnumb;
    this.lc = lc;

  }

  /**
   * Asks the player which army he wants to upgrade
   * 
   * @return the army the player wants to upgrade
   */
  public Army askArmy() {
    List<Army> armies = ((PlayerAres) this.player).getArmies();
    if (armies.isEmpty()) {
      throw new IllegalArgumentException("No armies available to upgrade");
    }
    return (Army) this.lc.choose("Which army do you want to upgrade?", armies);
  }

  /**
   * asks the player how many warriors ther want to add
   * 
   * @param max the maximum number of warriors the player can add
   * @return the number of warriors to add
   */
  public int askNumberOfWarriors() {
    List<Integer> options = new ArrayList<>();
    for (int i = 1; i <= ((PlayerAres) this.player).getWarriors(); i++) {
      options.add(i);
    }
    return (int) this.lnumb.choose("How many warriors do you want to add?", options);
  }

  @Override
  public void act(PlayerAres player) throws NoMoreRessourcesException, CantBuildException, IOException {
    Army chosenArmy = askArmy();

    // asks the player how he wants to upgrade the army

    // checks if the player has an army to upgrade
    if (chosenArmy == null) {
      throw new IllegalArgumentException("No army selected");
    }

    // check if the army has 5 warriors to upgrade
    if (chosenArmy.getNbWarriors() < 5) {
      throw new CantBuildException("To upgrade an army to a camp, the army must have 5 warriors");
    }
    int add = askNumberOfWarriors();
    if (player.getWarriors() < add) {
      throw new CantBuildException("To upgrade an army to a camp, you must have enough warriors in stock");
    }
    chosenArmy.addWarriors(add);
    player.removeWarriors(add);

    this.tuile = chosenArmy.getTuile();

    this.tuile.removeBuilding();
    player.removeArmy(chosenArmy);

    Camp camp = chosenArmy.upGradeToCamp(player);

    this.tuile.setBuilding(camp);
    player.addCamp(camp);

    System.out.println("The army evolved into a camp (" + chosenArmy.getNbWarriors() + " warriors)");

  }
}