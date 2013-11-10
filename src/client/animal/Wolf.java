package src.client.animal;

import src.client.properties.Carnivore;
import src.client.properties.Singable;
import src.client.food.Name;

public class Wolf extends Animal implements Carnivore, Singable {
  private static int counter;
  
  public Wolf(){
    super("Wolf-"+(++counter),Name.LAMB);
  }

  @Override 
  public String sing(){
    return "uaua ";
  }
}