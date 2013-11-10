package src.client.food;

import java.util.Random;


public enum Name { //koristi i klase nasljednice Animal
  LAMB, CARROT, GRASS, CHICKEN;
  
  public static Name getRandom(){
    return values()[new Random().nextInt(values().length)];
  }
}