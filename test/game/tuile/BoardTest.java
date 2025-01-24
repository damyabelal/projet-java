package game.tuile;

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
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                // on verifie a la construction du plateau que chaque case est occupé par la tuile Sea
                assertTrue(board.getGrid()[x][y] instanceof Sea);
            }
        }
    }

    @Test
    void testIsEmpty() {
        // verifier qu'une case avec une tuile Sea est considere comme vide
        Position pos = new Position(0, 0);
        assertTrue(board.isEmpty(pos));
        
        // on ajoute une tuile differente de Sea et on teste si la meme position est toujours vide
        board.put(new Forest(), pos);
        assertFalse(board.isEmpty(pos));
    }

    @Test
    void testRandomPosition() {
        // on verifie que la position aleatoire n'est pas null et ne sort pas des limites de bordure du plateau
        Position randomPos = board.randomPosition();
        assertNotNull(randomPos);
        assertTrue(randomPos.getX() >= 0 && randomPos.getX() < board.getWidth());
        assertTrue(randomPos.getY() >= 0 && randomPos.getY() < board.getHeight());
    }

    @Test
    void testHaveNeighbor() {
        // on pose une tuile au centre ensuite on verifie si elle a un voisin a cote (au depart non vu que ya que les tuiles Sea)
        Position pos = new Position(2, 2);
        board.put(new Forest(), pos);
        assertFalse(board.haveNeighbor(pos));

        // on lui rajoute une tuile voisine et doit renvoyer vrai dans ce cas 
        Position neighbor = new Position(2, 3);
        board.put(new Forest(), neighbor);
        assertTrue(board.haveNeighbor(pos));
    }

    @Test
    void testHaveEmptyNeighborList() {
        Position pos = new Position(2, 2);
        board.put(new Forest(), pos);
        ArrayList<Position> emptyNeighbor = board.haveEmptyNeighboorList(pos);
        // on verifie que la methode ajoute bien un voisin libre a sa liste
        assertEquals(4, emptyNeighbor.size());
    }
}
