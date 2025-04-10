package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.listchooser.ListChooser;
import game.tuile.building.Army;

import java.io.IOException;
import java.util.List;
import game.CantBuildException;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Camp;

public class UpgradeWithRessources extends ActionManager<PlayerAres> implements Action<PlayerAres> {
  private Earth tuile;

  private ListChooser<Army> lc;

  public UpgradeWithRessources(PlayerAres player, ListChooser<Army> lc) {
    super(player);
    this.cost.put(Ressource.WOOD, 2);
    this.cost.put(Ressource.ORE, 3);
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
    return this.lc.choose("Which army do you want to upgrade?", armies);
  }

  @Override
  public void act(PlayerAres player) throws NoMoreRessourcesException, CantBuildException, IOException {
    Army chosenArmy = askArmy();
    // checks if the player has an army to upgrade
    if (chosenArmy == null) {
      throw new IllegalArgumentException("No army selected");
    }
    // checks if the player has enough ressources

    if (!this.hasEnoughRessources()) {
      throw new NoMoreRessourcesException("Not enough resources to upgrade the army");
    }

    else {
      this.removeRessources();

    }

    this.tuile = chosenArmy.getTuile();

    this.tuile.removeBuilding();
    player.removeArmy(chosenArmy);

    Camp camp = chosenArmy.upGradeToCamp(player);

    this.tuile.setBuilding(camp);
    player.addCamp(camp);

    System.out.println("The army evolved into a camp (" + chosenArmy.getNbWarriors() + " warriors)");

  }
}
