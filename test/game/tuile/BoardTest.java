package game.tuile;
import game.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(5, 5);
    }

    @Test
    void testBoardInit() {
        assertEquals(5, board.getWidth()); 
        assertEquals(5, board.getHeight());
    }

    @Test
    void testHaveNeighbor() {
        // Place a tile at the center and check if it has a neighbor next to it (initially, it won't since only Sea tiles are present)
        Position pos = new Position(2, 2);
        board.put(new Forest(), pos);
        assertFalse(board.haveNeighbor(pos));

        // Add a neighboring tile and it should now return true
        Position neighbor = new Position(2, 3);
        board.put(new Forest(), neighbor);
        assertTrue(board.haveNeighbor(pos));
    }

    @Test
    void testHaveEmptyNeighborList() {
        Position pos = new Position(2, 2);
        board.put(new Forest(), pos);
        ArrayList<Position> emptyNeighbor = board.haveEmptyNeighboorList(pos);
        // Verify that the method correctly adds a free neighbor to the list
        assertEquals(4, emptyNeighbor.size());
    }

    @Test
    void testTileNumber() {
        // Should calculate 1/3 of the total number of board tiles
        int test = (int) (board.getWidth() * board.getHeight() * 1.0 / 3);
        assertEquals(test, board.tileNumber());
    }

    @Test
    void testPut() {
        Position pos = new Position(2, 2);
        Tuile newTile = new Forest();
        board.put(newTile, pos);
        // Verify that the Forest tile is placed at position (2,2)
        assertEquals(newTile, board.getGrid()[pos.getX()][pos.getY()]);
    }

    @Test
    // This test ensures that the method places 1/6 of the tiles (half of 1/3)
    void testPlaceInitialeTiles() {
        int nb = 0;
        Tuile[][] grid = board.getGrid();
        // Traverse the board and increment nb each time a non-Sea tile is found
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (!(grid[x][y] instanceof Sea)) {
                    nb++;
                }
            }
        }
        // At the end, nb should be equal to 1/6 of the total number
        assertEquals(board.tileNumber() / 2, nb);
    }

}
