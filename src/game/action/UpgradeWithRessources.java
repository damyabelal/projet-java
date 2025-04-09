package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.listchooser.RandomListChooser;
import game.tuile.building.Army;

import java.io.IOException;
import java.util.List;
import game.CantBuildException;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Camp;


public class UpgradeWithRessources extends ActionManager implements Action<PlayerAres> {
private Earth tuile; 


private RandomListChooser<Army> lc;
private RandomListChooser<String> lString;  
private RandomListChooser<Integer> lnumb;

public UpgradeWithRessources(PlayerAres player) {
    super(player);
    this.cost.put(Ressource.WOOD, 2);
    this.cost.put(Ressource.ORE, 3);
    this.lString = new RandomListChooser<>();
    this.lnumb = new RandomListChooser<>();
    this.lc = new RandomListChooser<>();
   
}



@Override
public void act(PlayerAres player) throws NoMoreRessourcesException, CantBuildException, IOException {
  // TODO Auto-generated method stub
  throw new UnsupportedOperationException("Unimplemented method 'act'");
}



    /**
     * Asks the player which army he wants to upgrade
     * @return the army the player wants to upgrade
     */
    public Army askArmy() {
      List<Army> armies = ((PlayerAres) this.player).getArmies();
      if (armies.isEmpty()) {
          throw new IllegalArgumentException("No armies available to upgrade");
  }
      return this.lc.choose("Which army do you want to upgrade?", armies);
  }

  /**
     * asks the player how many warriors ther want to add
     * @param max the maximum number of warriors the player can add
     * @return the number of warriors to add
     */
    public int askNumberOfWarriors() {
      List<Integer> options = new java.util.ArrayList<>();
      for (int i = 1; i <= ((PlayerAres) this.player).getWarriors(); i++) {
          options.add(i);
      }
      return this.lnumb.choose("How many warriors do you want to add?", options);
  }
}