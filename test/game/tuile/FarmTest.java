package game.tuile;
import game.Player;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;



public class FarmTest{
  
    private Farm farm;
    private Earth tuile;
    private Player player;
    private Ressource ressource;
  
    @BeforeEach
    void setUp(){
      player = new Player("loulou");
      ressource = Ressource.SHEEP;
      tuile = new Earth(ressource,"F");
      farm = new Farm(tuile);
  
    }
  
    @Test
    void canBeExploitationShouldReturnFalse(){
      assertFalse(farm.canBeExploitation(player));
  
    }
    
    @Test 
    void upGradeToExploitationShouldReturnNull(){
      assertEquals(farm.upGradeToExploitation(player),null);


  }



  @Test
  void canBeExploitationShouldReturnTrue(){
    Ressource sheep =Ressource.SHEEP;
    Ressource wealth=Ressource.WEALTH;
    Ressource wood=Ressource.WOOD;

    // giving the player enough ressources to be able to upgrade a farm to an exploitation
    player.addRessource(sheep, 1);
    player.addRessource(wood, 2);
    player.addRessource(wealth, 1);
    assertTrue(farm.canBeExploitation(player));

  }


  @Test
  void  upGradeToExploitationShouldReturnNewExploitation(){
    assertTrue(farm.upGradeToExploitation(player) instanceof Exploitation);
 
}



}