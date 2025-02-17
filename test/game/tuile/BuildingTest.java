package game.tuile;
import game.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import game.tuile.*;

class BuildingTest{

    private  Building building;
    private Farm farm;

    @BeforeEach
    void setUp() {
        Farm farm=new  Farm();

    }





    @Test
    void testGetTuile(){
    
    Building building = new Building(farm);  
    assertEquals(farm,Building.getTuile());



    
    


    }


    
}