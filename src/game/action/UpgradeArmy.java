package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.building.Army;
import game.CantBuildException;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Camp;
import listchooser.ListChooser;

public class UpgradeArmy extends ActionManager implements Action<PlayerAres>{
    public static ListChooser<Army> lc;
    private Earth tuile;
    private Army   army;

    public UpgradeArmy(PlayerAres player, Earth tuile, Army army) {
        super(player);
        this.army = ask();
        this.tuile = army.getTuile();
        this.tuile = tuile;
        this.army = army;
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.ORE, 3);


    }

    /**
     * ask the player which army he wants to upgrade
     * @return the army the player wants to upgrade
     */
    public Army ask(){
        return lc.choose("Which army do you want to upgrade?", ((PlayerAres) this.player).getArmies());
    }





    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException , CantBuildException {
        // verify if the player has enough ressources
        if (!this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough resources to upgrade the army");
        }
        

        // verify if the player can build an army
        if (!army.canBeCamp(player)){
            throw new CantBuildException("You can't build an army here");

        }
        this.removeRessources();

        // creer un camp a la place de l'armee
        Camp camp = new Camp(tuile, army.getNbWarriors(), player);
        tuile.setBuilding(camp);
        player.addCamp(camp);
        // est ce que on doit faire un removeArmy() dans playerAres ??
        



    }





    



    
}
