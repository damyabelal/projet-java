package game.tuile;

public class Camp extends Army{

    public Camp(){
        super(nbWarriors, Tuile);
    }

    public getRessource(){
        return super.getRessource()*2; 
    };


}