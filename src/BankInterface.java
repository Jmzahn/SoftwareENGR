
import java.util.List;
import java.util.Scanner;

public class BankInterface {
    static List<Account> accounts=DatabaseInterface.getDatabase().getAccountList();
    /*
    query these accounts for cardNo, cardType(debit(they're all debit)/credit), PIN, and AuthorizationNo
    Someone will need to fill the AccountList.csv TODO
    */


       static String getReceipt(String cardNo)
   {
       String lastFour = cardNo.substring(cardNo.length() - 4);
       lastFour="Your receipt:\nLast four digits: " + lastFour;
       return lastFour;
    }

    public static void GetCardDNo(){
        String  DebitAnswer, cardNo, PIN;
        int i = 0;   
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Card Number:");
        cardNo = input.nextLine();
        
        int checkr = 0;
 
        int size;
 
        size = accounts.size();
 
        
        for(i=0;i<size;i++){
            if( cardNo.equals(accounts.get(i).getCardNo()))
            {
                    System.out.println("Enter PIN number:");
                    PIN = input.nextLine();
                    if(PIN.equals(accounts.get(i).getPin()))
                    {
                        System.out.println("Card Accepted");
                        checkr = 1;
                        System.out.println(getReceipt(cardNo));
                        
                    }
                }
                else{
                    System.out.println("Invalid option entered");
                }
            }
         if(checkr == 0){
            System.out.println("Card not recognized");
 
        }
 
    }
     
    public static void GetCardCNo(){
        String  DebitAnswer, cardNo, PIN;
        int i = 0;   
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Card Number:");
        cardNo = input.nextLine();
        
        int checkr = 0;
 
        int size;
 
        size = accounts.size();
 
        
        for(i=0;i<size;i++){
            if( cardNo.equals(accounts.get(i).getCardNo()))
            {
                String zipcode;
                Scanner in = new Scanner(System.in);
                System.out.println("Please Enter Zip Code");
                zipcode = in.nextLine();
                 
                System.out.println("Card Accepted");
                checkr = 1;
                System.out.println(getReceipt(cardNo));
     
                }
                else{
                    System.out.println("Invalid option entered");
                }
            }
         if(checkr == 0){
            System.out.println("Card not recognized");
 
         }
    }
}

