package game;

import java.util.ArrayList;
import java.util.List;
import game.tuile.building.Army;
import game.tuile.building.Camp;

public class  PlayerAres extends Player{

        private int warriors;
        private int secretWeapon;
        private List<Army> armies;
        private List<Camp> camps;
        
        
        // initialies a new playerares with 30 warriors , a name and zero secret weapons
        public PlayerAres(String name){
                super(name );
                this.warriors=30;
                this.secretWeapon=0;
                this.armies = new ArrayList<>();
                this.camps = new ArrayList<>();



    }
    /** 
     * adds warriors to the army
     * @param nb number of warriors to add
     */
    public void addWarriors(int nb) {
        this.warriors += nb;
    }

    /** 
     * removes nb  warriors from  the players army
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

    /** return the number of warriors for this player 
     *@return number of warriors belonging this this player
     */
    public int getWarriors(){
        return this.warriors;

    }
    /** returns the number of secret weapons owned by this player
     * @return the number of secret weapons of this player
     * 
     */
    
    
     /**
      * returns the number of secret weapons owned by this player
      * @return int
      */
    public  int getNbSecretWeapon(){
        return this.secretWeapon;

 
    }
    /**
     * removes an army from the list of armies
     * @param army
     */
    public void removeArmy(Army army){
        this.armies.remove(army);
    }
     
    /**
     * adds a secret weapon to the player
     */
    public void addSecretWeapon(){
        this.secretWeapon += 1 ;
    }

   
    /**
     * returns the list of armies
     * @return List<Army>
     */
    public List<Army> getArmies(){
        return this.armies;
    }

    /**
     * adds an army to the list of armies
     * @param army
     */
    public void addArmy(Army army){
        this.armies.add(army);
    }

    /**
     * returns the list of camps
     * @return List<Camp>
     */
    public List<Camp> getCamps(){
        return this.camps;
    }

    /**
     * adds a camp to the list of camps
     * @param camp
     */
    public void addCamp(Camp camp){
        this.camps.add(camp);
    }


}