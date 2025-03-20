package game.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @BeforeEach
    void setUp(){
        player = new PlayerAres("Ares");
        board= new Board(5, 5); 
        action= new BuildArmy(board, player); 
    }

    @Test
    void BuildTest() throws NoMoreRessourcesException, CantBuildException, IOException{
        assertTrue(player.getArmies().isEmpty());
        action.act(player);
        assertTrue(! player.getArmies().isEmpty());
    }

}