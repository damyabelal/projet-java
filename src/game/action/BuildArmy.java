package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.Ressource;
import game.tuile.Tuile;
import game.tuile.building.Army;
import game.tuile.building.Port;
import game.util.Position;
import listchooser.RandomListChooser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import game.Board;
import game.CantBuildException;
import game.tuile.Earth;


/*
 * Build an army on the island
 */
public class BuildArmy extends ActionManager implements Action<PlayerAres> {
    private Board board;
    protected HashMap<Ressource,Integer> cost;
    public static RandomListChooser<Position> lc;

   
    public BuildArmy(Board board , PlayerAres player){  
        super(player);
        this.board = board;
        this.cost= new HashMap<>(){
            {put(Ressource.WOOD,1);
            put(Ressource.SHEEP,1); 
            put(Ressource.ORE,1);
            
            }
        };
        lc= new RandomListChooser<>(); 
    };
    
    public Position askCoordinate() throws IOException {
        return lc.chooseCoordinate("Where do you want to build a Army?", this.board);
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
        return cptBuild >=2 && cptPort >= 1 ;
    }
        
   
        
    /**
     * Build an army on the island
     * @param player
     * @throws NoMoreRessourcesException
     * @throws CantBuildException
     * @throws IOException
    */
    public void act(PlayerAres player) throws NoMoreRessourcesException, CantBuildException, IOException {
        Position choosenPosition= askCoordinate();
        Tuile tile= this.board.getTile(choosenPosition); 
        if (player.getWarriors() < 1) {
            throw new NoMoreRessourcesException("You need at least 1 warrior to build an Army");
        }
    
        if (!this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough resources to build an Army");
        }
    
        if (!canBuildArmy((Earth) tile, player)) {
            throw new CantBuildException("conditions not met to build an army here");
        }
    
        this.removeRessources();
    
        try {

            Army army = new Army((Earth) tile, 1, player);
            player.addArmy(army);
        
        }catch(CantBuildException e){

            throw new CantBuildException("Can't build an army here");

        }


    }}