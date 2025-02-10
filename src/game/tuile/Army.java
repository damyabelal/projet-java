package game.tuile;

public class Army extends Building{

    public int nbWarriors  ;
    public static final String SYMBOL = " -> ";//" 🏹 "
    public int nbWarriorsMax = 5;


    
    /** create an army on a given tile
     * @param tuile the tile where we build the building
     * @param nbWarriors the number of warriors
     */
    public Army( Earth tuile, int nbWarriors){
        super(dimension, tuile); 
        if (capacity > nbWarriorsMax){
            this.capacity = nbWarriorsMax;
        }
        else{
            this.nbWarriors = nbWarriors;
        }
    }

    /** add a given number of warriors, 
     * @param newWarriors the number of warriors 
     */
    public void addWarriors(int newWarriors){
        if (this.nbWarriors + newWarriors > this.nbWarriorsMax){
            this.nbWarriors = this.nbWarriorsMax;
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
        return this.capacity >= 5; 
}
    
}
