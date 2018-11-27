import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerInterface////I added a static Database to DatabaseInterface that you can pull via get method, and push via set
{
    //Im guessing this will be implemented by interaction with the console,
    // as will all IO i.e. Bank Interface, Employee Interface -JZ



    private static Database database;
    private static Scanner input;
    private static Transaction transaction;
    private static double subTotal,total;

    static void welcome(){
        System.out.println("Welcome to checkout");
        input = new Scanner(System.in);
        System.out.println("Press Enter to start");     // Initiates the system
        String cardNo = input.nextLine();
        startCheckout();
        scan();         // Calls the scan function

    }

    private static void startCheckout(){
        database=DatabaseInterface.getDatabase();
        transaction=new Transaction();
        subTotal=transaction.getTotal();//sets to default==0.0
    }
    private static void scan(){//use Transaction.add(Item i) to add these items to transaction, make sure to update subtotal
        System.out.println("Please scan items");

        List< Item > items = database.getInventoryList();

        int count = 1;

        while(count!= 0){
            System.out.println("Please enter name of item: ");
            String itemName = input.nextLine();
            for(int i =0; i < items.size(); i++){
                if( itemName.equals(items.get(i).name)){         // Check to make sure the item exist
                    transaction.addItem(items.get(i));                  // Add item to the transaction list
                }
            }
            // total,subtotals, cancel
            System.out.println("Enter 1 to add another item\n");
            System.out.println("Enter 2 to check the current total\n");
            System.out.println("Enter 3 to show total\n");
            System.out.println("Enter 4 to cancel the payment\n");
            
            int select = input.nextInt();
            input.nextLine();
            if(select == 1){
                // Do nothing and continue loop
            }
            if(select == 2){
                displaySubTotal();
            }
            if(select == 3){
                displayTotal();
                selectPayment();
                count = 0;
            }
            if(select == 4){
                cancel(0);
                count = 0;
            }
            else{
                System.out.println("Invalid option selected");
            }

            

        }
        

    }


    private static void displaySubTotal(){
        System.out.println(subTotal);
    }
    private static void displayTotal(){
        total=BusinessLogic.computeTax(subTotal);
        System.out.println(total);
    }
    private static void selectPayment()
    {
        Scanner selectpay = new Scanner(System.in);
        
        int selector;
        
        try{
            // Payment type selection menu
            System.out.println("Press 0 to cancel payment");
            System.out.println("Press 1 for Cash");
            System.out.println("Press 2 for Credit");
            System.out.println("Press 3 for Debit");
            System.out.print("Please select payment type:");
            selector = selectpay.nextInt();
            Account account;
        
            if(selector == 0){
                cancel(0);
            }
            else if(selector == 1){
                payCash();                      // 
            }
            else if(selector == 2 ){
                account=BankInterface.GetCardDNo();      // Calls bank interface to approve payment ( Credit )
                BusinessLogic.prepareReceipt(transaction,account);
                cancel(1);
            }
            else if(selector == 3){
                account=BankInterface.GetCardCNo();      // Calls bank interface to approve payment ( Debit )
                BusinessLogic.prepareReceipt(transaction,account);
                cancel(1);
            }
            else{
                System.out.println("Must select an option");    //a User gives number grreater then 3 or less than 0
            }
        }
        catch(InputMismatchException e){
            e.printStackTrace();
        //    System.err.println("Must give valid input ");
        }

    }

    private static void payCash()
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
                BusinessLogic.prepareReceipt(transaction,null);//get receipt with null account
            }
            else//otherwise just print the receipt
            {
                System.out.println("\nTransaction complete!\nPrinting receipt.");
                BusinessLogic.prepareReceipt(transaction,null);//get receipt with null account
            }
        }//not sure where to go from here, im guessing welcome page for next customer
        welcome();
    }
    private static void cancel(int t)//t==0 for cancel payment, t==1 for cancel order
    {
        if( t == 0 ){
            System.out.println("\nPayment method has been canceled");
            scan();
        }
        if( t == 1 ){
            System.out.println("\nOrder has been canceled");
            transaction=null;
            welcome();
        }
    }
}
