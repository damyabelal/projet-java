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

public class BuildArmyTest{
    
    private Board board; 
    private PlayerAres player; 
    private Action<PlayerAres> action; 
    private Action<PlayerAres> actionBuild; 

    @BeforeEach
    void setUp(){
        player = new PlayerAres("Ares");
        board= new Board(5, 5); 
        action= new BuildArmy(board, player); 
        actionBuild= new BuildPort(player, board); 
    }

    @Test
    void BuildTest() throws NoMoreRessourcesException, CantBuildException, IOException{
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