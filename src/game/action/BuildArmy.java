package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.Ressource;
import game.tuile.building.Army;

public class BuildArmy implements Action<PlayerAres> {

    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException {

        if (player.getWarriors() < 1) {
            throw new NoMoreRessourcesException("You need at least 1 warrior to build an Army");
        }

        if (player.getRessourceAmount(Ressource.WOOD) < 1 ||
            player.getRessourceAmount(Ressource.SHEEP) < 1 ||
            player.getRessourceAmount(Ressource.WEALTH) < 1) {
            throw new NoMoreRessourcesException("Not enough resources to build an Army");
        }

        player.removeRessource(Ressource.WOOD, 1);
        player.removeRessource(Ressource.SHEEP, 1);
        player.removeRessource(Ressource.WEALTH, 1);

        player.addArmy(new Army(null, 0));

    }
}
