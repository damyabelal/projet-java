package game.tuile;

public abstract class Tuile {
    public String symbol ; /// symbol of the tile

    /**
     * Constructor for Tuile.
     *
     * @param symbol The symbol representing this tile.
     */    
    public Tuile(String symbol) {
        this.symbol =symbol ;

    }
    /**
     * Gets the symbol of the tile.
     *
     * @return the symbol of the tile.
     */
    public String getSymbol() {
        return symbol;
    }   
}
