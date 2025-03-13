package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.Ressource;
import game.tuile.building.Army;
import game.tuile.building.Port;

import java.util.List;

import game.Board;
import game.CantBuildException;
import game.tuile.Earth;

public class BuildArmy extends ActionManager implements Action<PlayerAres> {
    private Board board;
    public BuildArmy(){
        super(player);
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.SHEEP, 1);
        this.cost.put(Ressource.WEALTH, 1);
        this.board = board;
    }


    /**
     * Check if the player can build an army if in the island there are at least 2 buildings and 1 port
     * @param earth
     * @param player
     * @return boolean
     */
    private boolean canBuildArmy(Earth earth, PlayerAres player) {
        int cptBuild = 0;
        int cptPort = 0;
        List<Earth> island = board.getIsland(earth);
        for (Earth tuile : island){
            if(tuile.haveBuild()){

                cptBuild++;
            }
            if(tuile.getBuilding() instanceof Port){
                cptPort++;
            }

        }
        return cptBuild >=2 && cptPort >= 1 && player.hasEnoughRessources(earth.getBuilding()) ;
    }
        
   
        

    @Override
    public void act(PlayerAres player) {

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