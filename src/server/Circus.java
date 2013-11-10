package src.server;

import java.io.*;
import java.net.*;

import java.util.Random;

import src.client.food.Food;

public class Circus { //nema potrebe za multi-client!
  private final static int SERVER_PORT=444;
  private ServerSocket serverSocket;

  private Object[][] stage;
  
  private static Circus uniqueCircus=new Circus();//singletone
  
  private Circus(){}
  
  public static void main(String[] args){
    try{
      uniqueCircus.serverSocket=new ServerSocket(SERVER_PORT);
      Socket socket=uniqueCircus.serverSocket.accept();  //serverski kraj
      
      //prima referencu
      ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
      uniqueCircus.stage=(Object[][])ois.readObject();
      
      //rpostavljanje Hrane(TACNO)
      Random generator=new Random();
      for(int k=0;k<15;k++)
      {
        boolean isSet=false;
        while(!isSet){
          int i=generator.nextInt(uniqueCircus.stage.length);
          int j=generator.nextInt(uniqueCircus.stage.length-1)+1; //da ne postavimo u prvu kolonu
          if(!(uniqueCircus.stage[i][j] instanceof Food)) { //da se ne bi hrana postavila tamo gdje vec postoji
            uniqueCircus.stage[i][j]=new Food();
            isSet=true;
        }
        }
      }
      //postavljanje SING (TACNO)
      for(int k=0;k<15;k++)
      {
        boolean isSet=false;
        while(!isSet){
          int i=generator.nextInt(uniqueCircus.stage.length);
          int j=generator.nextInt(uniqueCircus.stage.length-1)+1;
          if(!(uniqueCircus.stage[i][j] instanceof Food)&&!(uniqueCircus.stage[i][j] instanceof String)){
            uniqueCircus.stage[i][j]=new String("SING");
            isSet=true;
          }
        }
      }
     
     //varca je nazad
     ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
     oos.writeObject(uniqueCircus.stage);
     
    //house-keeping
    oos.close();
    ois.close();
    socket.close();
    uniqueCircus.serverSocket.close(); 
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}