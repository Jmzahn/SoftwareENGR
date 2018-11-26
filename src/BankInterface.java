
import java.util.List;
import java.util.Scanner;

public class BankInterface {
    List<Account> accounts=DatabaseInterface.getDatabase().getAccountList();
    /*
    query these accounts for cardNo, cardType(debit(they're all debit)/credit), PIN, and AuthorizationNo
    Someone will need to fill the AccountList.csv TODO
    */

   public static void GetCardNo(){
       String  DebitAnswer, cardNo, PIN;
       int i = 0;
       String[] StoredCardNo = {"12345678", "23456790", "345678901"};
       String[] StoredPINNo = {"1234", "2345", "3456"};        
       Scanner input = new Scanner(System.in);
       System.out.println("Enter Card Number:");
       cardNo = input.nextLine();
       
       int checkr = 0;
       
       for(i=0;i<3;i++){
           if( cardNo.equals(StoredCardNo[i]))
           {
               System.out.println("Is this a debit card? Y/N");
               DebitAnswer = input.nextLine();
               if(DebitAnswer.equals("Y") || (DebitAnswer.equals("y")))
               {
                   System.out.println("Enter PIN number:");
                   PIN = input.nextLine();
                   if(PIN.equals(StoredPINNo[i]))
                   {
                       System.out.println("Card Accepted");
                       checkr = 1;
                       System.out.println(getReceipt(cardNo));
                   }
               }
               else if(DebitAnswer.equals("N") || (DebitAnswer.equals("n")))
               {
                   System.out.println("Card Accepted");
                   System.out.println(getReceipt(cardNo));
                   checkr = 1;
               }
               else{
                   System.out.println("Invalid option entered");
               }
           }
           
           else{
                   System.out.println("Card not recognized");
               }
       }
       if(checkr == 0){
           System.out.println("Card not recognized");
   }
   
    
}
       static String getReceipt(String cardNo)
   {
       String lastFour = cardNo.substring(cardNo.length() - 4);
       lastFour="Your receipt:\nLast four digits: " + lastFour;
       return lastFour;
    }

public static void main(String[] args)
{
   GetCardNo();
}

}
