package game.tuile;
import game.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import game.tuile.*;

class BuildingTest{

    private  Building building;
    private Farm farm;
    private Earth earthtuile;
    private Ressource ressource;


    @BeforeEach
    void setUp(){
        Ressource ressource=Ressource.SHEEP;
        Earth earthtuile=new Earth(ressource ,"F");
        Farm farm=new  Farm(earthtuile);
        
    }





    @Test
    void testGetTuile(){
    
    Building building = new Building(farm);  
    assertEquals(farm,Building.getTuile());

}


    
}