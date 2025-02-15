package game.tuile;

import java.util.HashMap;

public class Army extends Building{

    private int nbWarriors  ;
    public static final String SYMBOL = " -> ";//" 🏹 "
    private static int nbWarriorsMax = 5;
    private HashMap<Integer, Ressource> cost;
    
    /** create an army on a given tile
    * @param tuile the tile where we build the building
    * @param nbWarriors the number of warriors
    */
    public Army(Earth tuile, int nbWarriors){
        super(tuile); 
        if (nbWarriors > nbWarriorsMax){
            this.nbWarriors = nbWarriorsMax;
        }
        else{
            this.nbWarriors = nbWarriors;
        }
    this.cost.put(1, Ressource.WOOD);
    this.cost.put(1, Ressource.SHEEP);
    this.cost.put(1,Ressource.WEALTH);

    }

    /** add a given number of warriors, 
     * @param newWarriors the number of warriors 
     */
    public void addWarwheatriors(int newWarriors){
        if (this.nbWarriors + newWarriors > Army.nbWarriorsMax){
            this.nbWarriors = Army.nbWarriorsMax;
        }
        else{
            this.nbWarriors += newWarriors;
        }
    }

    /** evolve the army into a camp */
    //public void evolveIntoCamp(){
    //    Camp camp = camp(this.nbWarriors, this.tuile);
    //}

    /** return the number of warriors
     * @return the number of warriors
     */
    public int getNbWarriors(){
        return this.nbWarriors;
    }




    /**
     * return true if the army can be a camp
     * @return boolean
     */
    public boolean canBeCamp(){
        return this.nbWarriors >= 5; 
    }

    @Override
    protected boolean canBuild() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canBuild'");
    }

    
}
