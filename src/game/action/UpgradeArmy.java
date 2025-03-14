package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
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





    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'act'");
    }





    



    
}
