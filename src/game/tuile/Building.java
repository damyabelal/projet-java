package game.tuile;

/**
 * A class to create a Building 
 */
public class Building{
    public static String SYMBOL = " 🏡 ";
<<<<<<< HEAD
    
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
=======
    protected int capacity;
    protected Earth tuile ; /// pour savoir ou est le batiment sur qu'elle tuile il est construit
   
   
   /** create a building on a given tile
    * @param capacity the capacity oh this construction
    * @param tuile the tile where we build the building
    */
    public Building(int capacity, Earth tuile){
        this.capacity=capacity;
        this.tuile = tuile ;
>>>>>>> 12ad2401d01752abacdd38da9b50b97deb9f8b44

    // }

    /** constructeur annexe (il faut qu'on se mette d'accord svp)
     * public Building(int capacity){
     *  this.capacity= capacity;
     * }
     */

    /** return the tile where the build is
     * @return the tile
     */
    public Tuile getTuile(){
        return this.tuile;
    }

    /** return the capacity of this building
     * @return the capacity
     */
    public int getCapacity(){
     return this.capacity;
    }

    /** return the ressources
     */
    public Ressource getRessource(){
        return this.tuile.getRessource();
    }

}