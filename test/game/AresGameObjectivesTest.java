package game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import game.AresGameObjectives.ObjectiveType;

public class AresGameObjectivesTest{


 void setUp() {
  AresGameObjectives conquerir =new AresGameObjectives(ObjectiveType.CONQUER_TILES, 0);
  AresGameObjectives envahir =new AresGameObjectives(ObjectiveType.EVADE_ISLANDS, 0);
  AresGameObjectives detenir =new AresGameObjectives(ObjectiveType.DETAIN_WARRIORS, 0);
  

 }

 @Test
 public void  ObjectiveGetTypeTest(){
  AresGameObjectives objective = new AresGameObjectives(
            ObjectiveType.CONQUER_TILES, 10
        );
        assertEquals(ObjectiveType.CONQUER_TILES, objective.getType());


 AresGameObjectives objective2 = new AresGameObjectives(
          ObjectiveType.DETAIN_WARRIORS, 10
      );
      assertEquals(ObjectiveType.DETAIN_WARRIORS, objective2.getType());



      AresGameObjectives objective3 = new AresGameObjectives(
        ObjectiveType.INVADE_ISLANDS, 10
    );
    assertEquals(ObjectiveType.DETAIN_WARRIORS, objective3.getType());




 }



}