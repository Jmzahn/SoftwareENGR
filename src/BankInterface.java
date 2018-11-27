
import java.util.List;
import java.util.Scanner;

class BankInterface {
    private static List<Account> accounts;
    /*
    query these accounts for cardNo, cardType(debit(they're all debit)/credit), PIN, and AuthorizationNo
    Someone will need to fill the AccountList.csv TODO
    */




    static Account GetCardDNo(){
           accounts=DatabaseInterface.getDatabase().getAccountList();
        String  cardNo, PIN;
        int i;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Card Number:");
        cardNo = input.nextLine();
        

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
     
    static Account GetCardCNo(){
        String  cardNo;
        accounts=DatabaseInterface.getDatabase().getAccountList();
        int i;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Card Number:");
        cardNo = input.nextLine();
        

 
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

    static String getAuthNo(){
        Scanner in=new Scanner(System.in);
        System.out.print("Authorization Center says:\n Enter Authorization number: ");
        String auth=in.nextLine();
        in.close();
        return auth;
    }
}

