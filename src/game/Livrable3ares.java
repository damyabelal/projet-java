package game;

import java.util.List;

import game.tuile.Earth;
import game.tuile.Ressource;
import game.tuile.building.Army;

public class Livrable3ares {
    
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("You have to give two positive settings.");
            return;
        }
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);

        if (width < 10 || height < 10) {
            System.out.println("minimum dimensions must be 10");
            return;
        }
        System.out.println("--------------------");
        System.out.println("--------ARES--------");
        System.out.println("--------------------");

        Board board = new Board(width, height);
        board.display();


        PlayerAres ares = new PlayerAres("ares",board);

        ares.addRessource(Ressource.WOOD, 10);
        ares.addRessource(Ressource.ORE, 10);
        ares.addRessource(Ressource.WEALTH, 10);
        ares.addRessource(Ressource.SHEEP, 10);

        /*
        1- construit une armée avec 1 guerrier
        2- ajoute des guerriers à son armée pour arriver à 5
        3- fait évoluer son armée en un camp
        4- achète 5 guerriers
        5- construit un port
        6- échange 3 ressources contre une
        7- achète une arme secrète
         */

        // 1. Build an army with 1 warrior
        try {
            System.out.println("===> arres veut construire une armee avec 1 guerrier");
            Earth tile1 = board.getRandomBuildableTile();
            Army army = new Army(tile1, 1, ares);
            ares.addArmy(army);
        } catch (Exception e) {
            System.out.println("An error occurred while building the army: " + e.getMessage());
            e.printStackTrace();
        }

        //2. add warriors 
        System.out.println("----> ares ajoute des guerriers jusqu'à 5");
        try {
            List<Army> armies = ares.getArmies();
            if (!armies.isEmpty()) {
            Army army = armies.get(0);
            army.addWarriors(4);
            } else {
            System.out.println("No armies found to add warriors to.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while adding warriors: " + e.getMessage());
            e.printStackTrace();
        }

        //3. upgradeArmy 
        

    }
    

}



