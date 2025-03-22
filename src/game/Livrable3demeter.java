package game;

import game.action.BuildFarm;
import game.action.BuildPort;
import game.action.BuyThief;
import game.action.ExchangeRessources;
import game.action.ExchangeRessourcesPort;
import game.action.PlayThief;
import game.action.UpgradeFarm;
import game.tuile.Ressource;
import game.tuile.building.Farm;

public class Livrable3demeter {
    
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("you have to give two positive setting");
            return;
        }
        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);

        if (width < 10 || height < 10) {
            System.out.println("minimum dimensions must be 10");
            return;
        }
        System.out.println("---------------------");
        System.out.println("-------DEMETER-------");
        System.out.println("---------------------");
        
        Board board = new Board(width, height);
        board.display();

        PlayerDemeter demeter = new PlayerDemeter("demeter");

        demeter.addRessource(Ressource.WOOD, 10);
        demeter.addRessource(Ressource.ORE, 10);
        demeter.addRessource(Ressource.WEALTH, 10);
        demeter.addRessource(Ressource.SHEEP, 10);

        /*
        1- construit une ferme
        2- fait évoluer une ferme en une exploitation
        3-  construit un port
        4- échange 3 ressources contre une
        5- échange 2 ressources contre une grâce à son port
        6- achète un voleur
        */

        //1. Build a farm
        System.out.println("===> demeter veut construire une ferme");
        try {
            BuildFarm buildFarmAction = new BuildFarm(board, demeter);
            buildFarmAction.act(demeter);
        } catch (Exception e) {
            System.out.println("An error occurred while building the farm: " + e.getMessage());
        }


        //2. Upgrade a farm to an exploitation
        System.out.println("===> demeter veut faire évoluer une ferme en une exploitation");
        UpgradeFarm upgradeFarmAction = new UpgradeFarm(demeter);
        try {
            if (! demeter.getFarms().isEmpty()) {
                upgradeFarmAction.act(demeter);
            } else {
                System.out.println("No farm to upgrade");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while upgrading the farm: " + e.getMessage());
        }



        //3. build a port 
        System.out.println("===> demeter veut construire un port");
        try {
            BuildPort<PlayerDemeter> buildPortAction = new BuildPort<PlayerDemeter >(demeter, board);
            buildPortAction.act(demeter);
        } catch (Exception e) {
            System.out.println("An error occurred while building the port: " + e.getMessage());
        }




        //4. exchange 3 resources for 1
        System.out.println("===> demeter veut échanger 3 ressources contre une");
        ExchangeRessources<PlayerDemeter> exchangeRessourcesAction = new ExchangeRessources<PlayerDemeter>(demeter);
        try {
            exchangeRessourcesAction.act(demeter);
        } catch (Exception e) {
            System.out.println("An error occurred while exchanging resources: " + e.getMessage());
        }


        //5. exchange 2 resources for 1 with his port
        System.out.println("===> demeter veut échanger 2 ressources contre une grâce à son port");
        ExchangeRessourcesPort exchangeRessourcesPortAction = new ExchangeRessourcesPort(demeter);
        try {
            exchangeRessourcesPortAction.act(demeter);
        } catch (Exception e) {
            System.out.println("An error occurred while exchanging resources with the port: " + e.getMessage());
        }

        //6. buy a thief
        System.out.println("===> demeter veut acheter un voleur");
        BuyThief buyThiefAction = new BuyThief(demeter);
        try {
            buyThiefAction.act(demeter);
        } catch (Exception e) {
            System.out.println("An error occurred while buying a thief: " + e.getMessage());
        }


        //7. play a thief je sais pas si on doit faire cette action ou pas 
        /*System.out.println("===> demeter veut jouer un voleur");
        
        PlayThief playThiefAction = new PlayThief(null, null);
        try {
            if (demeter.getNbThief() == 0) {
                System.out.println("No thief to play");
                return;
            }
            playThiefAction.act(demeter);
        } catch (Exception e) {
            System.out.println("An error occurred while playing a thief: " + e.getMessage());
        }*/
        



    

    }
    
}
