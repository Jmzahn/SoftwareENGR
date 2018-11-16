public class Database {
    private Item[] inventoryList;
    private Transaction[] transactionLogList;
    private Account[] accountList;

    public Database(){
        
    }

    public Item[] getInventoryList(){
        return this.inventoryList;
    }

    public void setInventoryList(Item[] items){
        this.inventoryList = items;
    }

    public Transaction[] getTransactionLogList(){
        return this.transactionLogList;
    }

    public void setTransactionLogList(Transaction[] transactions){
        this.transactionLogList = transactions;
    }

    public Account[] getAccountList(){
        return this.accountList;
    }

    public void setAccountList(Account[] accounts){
        this.accountList = accounts;
    }
}
