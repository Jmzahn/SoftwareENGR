import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Database {
    private List<Item> inventoryList;
    private List<Transaction> transactionLogList;
    private List<Account> accountList;
    
    private String _inventoryListFilename = "./Database/InventoryList.csv";
    private String _transactionLogListFilename = "./Database/TransactionLogList.csv";
    private String _accountListFilename = "./Database/AccountList.csv";

    public Database(){
        this.inventoryList = readFromFile(Item.class, this._inventoryListFilename);
        this.transactionLogList = readFromFile(Transaction.class, this._transactionLogListFilename);
        this.accountList = readFromFile(Account.class, this._accountListFilename);
    }

    private <T extends Parsable> List<T> readFromFile(Class<T> class, String fileName){
        List<T> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(',');
                if(fields.length > 0){
                    list.add(class.parse(fields));
                }
                line = reader.readLine();
            }
            line = null;
            reader.close();
        }
        return list;
    }

    // write changes to CSV file
    private <T extends Parsable> void commit(List<T> list, String fileName){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            for(T item : list){
                String line = "";
                var arr = item.toArray();
                for(int i = 0; i < arr.length; i++){
                    line = line + arr[i];
                }
                out.writeLine(line);
            }
            out.close();
        }
    }

    public Item[] getInventoryList(){
        return this.inventoryList;
    }

    public void setInventoryList(Item[] items){
        this.inventoryList = items;
        commit(this.inventoryList, this._inventoryListFilename);
    }

    public Transaction[] getTransactionLogList(){
        return this.transactionLogList;
    }

    public void setTransactionLogList(Transaction[] transactions){
        this.transactionLogList = transactions;
        commit(this.transactionLogList, this._transactionLogListFilename);
    }

    public Account[] getAccountList(){
        return this.accountList;
    }

    public void setAccountList(Account[] accounts){
        this.accountList = accounts;
        commit(this.accountList, this._accountListFilename);
    }
}
