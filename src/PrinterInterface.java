//Team Red, Jacob Zahn, Christopher Gee , James Fallon, Ryan Pratt
import java.util.ArrayList;

class PrinterInterface {
    static void printReport(Report r){
        ArrayList<String> lines=r.getLines();
        for (String l:lines)
        {
            System.out.println(l);
        }
    }
}
