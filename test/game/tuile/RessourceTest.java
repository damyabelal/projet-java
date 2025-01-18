package test.game.tuile;

import tuile.Ressource;
import static org.junit.jupiter.api.Assertions.*;

public class RessourceTest {

    // on crée un tableau qui contient toute les valeurs de l'enum : dabord on verfie si la liste est non vide avec assertNotNull
    // ensuite on verifie si les valeurs sont bien dans l'ordre avec assertTrue
    @Test
    public void testEnumValues() {
        Ressource[] ressources = Ressource.values();
        assertNotNull(ressources);
        assertEquals(4, ressources.length);
        assertTrue(ressources[0] == Ressource.WOOD);
        assertTrue(ressources[1] == Ressource.WEALTH);
        assertTrue(ressources[2] == Ressource.ORE);
        assertTrue(ressources[3] == Ressource.SHEEP);
    }


}
