package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
<<<<<<< HEAD
import game.tuile.building.Army;

import game.tuile.Earth;
import game.tuile.Ressource;

public class UpgradeArmy extends ActionManager implements Action<PlayerAres>{

    private Earth tuile;
    private Army   army;

    public UpgradeArmy(PlayerAres player, Earth tuile) {
        super(player);
        this.tuile = tuile;
        this.army = army;
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.ORE, 3);
    }




=======
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Army;
import game.tuile.building.Camp;
import game.tuile.building.Farm;

public class UpgradeArmy extends ActionManager implements Action<PlayerAres>{
>>>>>>> 1be6b5da42e19222f5513c5d49aaa86b91425d24

    private Earth tuile; 

    public UpgradeArmy(PlayerAres player, Earth tuile){
        super(player);
        this.tuile= ask().getTuile();
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.ORE, 3);
    }

    public Army ask(){
        return lc.choose("Which army do you want to upgrade?", this.player.getArmies());
    }

    public void act(PlayerAres player) throws NoMoreRessourcesException {
        if (!this.hasEnoughRessources()){
            throw new NoMoreRessourcesException("Not enough ressources to upgrade this army");
        }
        this.removeRessources();
        Camp camp= new Camp(tuile, 0, player); //???
        tuile.setBuilding(camp);
        player.addCamp(camp);
    }

    
}
