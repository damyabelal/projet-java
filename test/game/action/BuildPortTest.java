package game.action;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Board;
import game.CantBuildException;
import game.NoMoreRessourcesException;
import game.PlayerAres;
import game.tuile.Ressource;

public class  BuildPortTest{

    private Board board; 
    private PlayerAres player; 
    private Action<PlayerAres> action; 

    @BeforeEach
    void setUp(){
        player = new PlayerAres("Marco");
        board= new Board(5, 5); 
        action= new BuildPort<PlayerAres>(player, board); 
       
       // ajout des ressources nessessaires pour construire un port
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.SHEEP, 2);
    }

    @Test
    void BuildTest() throws NoMoreRessourcesException, CantBuildException, IOException{
        assertTrue(player.getPorts().isEmpty());
        action.act(player);
        assertTrue(! player.getPorts().isEmpty());
    }



}