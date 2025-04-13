package game.action.actionDemeter;

import java.util.List;

import game.*;
import game.action.Action;
import game.action.ActionManager;
import game.listchooser.ListChooser;
import game.tuile.*;
import game.tuile.building.*;
import game.util.NoMoreRessourcesException;

public class UpgradeFarm extends ActionManager<PlayerDemeter> implements Action<PlayerDemeter> {

    private ListChooser<Farm> lc;
    private Earth tuile;

    public UpgradeFarm(PlayerDemeter player, ListChooser<Farm> lc) {
        super(player);
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.WEALTH, 1);
        this.cost.put(Ressource.SHEEP, 1);
        this.lc =lc;
    }

    public Farm ask() {
        List<Farm> farms = ((PlayerDemeter) this.player).getFarms();
        if (farms.isEmpty()) {
            throw new IllegalArgumentException("No farms available to upgrade.");
        }
        return lc.choose("Which farm do you want to upgrade?", farms);
    }

    @Override
    public void act(PlayerDemeter player) throws NoMoreRessourcesException {
        Farm chosenFarm = ask();

        if (chosenFarm == null) {
            System.out.println("No farm selected");
            return;  
        }
        
        
        if (!this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough resources to upgrade the farm \n cost: "+this.cost);
        }

       
        // on detruit la ferme associé a sa tuile pour la remplacer par la suite par une exploitation vu qu'on fait un upgrade sur la meme tuile
       
        
        
        Exploitation exploitation = chosenFarm.upGradeToExploitation(player);
        if (exploitation == null){
            return;
        }
        this.removeRessources();
        this.tuile = chosenFarm.getTuile();
        this.tuile.removeBuilding(); 
        player.removeFarm(chosenFarm);

        this.tuile.setBuilding(exploitation);
        player.addExploitation(exploitation);
 
        player.addPoints(2); 
        System.out.println("The farm evolved into a exploitation");


    }
}
