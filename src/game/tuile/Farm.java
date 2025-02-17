package game.tuile;

public class Farm extends Building{

    /** Build a Farm
     * @param tuile the tile where we build the farm
     */
    public Farm(Earth tuile) {
        super(tuile);
        this.dimension = 1;
    }

}
