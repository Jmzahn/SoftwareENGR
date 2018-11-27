import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Item> inventoryList;
    private List<Transaction> transactionLogList;
    private List<Account> accountList;

    final private String _inventoryListFilename = "./Database/InventoryList.csv";
    final private String _transactionLogListFilename = "./Database/TransactionLogList.csv";
    final private String _accountListFilename = "./Database/AccountList.csv";

    Database(){
        this.inventoryList = readItemsFromFile();
        this.transactionLogList = readTransactionsFromFile();
        this.accountList = readAccountsFromFile();
    }

    private List<Item> readItemsFromFile(){
        List<Item> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(_inventoryListFilename));
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                if(fields.length > 0){
                    list.add(new Item(fields));
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    private List<Account> readAccountsFromFile(){
        List<Account> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(_accountListFilename));
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                if(fields.length > 0){
                    list.add(new Account(fields));
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private List<Transaction> readTransactionsFromFile(){
        List<Transaction> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(_transactionLogListFilename));
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                if(fields.length > 0){
                    list.add(new Transaction(fields));
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // write changes to CSV file
    private <T extends Parsable> void commit(List<T> list, String fileName){
        commit(list, fileName, true);
    }
    private <T extends Parsable> void commit(List<T> list, String fileName, boolean tryAgain){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            for(T item : list){
                String[] arr = item.toArray();
                String line = arr[0];
                for(int i = 1; i < arr.length; i++){
                    line += "," + arr[i];
                }
                line = line + "\n";
                out.write(line);
            }
            out.close();
        }
        catch (FileNotFoundException e){
            if(tryAgain){
                try {
                    File file = new File(fileName);
                    file.createNewFile();
                    commit(list, fileName, false);
                }
                catch (IOException x){
                    x.printStackTrace();
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Item> getInventoryList(){
        return this.inventoryList;
    }

    public void setInventoryList(List<Item> items){
        this.inventoryList = items;
        commit(this.inventoryList, this._inventoryListFilename);
    }

    public List<Transaction> getTransactionLogList(){
        return this.transactionLogList;
    }

    public void setTransactionLogList(List<Transaction> transactions){
        this.transactionLogList = transactions;
        commit(this.transactionLogList, this._transactionLogListFilename);
    }

    public List<Account> getAccountList(){
        return this.accountList;
    }

    public void setAccountList(List<Account> accounts){
        this.accountList = accounts;
        commit(this.accountList, this._accountListFilename);
    }
}
