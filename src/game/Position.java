package game;
import game.util.Direction; 


public class Position{
    //latitude
    private int x;
    //longitude
    private int y;

    /**
     * build the position
     * @param int x
     * @param int y
     */
    public Position(int x, int y){
        this.x= x;
        this.y= y;
    }

    /**
     * return the value of x
     * @return int
     */
    public int getX(){
        return this.x;
    }

    /**
     * return the value of y 
     * @return int
     */
    public int getY(){
        return this.y;
    }

    /**
     * add the the value of the direction of to x and y
     * @param Direction d, the direction we add
     * @return Position , the previous position plus the direction 
     */
    public Position next(Direction d){
        return new Position(this.x + d.getDx(),this.y + d.getDy() );
    }

    /**
     * return a description of the position
     * @return String, the description 
     */
    public String toString(){
        return "("+this.x+","+this.y+")";
    }

}