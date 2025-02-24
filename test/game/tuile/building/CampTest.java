package game.tuile.building;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import game.tuile.Field;

import static org.junit.jupiter.api.Assertions.*;


class CampTest{

  private Camp camp;
  private Field tuile;
  

 @BeforeEach
 void setUp(){
 
  
  tuile = new Field();
  camp = new Camp(tuile ,8);

 }
/*
 * test the getAdditionalWarriors method : 
 * Tests whether the number of additional warriors beyond the Army limit is calculated correctly.
 */
@Test 
void getAdditionalWarriorTest(){
  assertEquals(3, camp.getAdditionalWarriors());
}




@Test
void getNameTest(){
  assertEquals("Camp", camp.getName());



}





}