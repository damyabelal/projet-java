package game.tuile;
import game.Board;
import game.util.*;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class PortTest{

  private Port port;
  private Board board;
  private Earth forest;
  private Position pos;
  private Position posbis;
  private Earth forestbis;



  @BeforeEach
  void setUp(){
    board= new Board(4,4);
    port = new Port(forest);
    pos= new Position(0,0);
    posbis=new Position(2,2);
    forest= new Forest();
    forestbis=new Forest();
    board.put(forestbis,posbis);
    board.put(forest,pos);


  


  }

  @Test
  void numberOfSeaTilesAroundAPosition(){
    assertEquals(port.nbSeaTiles(pos,board),2);// la position pos doit etre entouré de 2 tuile mer
    assertEquals(port.nbSeaTiles(posbis,board),4);


}

  @Test
  void canPlacePortShoudReturnTrue(){
    assertTrue(port.canPlacePort(pos, board));
  }
  @Test
  void canPlacePortShoudReturnFalse(){
    
    // placing earth tiles around the position (0,0) , 
    //so now the method canplaceport for this positin should return false
    Forest forest2= new Forest();
    Forest forest3= new Forest();
    Forest forest4= new Forest();
    Position pos2 =new Position(0,1);
    Position pos3=new Position(1,0);
    Position pos4 =new Position(1,1);
    board.put(forest2,pos2);
    board.put(forest3,pos3);
    board.put(forest4,pos4);

    assertFalse(port.canPlacePort(pos, board));




  }

}