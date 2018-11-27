import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerInterface////I added a static Database to DatabaseInterface that you can pull via get method, and push via set
{
    //Im guessing this will be implemented by interaction with the console,
    // as will all IO i.e. Bank Interface, Employee Interface -JZ



    private static Database database;

    private static Transaction transaction;
    private static double subTotal,total;

    static void welcome(){
        System.out.println("Welcome to checkout");
        Scanner input = new Scanner(System.in);
        System.out.println("Press Enter to start");     // Initiates the system
        String cardNo = input.nextLine();
        startCheckout();
        scan();         // Calls the scan function
        input.close();
    }

    static void startCheckout(){
        database=DatabaseInterface.getDatabase();
        transaction=new Transaction();
        subTotal=transaction.getTotal();//sets to default==0.0
    }
    static void scan(){//use Transaction.add(Item i) to add these items to transaction, make sure to update subtotal
        // System.out.println("Please scan items");

        List< Item > items = database.getInventoryList();
        Scanner input=new Scanner(System.in);
        int count = 1;

        while(count != 0){
            // total,subtotals, cancel
            System.out.println("Enter 1 to scan item");
            System.out.println("Enter 2 to check the sub-total");
            System.out.println("Enter 3 to finalize the transaction");
            System.out.println("Enter 4 to cancel the payment");
            
            int select = input.nextInt();
            input.nextLine();
            if(select == 1){
                System.out.println("Please enter name of item: ");
                String itemName = input.nextLine();
                for(int i =0; i < items.size(); i++){
                    if( itemName.equals(items.get(i).name)){         // Check to make sure the item exist
                                        // Add item to the transaction list 

                        if(items.get(i).isBooze){
                            String booz;
                            System.out.println("Enter alcohol confirmation code: 1 = confirm, 2 = decline");
                            booz = input.nextLine();
                            if(booz.equals("1")){
                                transaction.addItem(items.get(i));
                                subTotal += items.get(i).price*(1-items.get(i).discount);
                                System.out.println(items.get(i));
                                System.out.println(items.get(i).description);
                                System.out.println(items.get(i).price);

                            }
                            else{
                                cancel(1);      // Cops are on the way, you're underage
                            }

                        }
                        else{
                            transaction.addItem(items.get(i));
                            subTotal += items.get(i).price*(1-items.get(i).discount);
                            System.out.println(items.get(i).description);
                            System.out.println(items.get(i).price);
                        }
                    }
                }
            }
            else if(select == 2){
                displaySubTotal();
            }
            else if(select == 3){
                displayTotal();
                selectPayment();
                count = 0;
            }
            else if(select == 4){
                cancel(0);
                count = 0;
            }
            else{
                System.out.println("Invalid option selected");
            }

        }
        input.close();

    }


    private static void displaySubTotal(){
        System.out.println(subTotal);
    }
    static void displayTotal(){
        total=BusinessLogic.computeTax(subTotal);
        if(total % .01 != 0){
            total = total - (total % .01) + .01;
        }
        System.out.println(total);
    }
    private static void selectPayment()
    {
        Scanner selectpay = new Scanner(System.in);
        Account account;
        int selector;
        
        try{
            // Payment type selection menu
            System.out.println("Press 0 to cancel payment");
            System.out.println("Press 1 for Cash");
            System.out.println("Press 2 for Credit");
            System.out.println("Press 3 for Debit");
            System.out.print("Please select payment type:");
            selector = selectpay.nextInt();

        
            if(selector == 0){
                cancel(0);
            }
            else if(selector == 1){
                payCash();                      // 
            }
            else if(selector == 2 ){
                account=BankInterface.GetCardCNo();      // Calls bank interface to approve payment ( Credit )
                BusinessLogic.prepareReceipt(transaction,account);
                welcome();
            }
            else if(selector == 3){
                account=BankInterface.GetCardDNo();      // Calls bank interface to approve payment ( Debit )
                BusinessLogic.prepareReceipt(transaction,account);
                welcome();
            }
            else{
                System.out.println("Must select an option");    //a User gives number greater then 3 or less than 0
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

        Scanner cashIn=new Scanner(System.in);
        System.out.println("Insert cash into bill or coin readers (double) : ");//prompt for cash
        insertedCash = cashIn.nextDouble();
        cashIn.nextLine();
        
        insertedCash = (double) ((int) (insertedCash * 100)) / 100;

        if(insertedCash<total)//cancel order if insufficient payment
            cancel(1);
        else//otherwise complete it
        {
            double dif= (double) ((int)(insertedCash * 100) - (int)(total * 100)) / 100;//calculate change
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
