package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.Ressource;
import game.tuile.building.Army;
import game.tuile.building.Port;

import java.util.HashMap;
import java.util.List;

import game.Board;
import game.CantBuildException;
import game.tuile.Earth;

public class BuildArmy extends ActionManager implements Action<PlayerAres> {
    private Board board;
    protected HashMap<Ressource,Integer> cost;
    private Earth earth;
    public BuildArmy(Board board , PlayerAres player, Earth earth ){  
        super(player);
        this.board = board;
        this.earth = earth ;


        this.cost= new HashMap<>(){
            {put(Ressource.WOOD,1);
            put(Ressource.SHEEP,1); 
            put(Ressource.ORE,1);
            
            }
        };
    };
    
    


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
        return cptBuild >=2 && cptPort >= 1 ;
    }
        
   
        

    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException, CantBuildException {
        if (player.getWarriors() < 1) {
            throw new NoMoreRessourcesException("You need at least 1 warrior to build an Army");
        }
    
        if (!this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough resources to build an Army");
        }
    
        if (!canBuildArmy(this.earth, player)) {
            throw new CantBuildException("conditions not met to build an army here");
        }
    
        this.removeRessources();
    
        try {

            Army army = new Army(null, 0, player);
            player.addArmy(army);
        
        }catch(CantBuildException e){

            throw new CantBuildException("Can't build an army here");

        }


    }}