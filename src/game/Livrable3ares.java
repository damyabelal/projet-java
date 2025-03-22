package game;

import java.io.IOException;
import java.util.List;
import game.tuile.Ressource;
import game.tuile.building.Army;
import game.action.*;

public class Livrable3ares {

    
    public static void main(String[] args) throws IOException {
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


        PlayerAres ares = new PlayerAres("ares");

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
        System.out.println("===> arres "+ ares.getResources()+ " ("+ ares.getWarriors()+ " warriors)  veut construire une armee avec 1 guerrier");
        BuildArmy buildArmyAction = new BuildArmy(board, ares);

        try {
            buildArmyAction.act(ares);

        } catch (Exception e) {
            System.out.println("An error occurred while building the army: " + e.getMessage());
           
        } 
        System.out.println("\n"); 

        //2. add warriors 
        System.out.println("===> arres "+ ares.getResources()+ " ("+ ares.getWarriors()+ " warriors)  ajoute des guerriers jusqu'à 5");
        DisplayWarriors displayWarriorsAction= new DisplayWarriors(ares); 
        try {
            displayWarriorsAction.act(ares);
        } catch (Exception e) {
            System.out.println("An error occurred while adding warriors: " + e.getMessage());
           
        }
        System.out.println("\n"); 

        //3. upgradeArmy 
        System.out.println("===> arres "+ ares.getResources()+ " ("+ ares.getWarriors()+ " warriors)  fait évoluer son armée en camp");
        UpgradeArmy upgradeArmyAction = new UpgradeArmy(ares);
        try {
            upgradeArmyAction.act(ares);
            
        } catch (Exception e) {
            System.out.println("An error occurred while upgrading the army to camp: " + e.getMessage());
            e.printStackTrace();
        }

        board.display();
        System.out.println("\n"); 
        //4. acheter 5 guerriers 
        System.out.println("===> arres "+ ares.getResources()+ " ("+ ares.getWarriors()+ " warriors)  achète 5 guerriers");
        try {
            ares.addWarriors(5);
        } catch (Exception e) {
            System.out.println("An error occurred while buying warriors: " + e.getMessage());
            
        }

        System.out.println("\n"); 
    
        //5. BuildPort
        System.out.println("===> arres "+ ares.getResources()+ " ("+ ares.getWarriors()+ " warriors)  construit un port");
        BuildPort<PlayerAres> buildPort = new BuildPort<PlayerAres>(ares, board);
        try {
            buildPort.act(ares);            
        } catch (Exception e) {
            System.out.println("An error occurred while building a Port: " + e.getMessage());

        } 

        board.display();
        System.out.println("\n"); 

        //6. échange 3 ressources contre une
        System.out.println("===> arres "+ ares.getResources()+ " ("+ ares.getWarriors()+ " warriors)  échange 3 WOOD contre 1 ORE");
        ExchangeRessources<PlayerAres> exchange = new ExchangeRessources<>(ares);
        try {
            exchange.act(ares);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'échange : " + e.getMessage());
        }

        System.out.println("\n"); 

        //7. achète une arme secrète
        System.out.println("===> arres "+ ares.getResources()+ " ("+ ares.getWarriors()+ " warriors)  achète une arme secrète");
        BuySecretWeapon buy = new BuySecretWeapon(ares);
        try {
            buy.act(ares);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'achat : " + e.getMessage());
        }

        System.out.println("\n"); 
    }
}
