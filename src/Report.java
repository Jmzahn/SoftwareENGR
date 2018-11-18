import java.util.ArrayList;
import java.util.Collection;

enum ReportType{
    DAILY,INVENTORY,RECEIPT
}

public class Report {
    private ArrayList<String> lines;
    private ReportType type;
    private String leftAlignFormat;

    public Report(ReportType t){
        this.type=t;
        this.lines=new ArrayList<>();
        this.leftAlignFormat="";
    }

    public ReportType getType(){
        return this.type;
    }

    public String getFormat(){
        return this.leftAlignFormat;
    }

    public void makeHeader(){
        if(this.type==ReportType.DAILY)
        {
            this.leftAlignFormat = "| %-15s | %-4d |%n";

            this.lines.add("+-----------------+------+%n");
            this.lines.add("| Daily Report           |%n");
            this.lines.add("+-----------------+------+%n");
            this.lines.add("| Product         | #sold|%n");
            this.lines.add("+-----------------+------+%n");
        }
        if(this.type==ReportType.INVENTORY)
        {
            this.leftAlignFormat = "| %-23s|%n";

            lines.add("+-----------------+------+%n");
            lines.add("| Inventory Report       |%n");
            lines.add("+-----------------+------+%n");
            lines.add("| Product                |%n");
            lines.add("+-----------------+------+%n");
        }
        if(this.type==ReportType.RECEIPT)
        {
            this.leftAlignFormat = "| %-15s | %-4d |%n";

            lines.add("+-----------------+------+%n");
            lines.add("| Receipt                |%n");
            lines.add("+-----------------+------+%n");
            lines.add("| Product         | $    |%n");
            lines.add("+-----------------+------+%n");
        }
    }

    public void addLine(String s){
        this.lines.add(s);
    }

    public void addLines(Collection<String> lns){
        this.lines.addAll(lns);
    }

    public ArrayList<String> getLines()
    {
        return this.lines;
    }
}




