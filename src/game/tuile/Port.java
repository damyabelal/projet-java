package game.tuile;

import java.util.HashMap;

public class Port extends Building{

    public Port(Earth tuile, int dimension,HashMap<Integer, Ressource> cost){
        super(tuile);
        this.dimension = dimension;
        this.cost= cost; 
    }

    @Override
    protected boolean canBuild() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canBuild'");
    }
}