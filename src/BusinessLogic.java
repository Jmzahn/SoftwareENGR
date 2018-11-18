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
        return r;
    }

    static double computeTax(double preTax) {
        return preTax * 1.0825;
    }
}
