package game.listchooser;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RandomListChooserTest{

        private RandomListChooser<Integer> lc;

        @BeforeEach
        void setUp(){
            lc= new RandomListChooser<>(); 
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
}