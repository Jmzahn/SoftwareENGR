import java.util.Scanner;

public class BankInterface {
    public static void GetCardNo(){
        String  DebitAnswer, cardNo, PIN;
        int i = 0;
        String[] StoredCardNo = {"12345678", "23456790", "345678901"};
        String[] StoredPINNo = {"1234", "2345", "3456"};        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Card Number:");
        cardNo = input.nextLine();
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
                        getReceipt(cardNo);
                    }
                }
                else
                {
                    System.out.println("Card Accepted");
                    getReceipt(cardNo);
                }
            }
            else{
                System.out.println("Card not recognized");

            } 

        }


    }
    public String getReceipt(string cardNo)
    {
        lastFour = cardNo.substring(cardNo.length() - 4);
        return System.out.println("Your receipt:\nLast four digits: " + lastFour);

    }


public static void main(String[] args)
{
    GetCardNo();
}
    
}

