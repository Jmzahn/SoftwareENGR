//Team Red, Jacob Zahn, Christopher Gee , James Fallon, Ryan Pratt

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class CustomerInterface////I added a static Database to DatabaseInterface that you can pull via get method, and push via set
{
    //Im guessing this will be implemented by interaction with the console,
    // as will all IO i.e. Bank Interface, Employee Interface -JZ



    private static Database database;

    private static Transaction transaction;
    private static double subTotal,total;
    private static Scanner input;
    static void welcome(Scanner in){
        input=in;
        while(true)
        {
            
            try {
                System.out.println("Welcome to checkout");

                System.out.println("Press y to start");

                String cardNo=input.nextLine();
                if (cardNo.toUpperCase().startsWith("Y"))
                {
                    startCheckout();
                    scan();
                }
                else
                    break;

            }catch (Exception e)
            {
                e.printStackTrace();
                break;
            }
            
        }


    }

    private static void startCheckout(){
        database=DatabaseInterface.getDatabase();
        transaction=new Transaction();
        subTotal=transaction.getTotal();//sets to default==0.0
    }
    private static void scan(){//use Transaction.add(Item i) to add these items to transaction, make sure to update subtotal
        // System.out.println("Please scan items");

        List< Item > items = database.getInventoryList();
        
        int count = 1;

        while(count != 0){
            // total,subtotals, cancel
            System.out.println("Enter 1 to scan item");
            System.out.println("Enter 2 to check the sub-total");
            System.out.println("Enter 3 to finalize the transaction");
            System.out.print("Enter 4 to cancel the transaction: ");
            
            String select = input.nextLine();

            if(Integer.parseInt(select)==1){
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
            else if(Integer.parseInt(select)== 2){
                displaySubTotal();
            }
            else if(Integer.parseInt(select)== 3){
                displayTotal();
                selectPayment();

                count=0;
            }
            else if(Integer.parseInt(select)== 4){
                cancel(1);
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
        if(total % .01 != 0){
            total = total - (total % .01) + .01;
        }
        System.out.println(total);
    }
    private static void selectPayment()
    {
        
        Account account;
        String selector;
        
        try{
            // Payment type selection menu
            System.out.println("Press 0 to cancel payment");
            System.out.println("Press 1 for Cash");
            System.out.println("Press 2 for Credit");
            System.out.println("Press 3 for Debit");
            System.out.println("Press 4 to cancel transaction");
            System.out.print("Please select payment type:");
            selector = input.nextLine();

        
            if(Integer.parseInt(selector)== 0){
                cancel(0);
            }
            else if(Integer.parseInt(selector)== 1){
                payCash();
            }
            else if(Integer.parseInt(selector)== 2 ){
                account=BankInterface.GetCardCNo(input);      // Calls bank interface to approve payment ( Credit )
                if(account==null)
                {
                    cancel(0);
                    return;
                }

                BusinessLogic.prepareReceipt(transaction,account);
                System.out.println();
                BusinessLogic.prepareReceipt(transaction,account);
                database.getTransactionLogList().add(transaction);
            }
            else if(Integer.parseInt(selector)== 3){
                account=BankInterface.GetCardDNo(input);      // Calls bank interface to approve payment ( Debit )
                if(account==null)
                {
                    cancel(0);
                    return;
                }

                BusinessLogic.prepareReceipt(transaction,account);
                System.out.println();
                BusinessLogic.prepareReceipt(transaction,account);
                database.getTransactionLogList().add(transaction);
            }
            else if(Integer.parseInt(selector)== 4){
                cancel(1);

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
        double insertedCash;//since we just got here they haven't put cash in yet
        String cash;

        System.out.println("Insert cash into bill or coin readers (double)\n (enter -1 to cancel payment) : ");//prompt for cash
        cash = input.nextLine();
        insertedCash=Double.parseDouble(cash);

        
        insertedCash = (double) ((int) (insertedCash * 100)) / 100;
        if(insertedCash==-1)
            cancel(0);
        if(insertedCash<total)//cancel order if insufficient payment
            cancel(1);
        else//otherwise complete it
        {
            double dif= (double) ((int)(insertedCash * 100) - (int)(total * 100)) / 100;//calculate change
            if(dif!=0)//if we need to dispense change do so
            {
                System.out.println("Dispensing Change...  "+dif+"\nTransaction complete!\nPrinting receipt.");
                BusinessLogic.prepareReceipt(transaction,null);//get receipt with null account
                System.out.println();
                BusinessLogic.prepareReceipt(transaction,null);
            }
            else//otherwise just print the receipt
            {
                System.out.println("\nTransaction complete!\nPrinting receipt.");
                BusinessLogic.prepareReceipt(transaction,null);//get receipt with null account
                System.out.println();
                BusinessLogic.prepareReceipt(transaction,null);
            }
            database.getTransactionLogList().add(transaction);
        }

    }
    private static void cancel(int t)//t==0 for cancel payment, t==1 for cancel order
    {
        if( t == 0 ){
            System.out.println("\nPayment method has been canceled");

        }
        else if( t == 1 ){
            System.out.println("\nOrder has been canceled");
            transaction=null;
        }
    }
}
