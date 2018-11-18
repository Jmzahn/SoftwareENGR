

public class DatabaseInterface {
    private static Database database = new Database();

    static Database getDatabase(){
        return database;
    }

    static void setDatabase(Database d){
        database=d;
    }


    static Report prepareDailyReport(){//called by BusinessLogic
        Report d = new Report(ReportType.DAILY);
        d.makeHeader();
        return d;
    }

    static Report prepareInventoryReport(){//called by BusinessLogic
        Report i = new Report(ReportType.INVENTORY);
        i.makeHeader();
        return i;
    }


}
