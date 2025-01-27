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

    @Test
    void testTileNumber() {
        // doit calculer 1/3 du nombre total des cases du plateau
        int test = (int) (board.getWidth() * board.getHeight() * 1.0 / 3);
        assertEquals(test, board.tileNumber());
    }

    @Test
    void testRandomTuile() {
        // on verifie que la tuile générée est differente de Sea (foret ou montagne ou paturage ou champ)
        Tuile randomTile = board.randomTuile();
        assertFalse(randomTile instanceof Sea);
    }

    @Test
    void testPut() {
        Position pos = new Position(2, 2);
        Tuile newTile = new Forest();
        board.put(newTile, pos);
        // on verifie que la tuile forest a bien été placé a la position (2,2)
        assertEquals(newTile, board.getGrid()[pos.getX()][pos.getY()]);
    }

    @Test
    // ce test permet de verifier que la methode place bien les 1/6 des des tuiles(la moitie de 1/3)
    void testPlaceInitialeTiles() {
        board.placeInitialeTiles();
        int nb = 0;
        Tuile[][] grid = board.getGrid();
        // on parcourt tout le plateau et on incremente (nb) a chaque fois qu'on croise une tuile terrestre
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
            if (!(grid[x][y] instanceof Sea)) {
                nb++;
            }
        }
    }
        // a la fin nb devrait etre egal a 1/6 du nombre total
        assertEquals(board.tileNumber() / 2, nb);
    }

    @Test
    void testPlaceNeighboorEarthTiles() {
        board.createBoard(); 
        int reste = board.placeNeighboorEarthTiles();
        // on verifie qu'il reste des tuiles a placer
        assertTrue(reste >= 0); 
    }

    @Test
    void testCreateBoard() {
        board.createBoard();
        // on teste par la meme occasion la methode getGrid() (pour eviter d'écrire plusieurs tests)
        Tuile[][] grid = board.getGrid();
        int nbEarthTile = 0;
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
            if (!(grid[x][y] instanceof Sea)) {
                nbEarthTile++;}
        }
    }
        // on peut pas predire de tests pour createBoard vu que cest un plateau qui est genéré aleatoirement
        // mais on peut s'assurer qu'il ya des tuiles autre que Sea 
        assertTrue(nbEarthTile > 0);
    }







}
