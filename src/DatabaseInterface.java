

public class DatabaseInterface {
    private static Database database = new Database();

    public static Database getDatabase(){
        return database;
    }


    static Report prepareDailyReport(){//called by BusinessLogic
        Report d = new Report(ReportType.DAILY);
        d.setLines();
        return d;
    }

    static Report prepareInventoryReport(){//called by BusinessLogic
        Report i = new Report(ReportType.INVENTORY);
        i.setLines();
        return i;
    }

    static Report prepareReceipt(Transaction transaction, Account account){//called by payCash and payCard
        Report r = new Report(ReportType.RECEIPT);
        r.setLines();
        return r;
    }
}
