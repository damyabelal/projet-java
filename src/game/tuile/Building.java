package game.tuile;

/**
 * A class to create a Building 
 */
public abstract class Building{
    public int dimension = 1;
    public static final String SYMBOL = " 🏡 ";
    protected int capacity;
    protected Earth tuile ; /// pour savoir ou est le batiment sur qu'elle tuile il est construit
   
   
   /** create a building on a given tile
    * @param capacity the capacity oh this construction
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
     return this.capacity;
    }

    /** return the ressources
     */
    public Ressource getRessource(){
        return this.tuile.getRessource();
    }

}