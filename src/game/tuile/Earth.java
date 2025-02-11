package game.tuile;

public class Earth extends Tuile{

   protected Ressource ressource ;
   protected String symbol ;
   protected int quantiteRessource;// j'ai met cet attribut car chaque tuile a une quantité de ressource qui 
                                    // egale a 0 au depart mais qui augmente a chaque tour de jeu
    protected Building building; // chaque tuile a au plus un batiment






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
        return this.ressource;
    }


/** return the quantity of ressource of this tile
 * @return quantity of ressource of this tile
 */
    public int getQuantiteRessource(){
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
        this.building= new Building(capacity);   
        //  maintenant un building (si il y en a) est relier a une tuile et pour recupere le
        // building d'une tuile est suffit de faire tuile.getBuilding()



    };

/** returns the building of the this tile if there is one */
    public Building getBuilding() throws  Exception{
        Building build=null;
       try{ build=this.building;
       }
       catch(Exception e) { 
        System.out.println("There is no Building placed on this tile.");
       }
       return build;
    }



     //public boolean haveEnoughRessources(){
        //TO DO
    //}

    
}