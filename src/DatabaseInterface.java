

public class DatabaseInterface {


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
