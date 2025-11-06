/**
 * @author Bryan Erickson
 */

import java.util.Scanner;

public class wareHouseContext {

    //private static warehouse warehouse;
    private static wareHouseContext context;

    private int currentUser;
    private String userId;
    public static final int IsClient = 0;
    public static final int IsClerk = 1;
    public static final int IsManager = 2;
    private static int currentState;
    private static wareHouseState[] states;
    private int[][] nextState;

    public Scanner scanner = new Scanner(System.in);

    public void setLogin(int code){
        currentUser = code;
    }

    public void setUser(String uID){
        userId = uID;
    }

    public int getLogin(){
        return currentUser;
    }

    public String getUser(){
        return userId;
    }

    public int getState(){
        return currentState;
    }

    private wareHouseContext() {
       

        states = new wareHouseState[2];
        states[0] = loginState.instance();
        states[1] = clientState.instance();
        nextState = new int[2][2];
        nextState[0][0] = -1;
        nextState[0][1] = 1;
        nextState[1][0] = 0;
        nextState[1][1] = 0;
        currentState = 0;

    }

    public void changeState(int transition) {
        currentState = nextState[currentState][transition];
        if (currentState == -1) {
            System.out.println("An error has occured");
        }

        states[currentState].run();

    }

    public void process() {
        states[currentState].run();
    }

    public static wareHouseContext instance() {
        if (context == null) {
            
            context = new wareHouseContext();
        }
        return context;
    }

    public static void main(String[] args) {
        wareHouseContext.instance().process();
        
    }
}
