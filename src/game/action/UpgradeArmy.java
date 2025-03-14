package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Camp;

public class UpgradeArmy extends ActionManager implements Action<PlayerAres>{

    private Earth tuile; 

    public UpgradeArmy(PlayerAres player, Earth tuile){
        super(player);
        this.tuile= tuile;
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.ORE, 3);
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
