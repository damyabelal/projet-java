package game.tuile;

public class Army extends Building{

    
   
    public Army( Earth tuile, int capacity){
        super(capacity, tuile); 
        if (capacity > 5){
            this.capacity = 5;
        }
        else{
            this.capacity = capacity;
        }
    }

    /** add a given number of warriors, 
     * @param newWarriors the number of warriors 
     */
    public void addWarriors(int newWarriors){
        this.capacity+=capacity ;
        if (this.capacity > 5){
            //this.evolveIntoCamp();
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
        return this.capacity;
    }

/// une methode pour savoir si on peut construire un camp
/// 
    /**  */
    public boolean canBeCamp(){
        return this.capacity >= 5; 
}
    
}
