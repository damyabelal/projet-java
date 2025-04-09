package game.action;

import game.NoMoreRessourcesException;
import game.Player;
import game.PlayerAres;
import game.listchooser.RandomListChooser;
import game.tuile.building.Army;
import java.util.List;
import game.CantBuildException;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Camp;

public class UpgradeWithWarriors extends ActionManager implements Action<PlayerAres> {

  private Army army;
  private RandomListChooser lString;
  private RandomListChooser lnumb;
  private RandomListChooser lc;

  // army is the army that the given player player wants to upgrade
  public UpgradeWithWarriors(Player player,Army army) {
    super(player);
    this.army=army;
    this.cost.put(Ressource.WOOD, 2);
    this.cost.put(Ressource.ORE, 3);
    this.lString = new RandomListChooser<>();
    this.lnumb = new RandomListChooser<>();
    this.lc = new RandomListChooser<>();
   
  }

}