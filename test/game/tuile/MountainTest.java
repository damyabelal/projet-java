package test.game.tuile;
import tuile.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MountainTest {

    private Mountain mountain;

    @BeforeEach
    void setUp() {
        mountain = new Mountain();
    }

    // verifier quon a bien creer un objet de type montagne et qu'il n'est pas null
    @Test
    void testConstructor() {
        assertNotNull(mountain);
    }
    
    // verifier que la ressource minerai correspond bien a la tuile montagne
    @Test
    void testGetRessource() {
        assertEquals(Ressource.ORE, mountain.getRessource());
    }
}
