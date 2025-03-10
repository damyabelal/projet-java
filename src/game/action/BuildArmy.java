package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.Ressource;
import game.tuile.building.Army;

public class BuildArmy extends ActionManager implements Action<PlayerAres> {

    public BuildArmy(){
        super(player);
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.SHEEP, 1);
        this.cost.put(Ressource.WEALTH, 1);
    }

    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException {

        if (player.getWarriors() < 1) {
            throw new NoMoreRessourcesException("You need at least 1 warrior to build an Army");
        }

        if (! this.hasEnoughRessources()){
            throw new NoMoreRessourcesException("Not enough resources to build an Army");
        }

        this.removeRessources();

        player.addArmy(new Army(null, 0));

    }

}