package game;

public class PlayerDemeter extends Player{

    private int points;
    private int nbThief;

    /** 
     * creates a demeter player with a name and number of points and number of thiefs that he has
     * @param name  name of the player
     */ 

    public PlayerDemeter(String name) {
            super(name);
            this.points = 0;
            this.nbThief = 0; 

    }
    /**
     * get the number of points that the demeter player has
     * @param tile
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * get the number of thief that the demeter player has
     * @param tile
     */
    public int getNbThief(){
        return this.nbThief;
    }

    public void addPoints(int nb){
        this.points += 1;
    }

    public void addThiefs(int nb){
        this.nbThief += 1;
    }

    
}