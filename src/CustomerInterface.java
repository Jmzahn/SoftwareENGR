import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerInterface////I added a static Database to DatabaseInterface that you can pull via get method, and push via set
{
    //Im guessing this will be implemented by interaction with the console,
    // as will all IO i.e. Bank Interface, Employee Interface -JZ

    Database database;

    static Transaction transaction;
    static double subTotal,total;

    static void welcome(){
        System.out.println("Welcome to checkout");
    }

    static void startCheckout(){
        transaction=new Transaction();
        subTotal=transaction.getTotal();//sets to default==0.0
    }
    static void scan(){//use Transaction.add(Item i) to add these items to transaction, make sure to update subtotal

    }
    static void displaySubTotal(){
        System.out.println(subTotal);
    }
    static void displayTotal(){
        total=BusinessLogic.computeTax(subTotal);
        System.out.println(total);
    }
    static void selectPayment()
    {
        Scanner selectpay = new Scanner(System.in);
        
        int selector;
        
        try{
            // Payment type selection menu
            System.out.println("Press 0 to Cancel");
            System.out.println("Press 1 for Cash");
            System.out.println("Press 2 for Credit");
            System.out.println("Press 3 for Debit");
            System.out.print("Please select payment type:");
            selector = selectpay.nextInt();
        
            if(selector == 0){
                cancel(0);
            }
            else if(selector == 1){
                payCash();
            }
            else if(selector == 2 ){
                BankInterface.GetCardNo();      // Calls bank interface to approve payment ( Credit )
            }
            else if(selector == 3){
                BankInterface.GetCardNo();      // Calls bank interface to approve payment ( Debit )
            }
            else{
                System.out.println("Must select an option");    //a User gives number grreater then 3 or less than 0
            }
        }
        catch(InputMismatchException e){
            e.printStackTrace();
            System.err.println("Must give valid input ");  
        }

    }

    static void payCash()
    {
        double insertedCash=0.0;//since we just got here they haven't put cash in yet

        //This while loop prompts for cash and handles some errors
        boolean passed=false;
        Scanner cashIn=new Scanner(System.in);
        while (!passed)
        {
            System.out.println("Insert cash into bill or coin readers (double) : ");//prompt for cash

            try{//try and grab double
                insertedCash=cashIn.nextDouble();
                if(insertedCash%.01==0)//check precision
                    passed=true;
                else
                    System.err.println("Inserted cash cannot be a value with more than two floating values. Retry.");//yell at customer

            }catch (InputMismatchException e)
            {
                e.printStackTrace();
                System.err.println("Must give double.");//yell at customer
            }
        }

        if(insertedCash<total)//cancel order if insufficient payment
            cancel(1);
        else//otherwise complete it
        {
            double dif=insertedCash-total;//calculate change
            if(dif!=0)//if we need to dispense change do so
            {
                System.out.println("Dispensing Change...  "+dif+"\nTransaction complete!\nPrinting receipt.");
                Report receipt = BusinessLogic.prepareReceipt(transaction,null);//get receipt with null account

                PrinterInterface.printReport(receipt);
            }
            else//otherwise just print the receipt
            {
                System.out.println("\nTransaction complete!\nPrinting receipt.");
                Report receipt = BusinessLogic.prepareReceipt(transaction,null);//get receipt with null account

                PrinterInterface.printReport(receipt);
            }
        }//not sure where to go from here, im guessing welcome page for next customer
        welcome();
    }
    static void cancel(int t)//t==0 for cancel payment, t==1 for cancel order
    {
        if( t == 0 ){
            System.out.println("\nPayment method has been cancled");
        }
        if( t == 1 ){
            System.out.println("\nOrder has been cancled");
        }
    }
}
