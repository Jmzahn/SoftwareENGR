import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerInterface
{
    //Im guessing this will be implemented by interaction with the console,
    // as will all IO i.e. Bank Interface, Employee Interface -JZ
    static Transaction transaction;
    static double subTotal,total;
    static void welcome(){

    }
    static void startCheckout(){
        transaction=new Transaction();
        subTotal=transaction.getTotal();//sets to default==0.0
    }
    static void scan(){//use Transaction.add(Item i) to add these items to transaction, make sure to update subtotal

    }
    static void displaySubTotal(){

    }
    static void displayTotal(){
        total=BusinessLogic.computeTax(subTotal);
    }
    static void selectPayment()
    {

    }

    static void payCash()
    {
        double insertedCash=0.0;

        //This while loop prompts for cash and handles some errors
        boolean passed=false;
        while (!passed)
        {
            System.out.println("Insert cash into bill or coin readers (double) : ");
            Scanner cashIn=new Scanner(System.in);
            try{
                insertedCash=cashIn.nextDouble();
            }catch (InputMismatchException e)
            {
                e.printStackTrace();
                System.err.println("Must give double.");
            }

            if(insertedCash%.01==0)
                passed=true;
            else
                System.err.println("Inserted cash cannot be a value with more than two floating values. Retry.");

        }

        if(insertedCash<total)
            cancel(1);
        else
        {
            double dif=insertedCash-total;//calculate change
            if(dif!=0)//if we need to dispense change do so
            {
                System.out.println("Dispensing Change...\n"+dif+"\nTransaction complete!\nPrinting receipt.");
                PrinterInterface.printReceipt(transaction);
            }
            else//otherwise just print the receipt
            {
                System.out.println("\nTransaction complete!\nPrinting receipt.");
                PrinterInterface.printReceipt(transaction);
            }
        }
    }
    static void cancel(int t)//t==0 for cancel payment, t==1 for cancel order
    {

    }
}
