package src.client.food;

import java.io.Serializable;
import java.util.Random;

public class Food implements Serializable {
  private Type type;
  private Name name;
  private int timeSec;

  public Food(){
      type=Type.getRandom();
      name=Name.getRandom();
      timeSec=new Random().nextInt(5*1000); //vrijeme je u secundama
  }
  
  public Food(Type arg1,Name arg2,int arg3){
    type=arg1;
    name=arg2;
    timeSec=arg3;
  }
    
  

  @Override
  public String toString(){
    return "[Type: "+type+"|Name: "+name+"|Time: "+timeSec+"]";
  }
  
  public Type getType(){
    return type;
  }
  
  public Name getName(){
    return name;
  }

  public int getTimeSec(){
    return timeSec;
  }
}