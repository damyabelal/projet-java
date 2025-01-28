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
    void testRandomPosition() {
        // Verify that the random position is not null and is within the board's border limits
        Position randomPos = board.randomPosition();
        assertNotNull(randomPos);
        assertTrue(randomPos.getX() >= 0 && randomPos.getX() < board.getWidth());
        assertTrue(randomPos.getY() >= 0 && randomPos.getY() < board.getHeight());
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
    void testRandomTuile() {
        // Verify that the generated tile is not Sea (it should be Forest, Mountain, Pasture, or Field)
        Tuile randomTile = board.randomTuile();
        assertFalse(randomTile instanceof Sea);
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
        board.placeInitialeTiles();
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

    @Test
    void testPlaceNeighboorEarthTiles() {
        board.createBoard(); 
        int reste = board.placeNeighboorEarthTiles();
        // Verify that there are still tiles left to place
        assertTrue(reste >= 0); 
    }

    @Test
    void testCreateBoard() {
        board.createBoard();
        // Also test the getGrid() method (to avoid writing multiple tests)
        Tuile[][] grid = board.getGrid();
        int nbEarthTile = 0;
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (!(grid[x][y] instanceof Sea)) {
                    nbEarthTile++;
                }
            }
        }
        // We cannot predict the results for createBoard since it randomly generates the board,
        // but we can ensure that there are some non-Sea tiles
        assertTrue(nbEarthTile > 0);
    }
}
