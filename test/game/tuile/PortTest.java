package game.tuile;
import game.Board;
import game.util.*;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class PortTest{

  private Port port;
  private Board board;
  private Earth forest;
  private Ressource ressource;
  private Position pos;
  private Position pos2;



  @BeforeEach
  void setUp(){
    board= new Board(4,4);
    ressource= Ressource.WOOD;
    forest= new Forest();
    port = new Port(forest);
    pos= new Position(0,0);
    pos2=new Position(2,2);
    board.put(forest,pos);
    board.put(forest,pos2);


  


  }


  
  @Test
  void numbeOfSeaTilesAroundAPort(){
    assertEquals(port.nbSeaTiles(pos,board),2);// la position pos doit etre entouré de 2 tuile mer
    assertEquals(port.nbSeaTiles(pos2,board),8);


}

}