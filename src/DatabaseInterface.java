

public class DatabaseInterface {
    public static Database database = new Database();

    public static Report prepareDailyReport(){//called by BusinessLogic
        Report d = new Report(ReportType.DAILY);
        d.setLines();
        return d;
    }

    public static Report prepareInventoryReport(){//called by BusinessLogic
        Report i = new Report(ReportType.INVENTORY);
        i.setLines();
        return i;
    }

    public static Report prepareReceipt(Transaction transaction, Account account){//called by payCash and payCard
        Report r = new Report(ReportType.RECEIPT);
        r.setLines();
        return r;
    }
}
