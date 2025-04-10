package game.action.actionAres;

import java.util.List;

import game.PlayerAres;
import game.action.Action;
import game.action.ActionManager;
import game.listchooser.ListChooser;
import game.tuile.building.Army;
import game.tuile.building.Camp;
import game.util.NoMoreRessourcesException;

public class DisplayWarriors extends ActionManager<PlayerAres> implements Action<PlayerAres> {

    private ListChooser<Integer> lcNumber;
    private ListChooser<String> lcString;
    private ListChooser<Army> lcArmy;
    private ListChooser<Camp> lcCamp;

    public DisplayWarriors(PlayerAres player, ListChooser<Integer> lcNumber, ListChooser<String> lcString, ListChooser<Army> lcArmy, ListChooser<Camp> lcCamp) {
        super(player);
        this.lcNumber = lcNumber;
        this.lcString = lcString;
        this.lcArmy = lcArmy;
        this.lcCamp = lcCamp;
    }

    /**
     * Asks the player how many warriors they want to add, ensuring they have enough.
     * 
     * @param player the player who performs the action
     * @return the number of warriors to add, or -1 if invalid
     */
    private int askWarrior(PlayerAres player) {
        int add = lcNumber.choose("How many warriors do you want to add?",List.of(player.getWarriors()));
        if (add > player.getWarriors() ||add <= 0) {
            System.out.println("Invalid number of warriors");
            return 0; 
        }
        return add;
    }

    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException {
        // ask if the player wants to add warriors to an army or a camp
        String choice = lcString.choose("Do you want to add warriors to an army or a camp? (army/camp)", List.of("army", "camp"));

        if ("army".equalsIgnoreCase(choice)) {
            Army army = lcArmy.choose("Which army do you want to add warriors to?", player.getArmies());
            if (army == null) {
                System.out.println("No army selected");
                return;
            }

            int add = askWarrior(player);
            // add warriors to the selected army : if army's number of warriors not > 5
            if (army.getNbWarriors() + add <= 5 ){
                player.removeWarriors(add);
                army.addWarriors(add);
                System.out.println(add + " warriors have been added to the army");
            }
            

        } else if ("camp".equalsIgnoreCase(choice)) {
            // choose a camp to add warriors to
            Camp camp = lcCamp.choose("Which camp do you want to add warriors to?", player.getCamps());
            if (camp == null) {
                System.out.println("No camp selected");
                return;
            }
            int warriorsToAdd = askWarrior(player);  
            
            player.removeWarriors(warriorsToAdd);
            camp.addWarriors(warriorsToAdd);
            System.out.println(warriorsToAdd + " warriors have been added to the camp");

        } else {
            System.out.println("Invalid choice please choose either 'army' or 'camp'");
        }
    }
    
}
