package game.tuile;
import game.util.*;

/**
 * A class to create a Building 
 */
public class Building{

    private int capacity;
    private Position pos;

/** initiats a new instance of building with the given capacity  */
    public Building(int capacity){
        this.capacity=capacity; // de base je voulais mettre la position d'une tuile dans le constructeur de
                                // building pour pouvoir relier un building a une tuile mais je pense que creer
                                // creer un attribut building dans earth est plus efficace et plus simple
        

    }



 public int getCapacity(){
     return this.capacity;
    }




    //public boolean canBuild(){
        //TO DO
    //}





}