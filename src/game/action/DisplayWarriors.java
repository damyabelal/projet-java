package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;

public class DisplayWarriors implements Action<PlayerAres>{

    private PlayerAres[]  lesjoueurs;



    DisplayWarriors(PlayerAres[] lesjoueurs){
            this.lesjoueurs=lesjoueurs;
}
    /** displays the warriors of every player
     */
    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException {
    
    
        
    }

  
    
}

