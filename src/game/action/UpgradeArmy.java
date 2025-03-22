package game.action;

import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.building.*;
import java.util.*;
import game.CantBuildException;
import game.tuile.*;
import listchooser.RandomListChooser;


public class UpgradeArmy extends ActionManager implements Action<PlayerAres> {

    public RandomListChooser<Army> lc;
    public RandomListChooser<String> lString; 
    public RandomListChooser<Integer> lnumb; 
    private Earth tuile;  

    public UpgradeArmy(PlayerAres player) {
        super(player);
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.ORE, 3);
        lc = new RandomListChooser<>();
    }

    /**
     * Ask the player which army they want to upgrade
     * @return the army the player wants to upgrade
     */
    public Army askArmy() {
        return lc.choose("Which army do you want to upgrade?", ((PlayerAres) this.player).getArmies());
    }

    /**
     * Ask the player how they want to upgrade the army
     * @return the method chosen by the player (either 'warriors' or 'resources')
     */
    public String askUpgradeMethod() {
        final RandomListChooser <String> methodChooser = new RandomListChooser<>();
        return methodChooser.choose("Do you want to upgrade by adding warriors or using resources?", List.of("warriors", "resources"));
    }

    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException, CantBuildException {
        Army chosenArmy = askArmy();
       
        // ask the player how they want to upgrade
        String method = askUpgradeMethod();

        // check if the player has an army to upgrade
        if (chosenArmy == null) {
            throw new IllegalArgumentException("No army selected");
        }
        // check if the player has enough resources
        if ("resources".equalsIgnoreCase(method)) {
            if (!this.hasEnoughRessources()) {
                throw new NoMoreRessourcesException("Not enough resources to upgrade the army");
            }
            this.removeRessources();

        } else if ("warriors".equalsIgnoreCase(method)) {
            // check if the army has 5 warriors to upgrade
            if (chosenArmy.getNbWarriors() != 5) {
                throw new CantBuildException("To upgrade an army to a camp, the army must have 5 warriors");
            }
            int add = lnumb.choose("How many warriors do you want to add?",List.of(player.getWarriors()));
            chosenArmy.addWarriors(add);

        } else {
            throw new IllegalArgumentException("Invalid upgrade method");
        }

        this.tuile = chosenArmy.getTuile(); 

        Camp camp = new Camp(this.tuile, chosenArmy.getNbWarriors() , player);

        this.tuile.removeBuilding();
        player.removeArmy(chosenArmy); 

        this.tuile.setBuilding(camp);
        player.addCamp(camp);


    }
}
