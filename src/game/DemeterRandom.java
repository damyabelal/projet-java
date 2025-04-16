package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import game.util.InvalidChoiceException;

public class DemeterRandom {

    public static void main(String[] args) throws IOException, InvalidChoiceException{
        if (args.length < 3) {
            System.out.println("You have to give two positive settings and the number of player.");
            return;
        }

        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        int nbPlayer= Integer.parseInt(args[2]); 

        if (width < 10 || height < 10 ) {
            System.out.println("minimum dimensions must be 10");
            return;
        }

        if (nbPlayer < 2 ) {
            System.out.println("You should play with at least two player");
            return;
        }

        System.out.println("---------------------");
        System.out.println("-------DEMETER-------");
        System.out.println("---------------------");
        System.out.println("\n"); 

        Board board = new Board(width, height);

        List<PlayerDemeter> players= new ArrayList<>(); 
        PlayerDemeter winner= null; 

        //création des différents joueurs
        for (int i=0; i<nbPlayer; i++){
            String name= "j"+(i+1); 
            players.add(new PlayerDemeter(name)); 
        }

        System.out.println("Placement initial des fermes (mode aleatoire ) : ");
        

        //Tour 1 : ordre normal
        for(int i=0 ;i<players.size();i++){
            players.get(i).placeInitialFarmRandom(board);
        }

        //Tour 2 : ordre inverse
        for(int i=players.size()-1 ;i>=0;i--){
            players.get(i).placeInitialFarmRandom(board);
        }
        //on initialise la liste des actions pour chaque joueurs
        for (PlayerDemeter p: players){
            p.createActions(board, 1);
        }

        while (winner==null){
            for (PlayerDemeter p: players){
                System.out.println(p.getName()+" ("+p.getPoints()+" points, "+p.getResources()+ ") turn!!"); 
                p.collectRessources();
                board.display();
                //on propose au joueur des actions
                p.createActions(board, 1);
                p.act(board, 1);
                //on vérifie si le joueur gagne
                if (p.getPoints() >= 12){
                    winner=p; 
                    break; 
                }
            }
        }

        System.out.println(winner.getName() +" gagne la partie!!");

    }
    
}
