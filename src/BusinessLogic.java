public class BusinessLogic {//TODO
    private static Report daily = new Report(ReportType.DAILY);
    private static Report inventory = new Report(ReportType.INVENTORY);
    static void timerSignal(){
        prepareReports();
        printReports();
    }

    static void prepareReports(){
        daily = DatabaseInterface.prepareDailyReport();
        inventory = DatabaseInterface.prepareInventoryReport();
    }

    static void printReports(){
        //TODO
    }
    static double computeTax(double preTax) {
        return preTax * 1.0825;
    }
}
