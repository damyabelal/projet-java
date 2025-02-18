package game.tuile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;


class CampTest{

@Test 
void GetAdditionnalWarriorTest(){
  Ressource ressource= Ressource.SHEEP;
  Earth tuile= new Earth(ressource,"F");
  Camp camp = new Camp(tuile ,8);
  assertEquals(camp.getAdditionalWarriors(),3);




}

@Test 
void getNameTest(){
  Ressource ressource= Ressource.SHEEP;
  Earth tuile= new Earth(ressource,"F");
  Camp camp = new Camp(tuile ,8);
  assertEquals(camp.getName(),"Camp");

  
}




}