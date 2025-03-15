package game.action;

import java.util.List;

import game.NoMoreRessourcesException;
import game.Player;
import game.PlayerAres;
import game.util.Position;
import listchooser.ListChooser;

public class AttackNeighboor extends ActionManager implements Action<PlayerAres>{

    public static ListChooser<Position> lc;
    public List<PlayerAres> ennemies;

    public AttackNeighboor(PlayerAres player, List<PlayerAres> ennemies){
        super(player); 
        this.ennemies= ennemies;
    }

    public PlayerAres askNeighboor(){
        return lc.choose("Who de you want to attack", this.ennemies);
    }

    public Integer howMuchDice(Player player){
        return 0;
    }

    public void act(PlayerAres player) throws NoMoreRessourcesException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'act'");        
    }
    
}
