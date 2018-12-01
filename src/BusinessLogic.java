//Team Red, Jacob Zahn, Christopher Gee , James Fallon, Ryan Pratt
import java.util.ArrayList;
import java.util.List;

class BusinessLogic {
    private static Report daily;
    private static Report inventory;

    static void timerSignal(){//called by TimerInterface
        prepareReports();
        printReports();
        daily=null;//sets reports to null after done
        inventory=null;
    }

    private static void prepareReports(){//called by timerSignal, populates reports
        daily = DatabaseInterface.prepareDailyReport();
        inventory = DatabaseInterface.prepareInventoryReport();
    }

    private static void printReports(){//called by timerSignal, prints reports
        PrinterInterface.printReport(daily);
        System.out.println();
        PrinterInterface.printReport(inventory);
    }


    static void prepareReceipt(Transaction transaction, Account account){//called by payCash and payCard
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
        r.addLines(lines);
        if(account==null)
        {
            r.makeFooter();
            PrinterInterface.printReport(r);
            return;
        }
        line="+------------------+---------+";
        r.addLine(line);
        line="| Last 4 CardNo    | AuthNo  |";
        r.addLine(line);
        format="| %-16s | %-7s |";
        line=String.format(format,account.getCardNo().subSequence(4,8),account.getAuthNo());
        r.addLine(line);
        line="+---------------+------------+";
        r.addLine(line);
        r.makeFooter();
        PrinterInterface.printReport(r);
    }

    static double computeTax(double preTax) {
        return preTax * 1.0825;
    }
}
