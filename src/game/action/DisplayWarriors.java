package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;

/**
 * This class is used to display the warriors of every player
 * it implements Action<PlayerAres>
 */
public class DisplayWarriors implements Action<PlayerAres>{

    private PlayerAres[]  lesjoueurs;



    DisplayWarriors(PlayerAres[] lesjoueurs){
            this.lesjoueurs=lesjoueurs;
}




   
    /**
     *  displays the warriors of every player
     * @param the player who make the action
     */
    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException {
        for(int i=0; i<(this.lesjoueurs).length; i++){

            System.out.println("Number Warriors of Player"+this.lesjoueurs[i]+ "est"+ this.lesjoueurs[i].getWarriors());

        }



}

  
    
}

