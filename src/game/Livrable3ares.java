package game;

import game.tuile.Ressource;

public class Livrable3ares {
    
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
        System.out.println("--------------------");
        System.out.println("--------ARES--------");
        System.out.println("--------------------");

        Board board = new Board(width, height);
        board.display();


        PlayerAres ares = new PlayerAres("ares");

        ares.getResources().put(Ressource.WOOD, 10);
        ares.getResources().put(Ressource.ORE, 10);
        ares.getResources().put(Ressource.WEALTH, 10);
        ares.getResources().put(Ressource.SHEEP, 10);

        /*
   - construit une armée avec 1 guerrier
   - ajoute des guerriers à son armée pour arriver à 5
   - fait évoluer son armée en un camp
   - achète 5 guerriers
   - construit un port
   - échange 3 ressources contre une
   - achète une arme secrète
         */
    }
    

}



