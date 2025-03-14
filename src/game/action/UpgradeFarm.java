package game.action;


import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Building;
import game.tuile.building.Exploitation;
import game.tuile.building.Farm;
import listchooser.ListChooser;


public class UpgradeFarm extends ActionManager implements Action<PlayerDemeter> {

    public static ListChooser<Farm> lc;
    private Earth tuile;

    public UpgradeFarm(PlayerDemeter player, Earth tuile) {
        super(player);
        this.tuile = ask().getTuile();
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.WEALTH, 1);
        this.cost.put(Ressource.SHEEP, 1);
    }

    public Farm ask(){
        return lc.choose("Which farm do you want to upgrade?", this.player.getFarms());
    }

    @Override
    public void act(PlayerDemeter player) throws NoMoreRessourcesException {
        if (!this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough resources to upgrade the farm");
        }
        this.removeRessources();
        Exploitation exploitation = new Exploitation(this.tuile, player);
        tuile.setBuilding(exploitation);
        player.addExploitation(exploitation);


    }
}