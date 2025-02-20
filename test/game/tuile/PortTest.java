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


  @BeforeEach
  void setUp(){
    board= new Board(5,5);
    ressource= Ressource.WOOD;
    forest= new Forest(ressource);
    port = new Port(forest);
  }
  

void numbeOfSeaTilesAroundAPort(){



}

}