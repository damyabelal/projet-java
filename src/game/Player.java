package game;
import java.util.HashMap;

import game.tuile.Building;
import game.tuile.Earth;
import game.tuile.Ressource;

public class Player {

    private String name;
    private int warriors;
    private HashMap<Ressource, Integer> ressources;

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
     */
    public void removeWarriors(int nb) {
        this.warriors -= nb;
    }

    /** 
     * returns the resources of player
     * @return resource map
     */
    public HashMap<Ressource, Integer> getResources() {
        return this.ressources;
    }
    
    /** 
     * adds a resource to the player's stock
     * @param resource resource to add
     * @param nb amount to add
     */
    public void addRessource(Ressource resource, int nb) {
        this.ressources.put(resource, ressources.getOrDefault(resource, 0) + nb);
    }

    /** 
     * removes a resource from the player's stock
     * @param ressource resource to remove
     * @param nb amount to remove
     */
    public void removeRessource(Ressource ressource, int nb) {
        this.ressources.put(ressource, ressources.getOrDefault(ressource, 0) - nb);
    }

    /** 
     * checks if the player has enough resources to build
     * @param building building to check
     * @return true if enough resources false otherwise
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
     * @return true if the building was constructed false if the player does not have enough resources
     */
    public boolean build(Building building, Earth tile) {
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
}



