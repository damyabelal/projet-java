package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.listchooser.InteractiveListChooser;
import game.listchooser.util.Input;
import game.tuile.Earth;
import game.util.CantBuildException;
import game.util.InvalidChoiceException;
import game.util.NoMoreRessourcesException;

public class Ares {
    
    public static void main(String[] args) throws IOException, InvalidChoiceException, CantBuildException, NoMoreRessourcesException{
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

        // tir au sort un objectif pour chaque joueuer
        System.out.println("\n");
        System.out.println("Player's goals");
        for (PlayerAres p:players){
            p.givePlayersObjective();
            System.out.println(p.getName() + " goal is to "+ p.getObjective() );
        }

        for (PlayerAres p: players){
            p.createActions(board, 0);
        }


        //ici on vas laisser les joueurs construire leurs deux armées 
        System.out.println("\n");
        System.out.println("TIME TO BUILD YOUR ARMIES !");
        System.out.println("\n");
        for (int j= 0; j<2; j++){
            for( PlayerAres p: players){
                System.out.println(p.getName()+" (" +p.getResources()+"/ nb warriors: "+ p.getWarriors() + ") build your armies");
                p.placeInitialArmy(board, new InteractiveListChooser<Earth>(), new InteractiveListChooser<Integer>()); 
            }
        }

        System.out.println("\n");
        System.out.println("THE GAME START");
        System.out.println("\n");
        int i= 0; //pour compter le nombre de tour
        while (winner==null){
            System.out.println("ROUND "+ i );
            for (PlayerAres p: players){
                System.out.println(p.getName()+" (" +p.getResources()+"/ nb warriors: "+ p.getWarriors() + ") turn!!"); 
                p.collectRessources();
                board.display();
                p.createActions(board, 0);// changer la liste des actions a chaque tour
                p.act(board, 0);
                if( isWinner(p, board)){
                    winner= p; 
                    break; 
                }
            }
            i++; 
            System.out.println("\n");
        }
    }

    private static boolean isWinner(PlayerAres player, Board board){
        return player.isObjectiveAchieved(board);
    }

}
