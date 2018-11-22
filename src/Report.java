import java.util.ArrayList;
import java.util.Collection;

enum ReportType{
    DAILY,INVENTORY,RECEIPT
}

public class Report {
    private ArrayList<String> lines;
    private ReportType type;
    private String leftAlignFormat;

    Report(ReportType t){
        this.type=t;
        this.lines=new ArrayList<>();
        this.leftAlignFormat="";
    }

    public ReportType getType(){
        return this.type;
    }

    String getFormat(){
        return this.leftAlignFormat;
    }

    void makeHeader(){
        if(this.type==ReportType.DAILY)
        {
            this.leftAlignFormat = "| %-15s | %-4d |";

            this.lines.add("+----------------------------+");
            this.lines.add("| Daily Report           |");
            this.lines.add("+--------------------+-------+");
            this.lines.add("| Product         | #    |");
            this.lines.add("+--------------------+-------+");
        }
        if(this.type==ReportType.INVENTORY)
        {
            this.leftAlignFormat = "| %-15s | %-4d |";

            this.lines.add("+----------------------------+");
            this.lines.add("| Inventory Report       |");
            this.lines.add("+--------------------+-------+");
            this.lines.add("| Product         | #    |");
            this.lines.add("+--------------------+-------+");
        }
        if(this.type==ReportType.RECEIPT)
        {
            this.leftAlignFormat = "| %-13s | %-6f |";

            this.lines.add("+----------------------------+");
            this.lines.add("| Receipt                |");
            this.lines.add("+------------------+---------+");
            this.lines.add("| Product       | $      |");
            this.lines.add("+------------------+---------+");
        }
    }

    void makeFooter(){
        if(this.type==ReportType.DAILY)
        {
            this.lines.add("+----------------------------+");
            this.lines.add("| End of Daily Report    |");
        }
        if(this.type==ReportType.INVENTORY)
        {
            this.lines.add("| End of Inventory Report|");
        }
        if(this.type==ReportType.RECEIPT)
        {
            this.lines.add("| X:____________________ |");
            this.lines.add("+----------------------------+");
            this.lines.add("| End of Receipt         |");
        }
        this.lines.add("+----------------------------+");
    }

    void addLine(String s){
        this.lines.add(s);
    }

    void addLines(Collection<String> lns){
        this.lines.addAll(lns);
    }

    ArrayList<String> getLines()
    {
        return this.lines;
    }
}




