package game.tuile;

/**
 * A class to create a Building 
 */
<<<<<<< HEAD
public class Building{
    public static final String SYMBOL = " B ";
    protected int dimension;
=======
public abstract class Building{
    public int dimension = 1;
    public static final String SYMBOL = " 🏡 ";
    protected int capacity;
>>>>>>> e3c6b0a63519935f5dae3239cc2e4f7907856e7c
    protected Earth tuile ; /// pour savoir ou est le batiment sur qu'elle tuile il est construit
   
   
   /** create a building on a given tile
    * @param di the capacity oh this construction
    * @param tuile the tile where we build the building
    */
    public Building(int dimension, Earth tuile){
        this.dimension=dimension;
        this.tuile = tuile ;

    }

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
     return this.dimension;
    }

    /** return the ressources
     */
    public Ressource getRessource(){
        return this.tuile.getRessource();
    }

}