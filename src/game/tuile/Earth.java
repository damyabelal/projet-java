package game.tuile;

public class Earth extends Tuile{

   protected Ressource ressource ;
   protected String symbol ;
   protected int quantiteRessource;// j'ai met cet attribut car chaque tuile a une quantité de ressource qui 
                                    // egale a 0 au depart mais qui augmente a chaque tour de jeu
   
   /** initialises  a new earth tile of the given type
    * @param ressource the ressource
    * @param symbol the symbol of the tile
   */
   public Earth(Ressource ressource , String symbol){
    super(symbol);
    this.ressource=ressource;
    this.symbol =symbol;
    this.quantiteRessource=0; // quand on construit une tuile elle possede au depart 0 ressouces

    }

/** returns the ressources of this tile
 * @return string which is the type of this tile
 */
    public Ressource getRessource(){
        return this.quantiteRessource;
    }

/** adds a number of the tile's  ressources to the already existing quantity of ressources
 * 
  */
    public void addRessource(int nbre){
        this.quantiteRessource+=nbre; // on a besoind de cette methode pour augmenter le nombre de 
                                        // ressource de chaque tuile a chaque tour de jeu
        
    }



 /** returs true if tis tile has a building , False otherwise
 * @return true of this tile has a building 

public boolean haveBuild(){}

*/




/** adds a new  building to this tile with the given capacity  
 * @param capacity the capacity of the building
*/
    public void addBuilding(int capacity){
        


    };


     public boolean haveEnoughRessources(){
        //TO DO
    }

    
}