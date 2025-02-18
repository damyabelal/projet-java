package game.tuile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;


class CampTest{

  private Camp camp;
  private Earth tuile;
  private Ressource ressource;

 @BeforeEach
 void setUp(){
  ressource= Ressource.SHEEP;
  tuile= new Earth(ressource,"F");
  camp = new Camp(tuile ,8);

 }

@Test 
void GetAdditionnalWarriorTest(){
  
  assertEquals(camp.getAdditionalWarriors(),3);




}

@Test 
void getNameTest(){

  assertTrue(camp.getName()=="Camp");

  
}




}