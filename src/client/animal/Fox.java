package src.client.animal;

import src.client.properties.Carnivore;
import src.client.properties.Singable;
import src.client.food.Name;

public class Fox extends Animal implements Carnivore, Singable{
  private static int counter;
  
  public Fox(){//dovoljan je podrazumijevani konst.
    super("Fox-"+(++counter),Name.CHICKEN);
  }
  
  @Override
  public String sing(){
    return "iii ";
  }

}