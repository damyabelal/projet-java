package game.tuile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class EarthTest{
    private Building building ;
    @BeforeEach
    void setUp() {
        building = new Building(35);   
    }

    private Tile tile ;
    @BeforeEach
    void setUp() {
        tile = new Tile();    
    }




    @Test
    void TestAddingABuildingToATile(){
      
      tile.addBuilding(building);
      assertEquals(building , tile.getBuilding());

    }



    @Test // it should throw an exception
    void TestGetBuildingOnATileWithoutABuilding(){
      assertThrows( tile.getBuilding()->Exception);
    }


    
}
