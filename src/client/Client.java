package src.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.InetAddress;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import src.client.animal.*;
import src.client.food.Food;


public class Client {
  private static final int SERVER_PORT=444;
  private List<Animal> animals;
  private Object[][] stage;
  
  static Client uniqueClient=new Client();//singletone!
  
  private Client(){
    animals=new ArrayList<Animal>();
    stage=new Object[30][30];
  }
  
  public static void main(String[] args){
   try{
     Socket clientSocket=new Socket(InetAddress.getLocalHost(),SERVER_PORT);
     //salje objekat
     ObjectOutputStream oos=new ObjectOutputStream(clientSocket.getOutputStream());
     oos.writeObject(uniqueClient.stage);
     //preuzima objekat
     ObjectInputStream ois=new ObjectInputStream(clientSocket.getInputStream());
     uniqueClient.stage=(Object[][])ois.readObject();
    
    //kreiranje zivotinja
    uniqueClient.createAnimals();
    uniqueClient.setAnimals();
    System.out.println("STAGE AT BEGINING:\n"+uniqueClient.stageToString());
    System.out.println("SIMULATION BEGINS.");
    for(int i=0;i<uniqueClient.animals.size();i++)
      (uniqueClient.animals.get(i)).start();
    for(int i=0;i<uniqueClient.animals.size();i++)//main thread ceka na izvrsavanje Animal thread
      (uniqueClient.animals.get(i)).join();
    System.out.println("SIMULATION ENDS.");
    System.out.println(uniqueClient.restToString());
    //house-keeping
    ois.close();
    oos.close();
    clientSocket.close();
   }
   catch(Exception e){
     e.printStackTrace();
   }
}
   
   public void createAnimals(){//T-Safe!
       for(int i=0;i<new Random().nextInt(5)+2;i++)
         animals.add(new Elephant()); 
       for(int i=0;i<new Random().nextInt(5)+2;i++)
         animals.add(new Fox());
       for(int i=0;i<new Random().nextInt(5)+2;i++)
         animals.add(new Rabbit());
       for(int i=0;i<new Random().nextInt(5)+2;i++)
         animals.add(new Wolf());
     }  
   
   public void setAnimals(){
     Iterator iteratorAnimal=animals.iterator();
     while(iteratorAnimal.hasNext()){//iterira kroz zivotinje
       Animal animal=(Animal)iteratorAnimal.next();
       while(!animal.isSet()){ //dok nije postavljena
         int i=new Random().nextInt(stage.length); //broj reda
         if(!(stage[i][0] instanceof Animal)){//ako tu vec ne postoji zivotinja
           stage[i][0]=animal;
           animal.setPosition(i,0);
         }
       }
     }
   }
   
   public String stageToString(){//tacno//
     String result="";
     for(int i=0;i<stage.length;i++)
       for(int j=0;j<stage[i].length;j++)
         if(stage[i][j]!=null)
            result+=stage[i][j]+" ["+i+","+j+"]\n";
     return result;
   }
       
   public String restToString(){
     int counterFood=0,counterSing=0;
     for(int i=0;i<stage.length;i++)
       for(int j=0;j<stage[i].length;j++)
     {
       if(stage[i][j] instanceof Food) 
         counterFood++;
       else if(stage[i][j] instanceof String)
         counterSing++;
     }
     return "Left SINGs - "+counterSing+", left Foods - "+counterFood;
   }

   public Object[][] getStage(){
     return stage;
   }
    
   public static Client getUClient(){
     return uniqueClient;
   }  
  }