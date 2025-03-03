package game;

import java.util.*;
import game.tuile.building.*;
import game.util.*;
import game.tuile.*;

public class  PlayerAres extends Player{

        private int warriors;
        private int secretWeapon;
        
        
        // initialies a new playerares with 30 warriors , a name and zero secret weapons
        public PlayerAres(String name){
                super(name );
                this.warriors=30;
                this.secretWeapon=0;
                

        
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
    public int getWarrior(){
        return this.warriors;

    }
    /** returns the number of secret weapons owned by this player
     * @return the number of secret weapons of this player
     * 
     */
    public  int getNbSecretWeapon(){
        return this.secretWeapon;

 
    }

    /** adds the this players inventory a new secret weapon that costs one wood and one ore if the player has enough ressources for that  */
    public void buySecretWeapon(){
       if (this.ressources.getOrDefault(Ressource.ORE,0)>= 1 && (this.ressources.getOrDefault(Ressource.WOOD,0))>=1);
            this.secretWeapon+=1;
    }

    



}