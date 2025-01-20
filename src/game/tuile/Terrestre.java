package game.tuile;

public class Terrestre extends Tuile{

   protected Ressource ressource ;
   
   /** initialises  a new earth tile of the given type*/
   public Terrestre(Ressource ressource){
    super();
    this.ressource=ressource;
    }

/** returns the ressources of this tile
 * @return string which is the type of this tile
 */
    public Ressource getRessource(){
        return this.ressource;
    }

/** returs true if tis tile has a building , False otherwise
 * @return true of this tile has a building 

public boolean haveBuild(){}

*/




/** adds a new building to this tile */
    public void addBuilding(){

    }

    
}