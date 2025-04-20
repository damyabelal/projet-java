package game.action.actionAres;

import java.util.ArrayList;
import java.util.List;

import game.PlayerAres;
import game.action.Action;
import game.action.ActionManager;
import game.listchooser.ListChooser;
import game.tuile.building.Army;
import game.tuile.building.Camp;
import game.util.NoMoreRessourcesException;
import game.util.InvalidChoiceException;

public class DisplayWarriors extends ActionManager<PlayerAres> implements Action<PlayerAres> {

    private ListChooser<Integer> lcNumber;
    private ListChooser<Army> lcArmyCamp;

    public DisplayWarriors(PlayerAres player, ListChooser<Integer> lcNumber, ListChooser<Army> lcArmyCamp ) {
        super(player);
        this.lcNumber = lcNumber;
        this.lcArmyCamp = lcArmyCamp;
    }

    /**
     * @return the description of the action
     */
    public String toString() {
        return "Display warriors";
    }

    /**
     * Asks the player how many warriors they want to add, ensuring they have
     * enough.
     * 
     * @param player the player who performs the action
     * @return the number of warriors to add, or -1 if invalid
     * @throws InvalidChoiceException
     */
    private int askWarrior(PlayerAres player) throws InvalidChoiceException {
        List<Integer> warriorChoices = new ArrayList<>();
        for (int i = 1; i <= player.getWarriors(); i++) {
            warriorChoices.add(i);
        }
        Integer add = lcNumber.choose("How many warriors do you want to add?", warriorChoices);

        if (add == null) {
            throw new InvalidChoiceException("Action cancelled : No warriors selected");
        }
        return add;
    }

    @Override
public void act(PlayerAres player) throws NoMoreRessourcesException, InvalidChoiceException {
    List<Army> allArmiesAndCamps = new ArrayList<>();

    for (Army army : player.getArmies()) {
        allArmiesAndCamps.add(army);
    }
    for (Camp camp : player.getCamps()) {
        allArmiesAndCamps.add(camp); 
    }

    Army selection = lcArmyCamp.choose("Choose an army or a camp to add warriors to:", allArmiesAndCamps);

    if (selection == null) {
        throw new InvalidChoiceException("Action cancelled: No army or camp selected");
    }

    int warriorsToAdd = askWarrior(player);

    if (selection instanceof Camp) {
        player.removeWarriors(warriorsToAdd);
        selection.addWarriors(warriorsToAdd);
        System.out.println(warriorsToAdd + " warriors have been added to the camp.");
    } else {
        if (selection.getNbWarriors() + warriorsToAdd > 5) {
            throw new InvalidChoiceException("Cannot add that many warriors. Maximum per army is 5.");
        }
        player.removeWarriors(warriorsToAdd);
        selection.addWarriors(warriorsToAdd);
        System.out.println(warriorsToAdd + " warriors have been added to the army.");
    }
}
}