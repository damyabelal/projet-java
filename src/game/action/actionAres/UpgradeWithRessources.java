package game.action.actionAres;

import game.PlayerAres;
import game.action.Action;
import game.action.ActionManager;
import game.listchooser.ListChooser;
import game.tuile.building.Army;

import java.util.List;

import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Camp;
import game.util.CantBuildException;
import game.util.InvalidChoiceException;
import game.util.NoMoreRessourcesException;

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
    * @return the description of the action
    */
    public String toString(){
      return "Upgrade with ressources => cost: " + this.cost; 
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
    if (chosenArmy == null) {
      throw new InvalidChoiceException("Action cancelled. No army was selected");
  
    }
    return chosenArmy;
}


  @Override
  public void act(PlayerAres player) throws NoMoreRessourcesException, CantBuildException, InvalidChoiceException {
    Army chosenArmy = askArmy();
   
    // checks if the player has enough ressources

    if (!this.hasEnoughRessources()) {
      throw new NoMoreRessourcesException("Not enough resources to upgrade the army");
    }
    
    this.tuile = chosenArmy.getTuile();

    this.tuile.removeBuilding();
    player.removeArmy(chosenArmy);

    Camp camp = chosenArmy.upGradeToCampWithRessources(player);

    this.removeRessources();

    this.tuile.setBuilding(camp);
    player.addCamp(camp);

    System.out.println("The army evolved into a camp (" + chosenArmy.getNbWarriors() + " warriors)");

  }
}
