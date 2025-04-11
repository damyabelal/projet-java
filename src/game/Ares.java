package game;

import java.util.ArrayList;
import java.util.List;

import game.listchooser.util.Input;

public class Ares {
    
    public static void main(String[] args){
        if (args.length < 3) {
            System.out.println("You have to give two positive settings and the number of player.");
            return;
        }

        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        int nbPlayer= Integer.parseInt(args[2]); 

        if (width < 10 || height < 10) {
            System.out.println("minimum dimensions must be 10");
            return;
        }

        if (nbPlayer < 1 ) {
            System.out.println("You should play with at least one player");
            return;
        }

        System.out.println("--------------------");
        System.out.println("--------ARES--------");
        System.out.println("--------------------");
        System.out.println("\n"); 

        Board board = new Board(width, height);

        List<PlayerAres> players= new ArrayList<>(); 
        PlayerAres winner= null; 

        //création des différents joueurs
        for (int i=0; i<nbPlayer; i++){
            String name= Input.readString(); 
            players.add(new PlayerAres(name)); 
        }

        while (winner==null){
            for (PlayerAres p: players){
                
            }
        }


    }

}
