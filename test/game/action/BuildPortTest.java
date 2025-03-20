package game.action;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Board;
import game.CantBuildException;
import game.NoMoreRessourcesException;
import game.PlayerAres;

public class  BuildPortTest{

    private Board board; 
    private PlayerAres player; 
    private Action<PlayerAres> action; 

    @BeforeEach
    void setUp(){
        player = new PlayerAres("Marco");
        board= new Board(5, 5); 
        action= new BuildPort<PlayerAres>(player, board); 
    }

    @Test
    void BuildTest() throws NoMoreRessourcesException, CantBuildException, IOException{
        assertTrue(player.getPorts().isEmpty());
        action.act(player);
        assertTrue(! player.getPorts().isEmpty());
    }



}