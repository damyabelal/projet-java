package game.tuile;
import game.util.*;

/**
 * A class to create a Building 
 */
public class Building{
    public static String SYMBOL = " 🏡 ";
    
    private int capacity;
    private Position pos;

    // private Tuile tuile ; /// pour savoir ou est le batiment sur qu'*elle tuile il est construit
/** initiats a new instance of building with the given capacity  */
    public Building(int capacity){
        this.capacity=capacity; // de base je voulais mettre la position d'une tuile dans le constructeur de
                                // building pour pouvoir relier un building a une tuile mais je pense que creer
                                // creer un attribut building dans earth est plus efficace et plus simple
        
   
   
   
    // public Building(int capacity, Tuile tuile){
    //     this.capacity=capacity;
    //     this.tuile = tuile ;

    // }

    // pour savoir ou est le batiment ou sur qu'elle tuile 
    public Tuile getTuile(){
        return this.tuile;
    }


 public int getCapacity(){
     return this.capacity;
    }




    //public boolean canBuild(){
        //TO DO
    //}





}}