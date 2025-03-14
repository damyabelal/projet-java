package game;

import java.util.ArrayList;
import java.util.List;

import game.tuile.building.Farm;
import game.tuile.building.Exploitation;

public class PlayerDemeter extends Player{

    private int points;
    private int nbThief;
    private List<Farm> farms;
    private List<Exploitation> exploitations;

    /** 
     * creates a demeter player with a name and number of points and number of thiefs that he has
     * @param name  name of the player
     */ 

    public PlayerDemeter(String name) {
            super(name);
            this.points = 0;
            this.nbThief = 0; 
            this.farms = new ArrayList<>();
            this.exploitations = new ArrayList<>();
           

    }
    /**
     * get the number of points that the demeter player has
     * @param tile
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * get the number of thief that the demeter player has
     * @param tile
     */
    public int getNbThief(){
        return this.nbThief;
    }

    /**
     * add points to the demeter player
     * @param nb int number of points to add
     */
    public void addPoints(int nb){
        this.points += 1;
    }
    /**
     * add thief to the  demeter player
     * @param nb int number of thief to add
     */

    public void addThiefs(int nb){
        this.nbThief += 1;
    }

    /**
     * get the farms of the demeter player
     * @return List<Farm> the farms of the demeter player
     */
    public List<Farm> getFarms(){
        return this.farms;
    }

    /**
     * add a farm to the demeter player
     * @param farm the farm to add
     */
    public void addFarm(Farm farm){
        this.farms.add(farm);
    }

    /**
     * remove a farm from the demeter player
     * @param farm the farm to remove
     */
    public void removeFarm(Farm farm){
        this.farms.remove(farm);
    }


    /**
     * get the exploitations of the demeter player
     * @return List<Exploitation> the exploitations of the demeter player
     */
    public List<Exploitation> getExploitations(){
        return this.exploitations;
    }

    /**
     * add an exploitation to the demeter player
     * @param exploitation the exploitation to add
     */
    public void addExploitation(Exploitation exploitation){
        this.exploitations.add(exploitation);

    }
        

    
}