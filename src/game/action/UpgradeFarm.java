package game.action;


import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Exploitation;


public class UpgradeFarm extends ActionManager implements Action<PlayerDemeter> {

    private Earth tuile;

    public UpgradeFarm(PlayerDemeter player, Earth tuile) {
        super(player);
        this.tuile = tuile;
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.WEALTH, 1);
        this.cost.put(Ressource.SHEEP, 1);
    }

    @Override
    public void act(PlayerDemeter player) throws NoMoreRessourcesException {
        if (!this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough resources to upgrade the farm");
        }
        this.removeRessources();
        Exploitation exploitation = new Exploitation(tuile, player);
        tuile.setBuilding(exploitation);
        player.addExploitation(exploitation);


    }
}