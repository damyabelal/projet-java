package game.action;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import game.Board;
import game.NoMoreRessourcesException;
import game.PlayerDemeter;
import game.tuile.Ressource;

public class PlayThiefTest{
  // creating 2 players so that player1 could steal the ressources of the second player
  private PlayerDemeter player1;
  private PlayerDemeter player2;
  private Board board;
  private PlayThief actionvoler;

  

  void setUp(){
    player1= new PlayerDemeter("lucie");
    player2=new PlayerDemeter("luca");
    board=new Board(5,5);


  }  

  void StealingARessourceThatTheyDontHaveShouldThrowException() throws NoMoreRessourcesException{
    assertTrue(player1.getRessourceAmount(Ressource.WOOD)==0);
    assertTrue(player2.getRessourceAmount(Ressource.WOOD)==0);
    assertThrows(NoMoreRessourcesException.class,()->actionvoler.act(player2));
  }


  void StealingARessourceThatTheyHaveShouldWord() throws NoMoreRessourcesException{
    player1.addRessource(Ressource.WOOD, 2);
    assertTrue(player1.getRessourceAmount(Ressource.WOOD)==2);
    actionvoler.act(player2);
    assertTrue(player1.getRessourceAmount(Ressource.WOOD)==0);
    assertTrue(player2.getRessourceAmount(Ressource.WOOD)==2);
    

    

  }
}