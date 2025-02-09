package game.tuile;

public class Army extends Building{
    
    public int nbWarriors;
    public int tuile;
   
    public Army(int nbWarriors, Tuile tuile){
        super(tuile); // il a la meme tuile que le building (je comprends pas pourquoi y a une erreur )
        
        
        /// faut verfier il est entre 1 et 5
        if (nbWarriors > 5){
            this.nbWarriors = 5;
        }
        else{
            this.nbWarriors = nbWarriors;
        }
        

    }

    /** add a given number of warriors, 
     * @param nbWarriors
     */
    public void addWarriors(int newWarriors){
        this.nbWarriors+=newWarriors ;
        if (this.nbWarriors > 5){
            evolveIntoCamp();
        }
    }

    /** evolve the army into a camp */
    public void evolveIntoCamp(){
        Camp camp = camp(this.nbWarriors, this.tuile);
    }

    /** return the number of warriors
     * @return the number of warriors
     */
    public int getNbWarriors(){
        return this.nbWarriors;
    }

/// une methode pour savoir si on peut construire un camp
/// 
    /**  */
    public boolean canBeCamp(){
        if(this.nbWarriors==5){
            return true;
        }
        else{
            return false;
        }
}
    
}
