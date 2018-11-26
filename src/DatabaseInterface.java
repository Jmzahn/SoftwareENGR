import java.util.ArrayList;
import java.util.List;

public class DatabaseInterface {
    private static Database database = new Database();

    public static Database getDatabase(){
        return database;
    }

    public static void setDatabase(Database d){
        database=d;
    }


    static Report prepareDailyReport(){//called by BusinessLogic
        Report d = new Report(ReportType.DAILY);//new report made with type daily
        d.makeHeader();//header added

        List<Transaction> transactions=database.getTransactionLogList();
        List<String> lines=new ArrayList<>();
        String line;//extracting necessary info from database, and instantiating helper variables

        List<String> products=new ArrayList<>();//holder
        List<Integer> amounts=new ArrayList<>();//holder, should always be same length as products

        String format=d.getFormat();//how this report is formatted
        int i;//helper
        double total=0.0;
        for (Transaction transaction:transactions)
        {
            List<Item> items=transaction.getCart();
            for(Item item:items)
            {
                if(products.isEmpty())//if empty this must be the first item we've seen
                {
                    products.add(item.name);
                    amounts.add(1);
                }
                i=products.indexOf(item.name);//-1 if no item in products, otherwise its there
                if(i!=-1)
                {
                    amounts.set(i,amounts.get(i)+1);
                }
                else
                {
                    products.add(item.name);
                    amounts.add(1);
                }
            }
            total+=transaction.getTotal();//adds up totals from each transaction
        }
        for(int x=0;x<products.size();x++) {//this dumps all the products and amounts onto lines
            line=String.format(format,products.get(x),amounts.get(x));
            lines.add(line);
        }
        line="+--------------------+-------+";
        lines.add(line);
        format="| Total: %,011.2f |";
        line=String.format(format,total);//adds formatted total onto lines
        lines.add(line);
        d.addLines(lines);//adds lines to the report
        d.makeFooter();//finishes report
        return d;//returns report
    }

    static Report prepareInventoryReport(){//called by BusinessLogic
        Report i = new Report(ReportType.INVENTORY);
        i.makeHeader();

        List<Item> inventory=database.getInventoryList();
        List<String> lines=new ArrayList<>();
        String line;//extracting necessary info from database, and instantiating helper variables

        String format=i.getFormat();//how this report is formatted

        List<String> products=new ArrayList<>();//holder
        List<Integer> amounts=new ArrayList<>();//holder, should always be same length as products

        for(Item item:inventory)
        {
            int dif=item.quantity-item.invMinimum;//get the difference between quantity and minimum
            if(dif<0)//if below min add to products and add dif to amounts
            {
                products.add(item.name);
                amounts.add(dif);
            }//else skip it
        }

        for(int x=0;x<products.size();x++) {//this dumps all the products and amounts onto lines
            line=String.format(format,products.get(x),amounts.get(x));
            lines.add(line);
        }
        line="+--------------------+-------+";
        lines.add(line);
        i.addLines(lines);//adds line to report
        i.makeFooter();//finishes report
        return i;//returns report
    }


}
