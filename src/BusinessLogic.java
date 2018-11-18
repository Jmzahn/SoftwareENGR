import java.util.ArrayList;
import java.util.List;

public class BusinessLogic {//TODO
    private static Report daily = new Report(ReportType.DAILY);
    private static Report inventory = new Report(ReportType.INVENTORY);
    static void timerSignal(){
        prepareReports();
        printReports();
        daily=null;
        inventory=null;
    }

    private static void prepareReports(){
        daily = DatabaseInterface.prepareDailyReport();
        inventory = DatabaseInterface.prepareInventoryReport();
    }

    private static void printReports(){
        PrinterInterface.printReport(daily);
        PrinterInterface.printReport(inventory);
    }


    static Report prepareReceipt(Transaction transaction, Account account){//called by payCash and payCard
        Report r = new Report(ReportType.RECEIPT);
        r.makeHeader();

        List<String> lines=new ArrayList<>();
        String format=r.getFormat();
        String line;
        List<Item> items=transaction.getCart();
        for(Item item:items)
        {
            line = String.format(format,item.name,item.price);
            lines.add(line);
        }
        if(account==null)
        {
            r.makeFooter();
            return r;
        }
        line="├───────────────┬──┴─────────┤";
        r.addLine(line);
        line="│ Last 4 CardNo │ AuthNo │";
        r.addLine(line);
        format="│ %-13s │ %-6s │";
        line=String.format(format,account.cardNo.subSequence(12,15),account.authNo);
        r.addLine(line);
        line="├───────────────┴────────────┤";
        r.addLine(line);
        r.makeFooter();
        return r;
    }

    static double computeTax(double preTax) {
        return preTax * 1.0825;
    }
}
