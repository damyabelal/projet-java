package game;

import game.tuile.Ressource;

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

        demeter.getResources().put(Ressource.WOOD, 10);
        demeter.getResources().put(Ressource.ORE, 10);
        demeter.getResources().put(Ressource.WEALTH, 10);
        demeter.getResources().put(Ressource.SHEEP, 10);

        /*
    - construit une ferme
    - fait évoluer une ferme en une exploitation
    - construit un port
    - échange 3 ressources contre une
    - échange 2 ressources contre une grâce à son port
    - achète un voleur
         */


    

    }
    
}
