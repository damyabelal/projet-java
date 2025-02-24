package game.tuile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuildingTest{

    private  Building building;
    private Earth tile;

    @BeforeEach
    void setUp() {
        building = new Building();  
        tile=new  Earth();

    }





    @Test
    void testGetTile(){
    tile.addBuilding();
    assertEquals(tile.getPosition(),Building.getPosition());


     


    }


    
}