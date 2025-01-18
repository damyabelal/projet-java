package test.game.tuile;
import tuile.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ForestTest {

    private Forest forest;

    @BeforeEach
    void setUp() {
        forest = new Forest();
    }
    
    // verifier quon a bien creer un objet de type foret et qu'il n'est pas null 
    @Test
    void testConstructor() {
        assertNotNull(forest);
    }
    
    // verifier que la ressource bois correspond bien a la tuile foret
    @Test
    void testGetRessource() {
        assertEquals(Ressource.WOOD, forest.getRessource());
    }
}
