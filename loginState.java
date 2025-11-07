
/**
 * @author Bryan Erickson
 */
import java.io.*;
import java.util.Scanner;

public class loginState extends wareHouseState {

    private static final int clientLogin = 0;
    private static final int clerkLogin = 1;
    private static final int managerLogin = 2;
    private static final int exit = 3;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static loginState instance;
    private wareHouseContext context;

    private loginState() {
        super();
    }

    //Allows the user to enter the client state
    private void client() {
        System.out.println("You are a client");

        Scanner clientScanner = wareHouseContext.instance().scanner;
        System.out.println("Enter the User ID to login as:");
        
        String userId = clientScanner.next();

        System.out.println("Enter the User Password as well:");
        
        String login = clientScanner.next();
        //Checks to see if the client exists within the client list
        if(Warehouse.instance().searchClient(userId) != null){
            System.out.println("Client Found");
            wareHouseContext.instance().setLogin(login);
            wareHouseContext.instance().setUser(userId);
            
            //Moves the fsm to the client state
            wareHouseContext.instance().changeState(1);
        }else{
            System.out.println("Client not found");
            //If client is not found, the fsm will error out, this causes it (can be changed if needed)
             wareHouseContext.instance().changeState(0);
        }
    }

    //Function for going to the clerk state
    private void clerk() {
        System.out.println("You are a clerk");
        //!!!Add transtions once code is done!!!
    }

    //Fucntion for goin to manager state
    private void manager() {
        System.out.println("You are a manager");
        //!!!Add transtions once code is done!!!
    }

    public static loginState instance() {
        if (instance == null) {
            instance = new loginState();
        }
        return instance;
    }

    public void run(){
        
        boolean end = false;

        System.out.println("Login Menu:");
        System.out.println("0: Login as client");
        System.out.println("1: Login as clerk");
        System.out.println("2: Login as manager");

        int menuInput = wareHouseContext.instance().scanner.nextInt();
        wareHouseContext.instance().scanner.nextLine();
        

        
        switch(menuInput){
            case 0:
            client();
                break;
            case 1:
            clerk();
                break;
            case 2:
            manager();
                break;
            }
            
            
        
    }

}
