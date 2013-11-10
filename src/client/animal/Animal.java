package src.client.animal;

import src.client.food.*;
import src.client.Client;
import src.client.properties.*;

import java.util.Random;

public abstract class Animal extends Thread {
  private String name;
  private Name favoriteFood;

  private int[] position;
  private int step; //broj polja koje zivotinja predje u jednom koraku

  public Animal(String arg1, Name arg2){
    super(arg1); 
    favoriteFood=arg2;
  }
  
  @Override
  public void run(){
    step=new Random().nextInt(3)+1;  //proizvoljan broj polja
    while(position[1]+step<((Client.getUClient()).getStage()).length){//uslov daljeg kretanja
      if(((Client.getUClient()).getStage())[position[0]][position[1]+step] instanceof Food){//naisao na hranu
        Food food=(Food)(Client.getUClient()).getStage()[position[0]][position[1]+step];
        System.out.println(getName()+" "+positionToString()+" : I found "+food+" on position ["+position[0]+","+(position[1]+step)+"]");
          if(((this instanceof Graham)&&(food.getType()).equals(Type.GRAHAM)&&(food.getName()).equals(favoriteFood))
          ||((this instanceof Carnivore)&&(food.getType()).equals(Type.CARNIVORE)&&(food.getName()).equals(favoriteFood)))
            {
            System.out.println("And, it's my favorite - "+food); 
            try{
              System.out.println("I'm eating..."); 
              sleep(food.getTimeSec());
            }
              catch(InterruptedException e){
                e.printStackTrace();
              }
        }
        else
          System.out.println("But it's not my favorite!");
          move(step);
        }
       else if(((((Client.getUClient()).getStage())[position[0]][position[1]+step])) instanceof String){ //naisao na SING
        System.out.print(getName()+" "+positionToString());
        if(this instanceof Singable){
          System.out.println(" Can sing.");
          for(int i=0;i<new Random().nextInt(5)+1;i++)
            System.out.println(((Singable)this).sing());
        }
        else 
          System.out.println(" Can't sing.");
          move(step);
      }
      else 
        move(step);
  }
  }
  
//HELPER
  private void move(int step){
    synchronized(Client.getUClient()){
            position[1]+=step;
            ((Client.getUClient()).getStage())[position[0]][position[1]]=this;//prepisuje Animal preko SING/Food
            ((Client.getUClient()).getStage())[position[0]][position[1]-step]=null;//oslobodi staro mjesto
          }
  }
  
  public void setPosition(int arg1,int arg2){
    if(position==null)
      position=new int[2];
    position[0]=arg1;
    position[1]=arg2;
  }

  public boolean isSet(){
    if(position!=null) 
      return true;
    return false;
  }
  //HELPER
  private Object positionToString(){
    if(position!=null)
      return "["+position[0]+","+position[1]+"]";
    else 
      return "It's not on the stage!";
  }
  
  @Override
  public String toString(){
    return getName()+" [Favorite food: "+favoriteFood+"]";
  } 
}
  