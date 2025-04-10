package game.action.actionAres;

import game.PlayerAres;
import game.action.Action;
import game.action.ActionManager;
import game.listchooser.ListChooser;
import game.tuile.building.Army;
import java.util.List;

import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Camp;
import game.util.CantBuildException;
import game.util.NoMoreRessourcesException;


public class UpgradeArmy extends ActionManager<PlayerAres> implements Action<PlayerAres> {
    
    private Earth tuile; 
    private ListChooser<Army> lc;
    private ListChooser<String> lString;  
    private ListChooser<Integer> lnumb;

    public UpgradeArmy(PlayerAres player, ListChooser<Army> lc, ListChooser<String> lString, ListChooser<Integer> lnumb) {
        super(player);
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.ORE, 3);
        this.lString = lString;
        this.lnumb = lnumb ;
        this.lc = lc;
       
    }

    /**
     * Asks the player which army he wants to upgrade
     * @return the army the player wants to upgrade
     */
    public Army askArmy() {
        List<Army> armies = ((PlayerAres) this.player).getArmies();
        if (armies.isEmpty()) {
            throw new IllegalArgumentException("No armies available to upgrade");
    }
        return this.lc.choose("Which army do you want to upgrade?", armies);
    }
    /**
     * asks the player how many warriors ther want to add
     * @param max the maximum number of warriors the player can add
     * @return the number of warriors to add
     */
    public int askNumberOfWarriors() {
        List<Integer> options = new java.util.ArrayList<>();
        for (int i = 1; i <= ((PlayerAres) this.player).getWarriors(); i++) {
            options.add(i);
        }
        return this.lnumb.choose("How many warriors do you want to add?", options);
    }

    /**
     * Asks the player how they want to upgrade the army: by adding warriors or by using ressources
     * @return the method chosen by the player (either 'warriors' or 'resources')
     */
    public String askUpgradeMethod() {
        String res=  this.lString.choose("Do you want to upgrade by adding warriors or using resources?", List.of("warriors", "resources"));
        System.out.println(res);
        return res; 
    }

    @Override
    public void act(PlayerAres player) throws NoMoreRessourcesException, CantBuildException {
        Army chosenArmy = askArmy();

        // asks the player how he wants to upgrade the army 
        String method = askUpgradeMethod();

        // checks if the player has  an army to upgrade
        if (chosenArmy == null) {
            throw new IllegalArgumentException("No army selected");
        }
        // checks if the player has enough ressources
        if ("resources".equalsIgnoreCase(method)) {
            if (!this.hasEnoughRessources()) {
                throw new NoMoreRessourcesException("Not enough resources to upgrade the army");
            }
            this.removeRessources();

        } else if ("warriors".equalsIgnoreCase(method)) {
            // check if the army has 5 warriors to upgrade
            if (chosenArmy.getNbWarriors() < 5) {
                throw new CantBuildException("To upgrade an army to a camp, the army must have 5 warriors");
            }
            int add = askNumberOfWarriors();
            if (player.getWarriors() < add){
                throw new CantBuildException("To upgrade an army to a camp, you must have enough warriors in stock");
             }
            chosenArmy.addWarriors(add);
            player.removeWarriors(add);

        } else {
            throw new IllegalArgumentException("Invalid upgrade method");
        }

        this.tuile = chosenArmy.getTuile(); 

        this.tuile.removeBuilding();
        player.removeArmy(chosenArmy); 

        Camp camp = chosenArmy.upGradeToCamp(player);
        
        this.tuile.setBuilding(camp);
        player.addCamp(camp);


        System.out.println("The army evolved into a camp ("+ chosenArmy.getNbWarriors()+" warriors)");

    }
    
}
