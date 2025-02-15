package game.tuile;

import java.util.HashMap;

public class Port extends Building{

    private Tuile tuile;
    private int dimension;
    private String symbol;
    private HashMap<Integer,Ressource> cost;

    public Port(){
        super(tuile, dimension, symbol, cost);
    }

    @Override
    protected boolean canBuild() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canBuild'");
    }
}