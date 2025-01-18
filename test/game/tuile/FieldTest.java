package test.game.tuile;
import tuile.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field();
    }
    
     // verifier quon a bien creer un objet de type champ et qu'il n'est pas null
    @Test
    void testConstructor() {
        assertNotNull(field);
    }
    // verifier que la ressource blé correspond bien a la tuile champ
    @Test
    void testGetRessource() {
        assertEquals(Ressource.WEALTH, field.getRessource());
    }
}
