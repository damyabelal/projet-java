package game.tuile;
import game.util.*;

/**
 * A class to create a Building define by 
 */
public class Building{
    public static String SYMBOL = " 🏡 ";
    
    private int capacity;
    private Tuile tuile ; /// pour savoir ou est le batiment sur qu'*elle tuile il est construit
   
   
   
    public Building(int capacity, Tuile tuile){
        this.capacity=capacity;
        this.tuile = tuile ;

    }

    // pour savoir ou est le batiment ou sur qu'elle tuile 
    public Tuile getTuile(){
        return this.tuile;
    }


//    public int getCapacity(){
  //      return this.capacity;
    //}


   // public boolean haveEnoughRessources(){
        //TO DO
    //}

    //public boolean canBuild(){
        //TO DO
    //}





}