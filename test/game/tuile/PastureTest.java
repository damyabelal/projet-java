package test.game.tuile;
import tuile.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PastureTest {

    private Pasture pasture;

    @BeforeEach
    void setUp() {
        pasture = new Pasture();
    }
    
    // verifier quon a bien creer un objet de type paturage et qu'il n'est pas null 
    @Test
    void testConstructor() {
        assertNotNull(pasture);
    }

    // verifier que la ressource mouton correspond bien a la tuile paturage

    @Test
    void testGetRessource() {
        assertEquals(Ressource.SHEEP, pasture.getRessource());
    }
}
