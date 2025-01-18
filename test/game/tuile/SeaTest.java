package test.game.tuile;
import tuile.Sea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeaTest {

    private Sea sea;

    @BeforeEach
    void setUp() {
        sea = new Sea();
    }
    
    // verifier qu'on a bien construit une tuile de type mer (sea)
    @Test
    void testConstructor() {
        assertNotNull(sea);
    }
}
