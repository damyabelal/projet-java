package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.listchooser.util.Input;
import game.util.CantBuildException;
import game.util.InvalidChoiceException;

public class Ares {
    
    public static void main(String[] args) throws IOException, InvalidChoiceException, CantBuildException{
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

        if (nbPlayer < 2 ) {
            System.out.println("You should play with at least two player");
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
            System.out.println("j"+(i+1)+" name:");
            String name= Input.readString(); 
            players.add(new PlayerAres(name));
             
        }

        //placement initial des armees : tour 1
        for(int i =0 ; i< players.size();i++){
            players.get(i).placeInitialArmy(board);
        }

        //placement initial des armees : tour 2
        for(int i =players.size()-1 ;i>=0;i--){
            players.get(i).placeInitialArmy(board);
        }


        // tir au sort un objectif pour chaque joueuer
        for (PlayerAres p:players){
            p.givePlayersObjective();
        }

        for (PlayerAres p: players){
            p.createActions(board, 0);
        }



        while (winner==null){
            for (PlayerAres p: players){
                System.out.println(p.getName()+" (" +p.getResources()+ ") turn!!"); 
                p.collectRessources();
                board.display();
                p.createActions(board, 0);// changer la liste des actions a chaque tour
                p.act(board, 0);
                if( isWinner(p, board)){
                    winner= p; 
                    break; 
                }
            }
        }
    }

    private static boolean isWinner(PlayerAres player, Board board){
        return player.isObjectiveAchieved(board);
    }

}
