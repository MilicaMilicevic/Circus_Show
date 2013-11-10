package src.client.animal;

import src.client.properties.Graham;
import src.client.properties.Singable;
import src.client.food.Food;
import src.client.food.Name;

public class Elephant extends Animal implements Graham, Singable {
  private static int counter;
  
  public Elephant(){
    super("Elephant-"+(++counter),Name.GRASS);
  }
  
@Override
  public String sing(){
    return "uuu ";
}

}