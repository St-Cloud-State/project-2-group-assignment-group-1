import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;
public class wareHouseContext {
private static int currentState;
private static wareHouseContext context;
private String current_Login;
private String userId;
private int currentUser;
 private BufferedReader reader = new BufferedReader(new 
                                      InputStreamReader(System.in));
private boolean Manager = false;
private boolean Client = false;
private boolean Clerk = false;
private static wareHouseState[] states;
private int[][] nextState;
 public Scanner scanner = new Scanner(System.in);
// Used to find out who the current client is
public String getLogin()
  { return current_Login;}
// Used for testing to verify current state
  public int getState()
  { return currentState;}

public void setLogin(String code){
        current_Login = code;
    }
 public void setUser(String uID){
        userId = uID;
    }
public String getUser(){
    return userId;
}

// Used to find out if the user is a clerk pretending to be a client
public boolean isClerk()
{
    if(Clerk){
        System.out.println("User was a clerk");
        return true;
    }
    else{
        System.out.println("User wasn't a clerk");
        return false;
    }
    
}

public boolean isClerk()
{
    if(Clerk){
        System.out.println("User was a clerk");
        return true;
    }
    else{
        System.out.println("User wasn't a clerk");
        return false;
    }
    
}
public boolean IsClient()
{
    if(Client){
        System.out.println("User was a client");
        return true;
    }
    else{
        System.out.println("User wasn't a client");
        return false;
    }
    
}
// reusable questions
public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
      }
    } while (true);
  }
  // reusable yes or no question
  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }

// Used to find out if the manager is pretending to be a clerk or client
public boolean getwasManager()
{
    if(Manager){
        System.out.println("User was a manager");
        return true;
    }
    else{
        System.out.println("User wasn't a manager");
        return false;
    }
    
}




private wareHouseContext(){
    states = new wareHouseState[4];
    // ask if group wants save functionality
states[0] = loginState.instance();
states[1] = clientState.instance(); 
states[2] = ClerkState.instance();
states[3] = ManagerState.instance();
    
    
    nextState = new int[4][4];
    nextState[0][0] = -1;nextState[0][1] = 1;nextState[0][2] = 2; nextState[0][3] = 3; 
    nextState[1][0] = 0;nextState[1][1] = -2;nextState[1][2] = 2; nextState[1][3] = -2; 
    nextState[2][0] = 0;nextState[2][1] = 1;nextState[2][2] = -2; nextState[2][3] = 3; 
    nextState[3][0] = 0; nextState[3][1] = -2; nextState[3][2] = 2; nextState[3][3] = -2; 
    currentState = 0;
}
// For use input state that you want to switch to if you 
public void changeState(int transition){
    
currentState = nextState[currentState][transition];
if( currentState == -2 )
    { System.out.println("You tried to switch to an invalid state"); terminate();}
if( currentState == -1 )
    { System.out.println("Thank you for using our system"); terminate();}
if(currentState == 0){
     { System.out.println("You are logging in");
    Clerk = false;
    Manager =false;
    Client =false;
    }
        
}
if( currentState == 1) {
     { System.out.println("You are a client");
    Client = true;
    }
}
if( currentState == 2 ){
    { System.out.println("You are a clerk");
    Clerk = true;
    Client =false;
    }
}
if( currentState == 3 ){
    { System.out.println("You are a manager");
    Manager = true;
    Client =false;
    }
}

states[currentState].run();
}
// stops the program
  private void terminate()
  {
    System.out.println("Terminating server");
    System.exit(0);
  }
// makes the first instance or returns that instance
public static wareHouseContext instance() {
    if (context == null) {
       System.out.println("calling constructor");
      wareHouseContext = new wareHouseContext();
    }
    return context;
  }
// runs a new state
public void process(){
    states[currentState].run();
  }
// main 
public static void main (String[] args){
    wareHouseContext.instance().process(); 
  }
}