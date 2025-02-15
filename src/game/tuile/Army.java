package game.tuile;
import java.util.HashMap;

public class Army extends Building{

    protected int nbWarriors  ;
    private static final String SYMBOL = " -> ";        //" 🏹 "
    private static int nbWarriorsMax = 5;

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
        this.dimension = this.nbWarriors;
        this.symbol = SYMBOL;
        this.cost = new HashMap<>();
        this.cost.put(1, Ressource.WOOD);
        this.cost.put(1, Ressource.SHEEP);
        this.cost.put(1,Ressource.WEALTH);

    }

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
        return this.getNbWarriors() > 5;
    }


    /** add a given number of warriors, 
     * @param newWarriors the number of warriors 
     */
    public void addWarriors(int newWarriors){
        if (this.getNbWarriors() + newWarriors > nbWarriorsMax){
            this.nbWarriors = nbWarriorsMax;
        }
        else{
            this.nbWarriors += newWarriors;
        }
    }

    /** evolve the army into a camp */
    public Camp upGradeToCamp(){
        if (this.canBeCamp()){
            Camp camp = new Camp(this.getTuile(), this.getNbWarriors());
            System.out.println("Army evolved into a camp");
            return camp;
        } else {
            System.out.println("Not enough Warriors or not enough Ressources");
        }
        return null;
    }



    @Override
    protected boolean canBuild() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canBuild'");
    }

    
}
