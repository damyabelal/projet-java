package game.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;


import game.Board;
import game.CantBuildException;
import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.Forest;
import game.tuile.Sea;
import game.tuile.building.Farm;
import game.tuile.building.Port;
import game.util.Position;

public class BuildArmyTest{
    
    private Board board; 
    private PlayerAres player; 
    private Action<PlayerAres> action; 
    private Action<PlayerAres> actionBuild; 
    // creation de tuile qui vont servir pour creer une ile de le board parceque comme le board est creer de facon  aleatoire on 
    //veut etre sur que il y a une ile qui respecte les conditions pour poser une armee sur le plateau
    private Forest foret1;
    private Forest foret2;
    private Forest foret3;
 
    private Sea sea;
    private Port port;
    private Position pos1;
    private Position pos2;
    private Position pos3;
    private Position pos4;


    @BeforeEach
    void setUp(){
        
        board= new Board(5, 5); //creation d'un plateau de maniere aleatoire
        player = new PlayerAres("Ares",board);
        action= new BuildArmy(board, player); 
        pos1=new Position(0,0);
        pos2=new Position(1,0);
        pos3=new Position(0,1);
        pos4= new Position(1,1);
        foret1=new Forest();
        foret2=new Forest();
        foret3=new Forest();
       
        sea= new Sea();
        port =new Port(null, player);
        // mise en place d'une ile dans le coin superieure gauche du board
        board.put(foret1,pos1);
        board.put(foret2,pos2);
        board.put(foret3,pos3);
        board.put(sea,pos4);

        
        actionBuild= new BuildPort(player, board);


    }

    @Test
    void BuildTest() throws NoMoreRessourcesException, CantBuildException, IOException{
        board.put(farm1,pos1)
        assertTrue(player.getArmies().isEmpty());
        actionBuild.act(player);
        actionBuild.act(player);
        assertTrue( player.getPorts().size() == 2);
        // en fait il faut ajouter dur la meme ile et c'est pas forcément le cas car ici je les
        // pose aléatoirement, je m'en occupe plus tard
        action.act(player);
        assertTrue(! player.getArmies().isEmpty());
    }

}