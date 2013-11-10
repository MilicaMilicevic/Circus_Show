package src.client.animal;

import src.client.properties.Graham;
import src.client.food.Name;

public class Rabbit extends Animal implements Graham {
  private static int counter;
  
  public Rabbit(){
    super("Rabbit-"+(++counter),Name.CARROT);
  } 
 }