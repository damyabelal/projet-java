package game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import game.tuile.building.*;
import game.util.Position;
import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.Tuile;

public class Player {

    private String name;
    private int warriors;
    private HashMap<Ressource, Integer> ressources;
    private ArrayList<Earth> playerTiles;

    /** 
     * creates a player with a name and initial resources
     * @param name  name of the player
     */
    public Player(String name) {
        this.name = name;
        this.warriors = 30;
        this.ressources = new HashMap<>();
        this.ressources.put(Ressource.WOOD, 0);
        this.ressources.put(Ressource.ORE, 0);
        this.ressources.put(Ressource.WEALTH, 0);
        this.ressources.put(Ressource.SHEEP, 0);
        this.playerTiles = new ArrayList<>();
    }

    /** 
     * returns the number of warriors
     * @return number of warriors
     */
    public int getWarriors() {
        return this.warriors;
    }

    /** 
     * returns the player's name
     * @return player's name
     */
    public String getName() {
        return this.name;
    }

    /** 
     * adds warriors to the army
     * @param nb number of warriors to add
     */
    public void addWarriors(int nb) {
        this.warriors += nb;
    }

    /** 
     * removes warriors from the army
     * @param nb number of warriors to remove
     * @exception NoMoreRessourcesException
     */
    public void removeWarriors(int nb) throws NoMoreRessourcesException{
        if ((this.warriors- nb) <0){
            throw new NoMoreRessourcesException("you have less than "+nb+" warriors"); 
        }
        else{
            this.warriors -= nb;
        }
    }
    /**
     * getter for playerTiles
     * @return playerTiles
     */
    public ArrayList<Earth> getPlayerTiles() {
        return this.playerTiles;
    }


    /**
     * adds a tile to the player's list of tiles
     * @param tile
     */
    public void addPlayerTile(Earth tile) {
        if(!this.playerTiles.contains(tile)){
            this.playerTiles.add(tile);
        }
    }


    /** 
     * returns the resources of player
     * @return resource map
     */
    public HashMap<Ressource, Integer> getResources() {
        return this.ressources;
    }
    
    /** 
     * adds a type of  resource to the player's stock
     * @param resource the resource to add
     * @param nb amount to add
     */
    public void addRessource(Ressource resource, int nb) {
        this.ressources.put(resource, ressources.getOrDefault(resource, 0) + nb);
    }

    /** 
     * removes a type of resource from the player's stock
     * @param ressource resource to remove
     * @param nb amount to remove
     * @exception NoMoreRessourcesException
     */
    public void removeRessource(Ressource ressource, int nb) throws NoMoreRessourcesException{
        if ((ressources.getOrDefault(ressource, 0) - nb)<0 ){
            throw new NoMoreRessourcesException("you have less than"+ nb + ressource);
        }
        this.ressources.put(ressource, ressources.getOrDefault(ressource, 0) - nb);
    }

    /** 
     * checks if the player has enough resources to build the given building 
     * @param building the building the player wants to build
     * @return true if the player has  enough resources false otherwise
     */
    public boolean hasEnoughRessources(Building building) {
        HashMap<Ressource, Integer> cost = building.getCost();
        for (Ressource resource : cost.keySet()) {
            if (ressources.getOrDefault(resource, 0) < cost.get(resource)) {
                return false;
            }
        }
        return true;
    }

    /** 
     * builds the given building if the player has enough resources
     * @param building the building to construct
     * @param tile the tile on which to construct the building
     * @return true if the building was constructed false if the player does not have enough resources to build the building
     */
    public boolean build(Building building, Earth tile) throws NoMoreRessourcesException{
        // on verifie que le joueur a suffisament de ressource pour le batiment en question
        if (! this.hasEnoughRessources(building)) {
            System.out.println("Not enough resources to build the building");
            return false; // on renvoie false car on a pas réussi
        }
        // si il en a on soustrait ces ressources de son stock, par exemple une armee qui coute 1 bois, 1 mouton, 1 blé on soustrait ces valeurs du joueur
        HashMap<Ressource, Integer> cost = building.getCost();
        for (Ressource resource : cost.keySet()) {
            removeRessource(resource, cost.get(resource));
        }
        // a la fin on place le batiment sur la tuile en question grace a la methode setBuilding()
        tile.setBuilding(building);
        System.out.println("Building has been constructed on the tile");
        return true; // on renvoie vrai car on a reussi 
    }

    /**
    * returns the amount of a specific resource the player has
    * @param resource the resource type
    * @return the amount of the resource
    */
    public int getRessourceAmount(Ressource resource) {
        return this.ressources.getOrDefault(resource, 0);
    }

    
    /**
     * 
     * @param Start
     * @param board
     * @param visited
     * @return
     */
    public List<Earth> exploreIsland(Earth Start ,Board board  , Set<Earth> visited){
        List<Earth> island = new ArrayList<>();
        Queue<Earth> queue = new LinkedList<>();
        queue.add(Start);
        visited.add(Start);
        while(!queue.isEmpty()){
            Earth current = queue.poll();
            island.add(current);
            //verifier les tuiles voisines
            for (Tuile neighbour : board.getNeighbours(current.getPosition())){
                if (neighbour instanceof Earth){
                    Earth neighbourEarth = (Earth) neighbour;
                    // ajouter a la file si elle n'a pas encore ete visitee
                    if (!visited.contains(neighbourEarth)){
                        queue.add(neighbourEarth);
                        visited.add(neighbourEarth);
                    }
                }
            }
        }
        return island;
    } 

    /**
     * 
     * @param board
     * @return
     */
    public List<List<Earth>> findIslands(Board board){
        List<List<Earth>> islands = new ArrayList<>();
        Set<Earth> visited = new HashSet<>();
        for (int x=0 ; x<board.getWidth(); x++){
            for(int y=0; y<board.getHeight(); y++){
                Tuile tile = board.getTile(new Position(x, y));
                if(tile instanceof Earth){
                    Earth earthTile = (Earth) tile ;
                    if(!visited.contains(earthTile)){
                        List<Earth> island =exploreIsland(earthTile, board, visited);
                        islands.add(island);
                    }
                }
            }
        }
        return islands;
    }
}



