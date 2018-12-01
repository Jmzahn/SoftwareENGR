//Team Red, Jacob Zahn, Christopher Gee , James Fallon, Ryan Pratt
import java.util.List;
import java.util.Scanner;

class BankInterface {
    private static List<Account> accounts;
    /*
    query these accounts for cardNo, cardType(debit(they're all debit)/credit), PIN, and AuthorizationNo
    Someone will need to fill the AccountList.csv
    */
    private static Scanner input;



    static Account GetCardDNo(Scanner in){
           accounts=DatabaseInterface.getDatabase().getAccountList();
        String  cardNo, PIN;
        int i;
        input = in;
        System.out.println("Enter Card Number (enter -1 to cancel payment):");
        cardNo = input.nextLine();

        if(cardNo.equals("-1"))
            return null;
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
                    accounts.get(i).setAuthNo(getAuthNo());
                    return accounts.get(i);


                }
            }
            else{
                System.out.println("Invalid option entered");
            }
        }

        System.out.println("Card not recognized");
 
        return null;
    }
     
    static Account GetCardCNo(Scanner in){
        String  cardNo;
        accounts=DatabaseInterface.getDatabase().getAccountList();
        int i;
        input =in;
        System.out.println("Enter Card Number (enter -1 to cancel payment):");
        cardNo = input.nextLine();


        if(cardNo.equals("-1"))
            return null;
        int size;
 
        size = accounts.size();
 
        
        for(i=0;i<size;i++){
            if( cardNo.equals(accounts.get(i).getCardNo()))
            {
                String zipcode;
                System.out.println("Please Enter Zip Code");
                zipcode = input.nextLine();
                 
                System.out.println("Card Accepted");
                accounts.get(i).setAuthNo(getAuthNo());
                return accounts.get(i);

     
                }
                else{
                    System.out.println("Invalid option entered");
                }
            }

            System.out.println("Card not recognized");


         return null;
    }

    private static String getAuthNo(){

        System.out.print("Authorization Center says:\n Enter Authorization number: ");
        return input.nextLine();
    }
}

