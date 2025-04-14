package game.action.actionAres;

import game.PlayerAres;
import game.action.Action;
import game.action.ActionManager;
import game.listchooser.ListChooser;
import game.tuile.building.Army;


import java.util.ArrayList;
import java.util.List;

import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Camp;
import game.util.CantBuildException;
import game.util.InvalidChoiceException;
import game.util.NoMoreRessourcesException;

public class UpgradeWithWarriors extends ActionManager<PlayerAres> implements Action<PlayerAres> {

  private ListChooser<Integer> lnumb;
  private ListChooser<Army> lc;
  private Earth tuile;

  // army is the army that the given player player wants to upgrade
  public UpgradeWithWarriors(PlayerAres player, ListChooser<Integer> lnumb, ListChooser<Army> lc) {
    super(player);
    this.cost.put(Ressource.WOOD, 2);
    this.cost.put(Ressource.ORE, 3);
    this.lnumb = lnumb;
    this.lc = lc;

  }


    /**
    * @return the description of the action
    */
    public String toString(){
      return "Upgrade with warriors"; 
  }

  /**
   * Asks the player which army he wants to upgrade
   * 
   * @return the army the player wants to upgrade
   */
  public Army askArmy() throws InvalidChoiceException {
    List<Army> armies = this.player.getArmies();
    
    if (armies.isEmpty()) {
        throw new InvalidChoiceException("No armies available to upgrade");
    }

    Army chosenArmy = lc.choose("Which army do you want to upgrade?", armies);

    while (chosenArmy == null) {
        System.out.println("Invalid choice. Please choose a valid army.");
        chosenArmy = lc.choose("Which army do you want to upgrade?", armies);
    }

    return chosenArmy;
}


  /**
   * asks the player how many warriors ther want to add
   * 
   * @param max the maximum number of warriors the player can add
   * @return the number of warriors to add
   */
  public int askNumberOfWarriors() {
    List<Integer> options = new ArrayList<>();
    for (int i = 1; i <= (this.player).getWarriors(); i++) {
      options.add(i);
    }
    Integer add = lnumb.choose("How many warriors do you want to add?", options);
    if (add == null) {
      add = 0;
    }
    return add;
  }

  @Override
  public void act(PlayerAres player) throws NoMoreRessourcesException, CantBuildException,InvalidChoiceException{
    Army chosenArmy = askArmy();

    // asks the player how he wants to upgrade the army

    // checks if the player has an army to upgrade
    if (chosenArmy == null) {
      throw new InvalidChoiceException("No army selected");
    }

    // check if the army has 5 warriors to upgrade
    if (chosenArmy.getNbWarriors() < 5) {
      throw new CantBuildException("To upgrade an army to a camp, the army must have 5 warriors");
    }
    int add = askNumberOfWarriors();

    if (player.getWarriors() < add) {
      throw new CantBuildException("To upgrade an army to a camp, you must have enough warriors in stock");
    }

    this.tuile = chosenArmy.getTuile();

    // destroy army
    this.tuile.removeBuilding();
    player.removeArmy(chosenArmy);

    Camp camp = chosenArmy.upGradeToCampWithWarriors(player);
    camp.addWarriors(add);
    player.removeWarriors(add);

    // build camp
    this.tuile.setBuilding(camp);
    player.addCamp(camp);

    System.out.println("The army evolved into a camp (" + camp.getNbWarriors() + " warriors)");

  }
}