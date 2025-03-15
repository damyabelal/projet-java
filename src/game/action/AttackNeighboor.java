package game.action;

import java.util.List;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.util.Position;
import listchooser.ListChooser;

public class AttackNeighboor extends ActionManager implements Action<PlayerAres>{

    public static ListChooser<Position> lc;

    public AttackNeighboor(PlayerAres player){
        super(player); 
    }

    public List<PlayerAres> NeighboorsList(){}

    public PlayerAres askNeighboor(){}

    public Integer howMuchDice(Player player){}

    public void act(PlayerAres player) throws NoMoreRessourcesException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'act'");        
    }
    
}
