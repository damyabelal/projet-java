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
import game.tuile.Forest;
import game.tuile.Sea;
import game.tuile.building.Camp;
import game.tuile.building.Port;
import game.util.Position;

public class BuildArmyTest{
    
       
        private Board board; 
        private PlayerAres player; 
        private Action<PlayerAres> action; 
        private Action<PlayerAres> actionBuild; 
        // creating an island that will respect so we can be sure that there are at least 
        //an island that meets the conditions for building an army since the board is created randomly
        private Forest foret1;
        private Forest foret2;
        private Forest foret3;
     
        private Sea sea;
        private Port port;
        private Position pos1;
        private Position pos2;
        private Position pos3;
        private Position pos4;
    
        private Camp camp1;
        private Camp camp2;
    
    
        @BeforeEach
        void setUp() throws CantBuildException{
            
            board= new Board(5, 5); //creates a random board for the game
            player = new PlayerAres("Ares");
            action= new BuildArmy(board, player); 
            pos1=new Position(0,0);
            pos2=new Position(1,0);
            pos3=new Position(0,1);
            pos4= new Position(1,1);
            foret1=new Forest();
            foret2=new Forest();
            foret3=new Forest();
            sea= new Sea();
            // placing a tile at the left upper corner of the board
            board.put(foret1,pos1);
            board.put(foret2,pos2);
            board.put(foret3,pos3);
            board.put(sea,pos4);
            
            //  creation of a port and two camps to be placed on the island created earlier.
            camp1=new Camp(foret1,6,player);
            camp2=new Camp(foret2,6,player);
            // the tile foret2 is placed beside a sea tile so we can place a port on it      
            port =new Port(foret2, player);
            
            
            
            
        }
        
        @Test
        void BuildTest() throws NoMoreRessourcesException, CantBuildException, IOException{
            
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

}