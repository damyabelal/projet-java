package test.game.util;
import util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    public void testGetDx() {
        // pour veifier les deplacements en x pour chaque direction
        assertEquals(1, Direction.N.getDx());
        assertEquals(-1, Direction.S.getDx());
        assertEquals(0, Direction.O.getDx());
        assertEquals(0, Direction.E.getDx());
    }

    @Test
    public void testGetDy() {
        // pour vfier les déplacements en y pour chaque direction
        assertEquals(0, Direction.N.getDy());
        assertEquals(0, Direction.S.getDy());
        assertEquals(-1, Direction.O.getDy());
        assertEquals(1, Direction.E.getDy());
    }
}
