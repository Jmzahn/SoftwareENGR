import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.NoSuchMethodException;
import java.util.ArrayList;
import java.util.List;

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

    private <T extends Parsable> List<T> readFromFile(Class<T> klass, String fileName){
        List<T> list = new ArrayList<>();
        try {
            Constructor<T> ctor = klass.getConstructor(String[].class);
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                if(fields.length > 0){
                    list.add(ctor.newInstance(fields));
                }
                line = reader.readLine();
            }
            line = null;
            reader.close();
        }
        catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            return list;
        }
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
                    line = line + "," + arr[i];
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
