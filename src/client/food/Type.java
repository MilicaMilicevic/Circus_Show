package src.client.food;

import java.util.Random;

public enum Type {
       CARNIVORE,GRAHAM;
  
       public static Type getRandom() {
         return values()[new Random().nextInt(values().length)]; //napomena values()- vraca niz vrijednosti
         }
}