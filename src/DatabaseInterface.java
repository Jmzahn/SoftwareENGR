import java.util.List;

public class DatabaseInterface {
    public static void updateInv(Item newItem){
        Database database = new Database();
        List<Item> inventory = database.getInventoryList();
        int i = 0;
        boolean found = false;
        while(!found && i < inventory.size()){
            found = inventory.get(i).name.toUpperCase().equals(newItem.name.toUpperCase());
            i++;
        }
        if(found) {
            inventory.set(i, newItem);
        }
        else {
            inventory.add(newItem);    
        }
        database.setInventoryList(inventory);
    }

    public static Report prepareDailyReport(){
        Report d = new Report(ReportType.DAILY);

        return d;
    }

    public static Report prepareInventoryReport(){
        Report i = new Report(ReportType.INVENTORY);

        return i;
    }

    public static void printReceipt(Transaction transaction, Account account){
        //TODO
    }
}
