package game.action;

import java.util.List;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.listchooser.RandomListChooser;
import game.tuile.building.Army;
import game.tuile.building.Camp;

public class DisplayWarriors extends ActionManager implements Action<PlayerAres> {

    private static RandomListChooser<Integer> lcNumber;
    private static RandomListChooser<String> lcString;
    private static RandomListChooser<Army> lcArmy;
    private static RandomListChooser<Camp> lcCamp;

    public DisplayWarriors(PlayerAres player) {
        super(player);
        lcNumber = new RandomListChooser<>();
        lcString = new RandomListChooser<>();
        lcArmy = new RandomListChooser<>();
        lcCamp = new RandomListChooser<>();
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
            return -1; 
        }
        return add;
    }

    /**
     * Displays the warriors in army or camp
     * This allows the player to add warriors to an army or camp
     * 
     * @param player the player who performs the action
     */
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

            //int add = askWarrior(player);  
            //pour le livrable 3 on poseras:
            int add= 5; 
            if (add == -1) return; 

            // add warriors to the selected army
            player.removeWarriors(add);
            army.addWarriors(add);
            System.out.println(add + " warriors have been added to the army");

        } else if ("camp".equalsIgnoreCase(choice)) {
            // choose a camp to add warriors to
            Camp camp = lcCamp.choose("Which camp do you want to add warriors to?", player.getCamps());
            if (camp == null) {
                System.out.println("No camp selected");
                return;
            }
            int warriorsToAdd = askWarrior(player);  
            if (warriorsToAdd == -1) return;  

            player.removeWarriors(warriorsToAdd);
            camp.addWarriors(warriorsToAdd);
            System.out.println(warriorsToAdd + " warriors have been added to the camp");

        } else {
            System.out.println("Invalid choice please choose either 'army' or 'camp'");
        }
    }
}
