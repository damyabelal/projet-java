package game.action;

import game.*;
import game.tuile.*;
import game.tuile.building.*;
import game.util.Position;
import game.listchooser.RandomListChooser;

import java.io.IOException;
import java.util.*;

/*
 * Builds an army on the island
 */
public class BuildArmy extends ActionManager implements Action<PlayerAres> {
    private Board board;
    public static RandomListChooser<Position> lc;
    public List<Earth> earthList;

   
    public BuildArmy(Board board , PlayerAres player){  
        super(player);
        this.board = board;
        this.cost.put(Ressource.WOOD,1);
        this.cost.put(Ressource.SHEEP,1); 
        this.cost.put(Ressource.WEALTH,1);
        lc= new RandomListChooser<>(); 
        earthList = new ArrayList<>();

    }



    
    public Position askCoordinate() throws IOException {
        return lc.chooseCoordinate("Where do you want to build an Army?", this.board);
    }


    /**
     * Checks if the player can build an army , which means if he has at least a port and two building placed on an island
     * @param earth
     * @param player
     * @return boolean
     */
    public  boolean canBuildArmy(Earth earth, PlayerAres player) {
        int cptBuild = 0;
        int cptPort = 0;
        List<Earth> island = board.getIsland(earth);
<<<<<<< HEAD
        
        for (Earth tuile : island){
=======
        for (Earth tuile : island){ 
>>>>>>> ec21b9f89ef0738b2e0ea5964580349a6c529a8d
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
        Earth tile= (Earth) this.board.getTile(choosenPosition); 

        // plus tard il faudra demander au joueur combien de warriors il veut ajouter


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
        // 1 par défaut, à faire évoluer plus tard
        Army army = new Army((Earth) tile, 1, player);

        player.addArmy(army);
        tile.setBuilding(army); 

        // plein de 1 à changer plus tard ici aussi
        player.removeWarriors(1); 

        System.out.println(player.getName() +": "+player.getResources()+ " build a army with 1 warrior on position "+ choosenPosition);
    
        player.addArmy(army);
            


    }}