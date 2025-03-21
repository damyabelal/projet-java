package game.listchooser;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import game.Board;
import game.util.Position;

public class RandomListChooserTest{

        private RandomListChooser<Integer> lc;
        private Board board; 

        @BeforeEach
        void setUp(){
            lc= new RandomListChooser<>(); 
            board= new Board(5, 5); 
        }

        @Test
        void returnIntegerTest(){
            ArrayList<Integer> list= new ArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            Integer pick= lc.choose(null, list);
            assertTrue( pick<4 && pick>0);
        }

        @Test
        void returnPosition() throws IOException{
            Position pos= lc.chooseCoordinate(null, board);
            assertTrue(board.isBuildable(pos)); 
        }
}