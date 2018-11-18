import java.util.ArrayList;

public class PrinterInterface {
    static void printReport(Report r){
        ArrayList<String> lines=r.getLines();
        for (String l:lines)
        {
            System.out.print(l);
        }
    }
}
