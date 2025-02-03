package game.tuile;

public class Earth extends Tuile{

   protected Ressource ressource ;
   protected String symbol ;
   
   /** initialises  a new earth tile of the given type*/
   public Earth(Ressource ressource , String symbol){
    super(symbol);
    this.ressource=ressource;
    this.symbol =symbol;
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




/** adds a new  building to this tile with the given capacity  */
    public void addBuilding(int capacite){
        
}

    
}