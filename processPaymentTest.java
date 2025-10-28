/**
 * @author Bryan Erickson
 */

import java.util.Scanner;



public class processPaymentTest{


    public static void main(String[] args) {
        ClientList c_list = new ClientList();
        Client tester = new Client("101", "Jim",0);
        Client tester2 = new Client("101", "Randy",50);
        c_list.addClient(tester);
        c_list.addClient(tester2);


        Scanner payment = new Scanner(System.in);

        
        System.out.println("Enter the transaction total:");
        float transactionTotal = payment.nextFloat(); //Change this to the total of the transaction

        System.out.println("Enter the amount paid by the client:");
        float amountPaid = payment.nextFloat();

        float remainingBalance = amountPaid - transactionTotal;

        if(remainingBalance < 0.0){
            System.err.println("A balance of " + Math.abs(remainingBalance) + " still exists, this will be debited to the account");
            tester.addToBalance(remainingBalance);
            System.out.println("Current account balance:" + tester.getBalance());
        }else{
            System.err.println("A credit of " + remainingBalance + " will be added to your account");
            tester.addToBalance(remainingBalance);
            System.out.println("Current account credit:" + tester.getBalance());
        }

        

        
        







        payment.close();


    }
}