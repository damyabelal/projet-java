package game.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.util.ArrayList;

import game.Board;
import game.CantBuildException;
import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.Earth;
import game.tuile.Field;
import game.tuile.Forest;
import game.tuile.Ressource;
import game.tuile.Sea;
import game.tuile.building.Camp;
import game.tuile.building.Farm;
import game.tuile.building.Port;
import game.util.Position;
import game.action.*;
public class BuildArmyTest{
    
       
        private Board board; 
        private PlayerAres player; 
        private Action<PlayerAres> action; 
        private Action<PlayerAres> actionBuild; 
        // creation de tuile qui vont servir pour creer une ile de le board parceque comme le board est creer de facon  aleatoire on 
        //veut etre sur que il y a une ile qui respecte les conditions pour poser une armee sur le plateau
       
    
        private Camp camp1;
        private Camp camp2;
    
    
        @BeforeEach
        void setUp() throws CantBuildException{
            
            board= new Board(5, 5); //creation d'un plateau de maniere aleatoire
            player = new PlayerAres("Ares");
                        board = new Board(5, 5);
            for (int x=0; x<5; x++){
                for (int y=0; y<5; y++){
                    board.put(new Sea(), new Position(x, y));
                    }
            }

            //mettre une tuile Earth au centre de 4 tuiles Earth
            Position pos = new Position(2, 2);
            board.put(new  Earth(Ressource.ORE,null), pos);

            Position pos1 = new Position(2, 1);
            board.put(new  Earth(Ressource.ORE,null), pos1);

            Position pos2 = new Position(1, 2);
            board.put(new  Earth(Ressource.ORE,null), pos2);
            
            Position pos3 = new Position(3, 2);
            board.put(new  Earth(Ressource.ORE,null), pos3);
    

            Position pos4 = new Position(2, 3);
            Earth earth = new Earth(Ressource.ORE, null);
            board.put(earth, pos4);
           
           

            

            Port port = new Port((Earth)board.getTile(pos), player);
            board.put(port.getTuile(), pos);

            
            player.addRessource(Ressource.WOOD,1);
            player.addRessource(Ressource.SHEEP, 1);
            player.addRessource(Ressource.WEALTH, 1);



           
            
            
            
            
        }
        
        @Test
        void BuildTest() throws NoMoreRessourcesException, CantBuildException, IOException{
            board= new Board(5, 5); //creation d'un plateau de maniere aleatoire
            player = new PlayerAres("Ares");
                        
            for (int x=0; x<5; x++){
                for (int y=0; y<5; y++){
                    board.put(new Sea(), new Position(x, y));
                    }
            }

            //mettre une tuile Earth au centre de 4 tuiles Earth
            Position pos = new Position(2, 2);
            board.put(new  Earth(Ressource.ORE,null), pos);

            Position pos1 = new Position(2, 1);
            board.put(new  Earth(Ressource.ORE,null), pos1);

            Position pos2 = new Position(1, 2);
            board.put(new  Earth(Ressource.ORE,null), pos2);
            
            Position pos3 = new Position(3, 2);
            board.put(new  Earth(Ressource.ORE,null), pos3);
    

            Position pos4 = new Position(2, 3);
            Earth earth = new Earth(Ressource.ORE, null);
            board.put(earth, pos4);
           
           

            Port port = new Port((Earth)board.getTile(pos), player);
            board.put(port.getTuile(), pos);  
            
            board.islands= board.findIslands();

            BuildArmy actionBuild = new BuildArmy(board, player);

            player.addRessource(Ressource.WOOD,1);
            player.addRessource(Ressource.SHEEP, 1);
            player.addRessource(Ressource.WEALTH, 1);


            assertTrue(player.getArmies().isEmpty());
            actionBuild.act(player);
            //  the player player should have a port in his inventory of ports
            assertTrue( player.getPorts().size() == 1);
            // initialising a list that would store both the player's camps
            ArrayList<Camp> listcamps= new ArrayList<>();
            listcamps.add(camp1);
            listcamps.add(camp2);
            assertTrue(player.getCamps()==listcamps);
            // en fait il faut ajouter dur la meme ile et c'est pas forcément le cas car ici je les
            // pose aléatoirement, je m'en occupe plus tard
            action.act(player);
            assertTrue(! player.getArmies().isEmpty());
        }
        
        @Test
        void canBuildArmyTest(){
            board = new Board(5, 5);
            for (int x=0; x<5; x++){
                for (int y=0; y<5; y++){
                    board.put(new Sea(), new Position(x, y));
                    }
            }

            //mettre une tuile Earth au centre de 4 tuiles Earth
            Position pos = new Position(2, 2);
            Earth tuile = new Field();
            board.put(tuile, pos);

            Position pos1 = new Position(2, 1);
            Earth tuile1 = new Field();
            board.put(tuile1, pos1);

            Position pos2 = new Position(1, 2);
            Earth tuile2 = new Field();
            board.put(tuile2, pos2);
            
            Position pos3 = new Position(3, 2);
            Earth tuile3 = new Field();
            board.put(tuile3, pos3);
    

            Position pos4 = new Position(2, 3);
            Earth earth = new Field();
            board.put(earth, pos4);

            
            
            Farm farm = new Farm(tuile, null);
            tuile.setBuilding(farm);

            
            Farm farm1 = new Farm(tuile1, null);
            tuile1.setBuilding(farm1);
            
            
            Port port = new Port(tuile3, player);
            tuile2.setBuilding(port);
            
            board.islands= board.findIslands();

            BuildArmy buildArmy = new BuildArmy(board, player);


            assertTrue(buildArmy.canBuildArmy(earth,player));


        }

}