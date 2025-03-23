package game.action;

import java.util.List;

import game.*;
import game.listchooser.RandomListChooser;
import game.tuile.*;
import game.tuile.building.*;

public class UpgradeFarm extends ActionManager implements Action<PlayerDemeter> {

    private RandomListChooser<Farm> lc;
    private Earth tuile;

    public UpgradeFarm(PlayerDemeter player) {
        super(player);
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.WEALTH, 1);
        this.cost.put(Ressource.SHEEP, 1);

        this.lc = new RandomListChooser<>();
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
        this.tuile = chosenFarm.getTuile(); 
        
        if (!this.hasEnoughRessources()) {
            throw new NoMoreRessourcesException("Not enough resources to upgrade the farm");
        }

        this.removeRessources();
        // on detruit la ferme associé a sa tuile pour la remplacer par la suite par une exploitation vu qu'on fait un upgrade sur la meme tuile
        //this.tuile.removeBuilding();
        

        chosenFarm.upGradeToExploitation(); 

        //Exploitation exploitation = new Exploitation(this.tuile, player);
        //this.tuile.setBuilding(exploitation);
        //player.addExploitation(exploitation);

        System.out.println("The farm evolved into a exploitation");


    }
}
