import java.util.ArrayList;
import java.util.List;

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

        List<Transaction> transactions=database.getTransactionLogList();
        List<String> lines=new ArrayList<>();
        String line;

        List<String> products=new ArrayList<>();
        List<Integer> amounts=new ArrayList<>();

        String format=d.getFormat();
        int i;
        double total=0.0;
        for (Transaction transaction:transactions)
        {
            List<Item> items=transaction.getCart();
            for(Item item:items)
            {
                if(products.isEmpty())
                {
                    products.add(item.name);
                    amounts.add(1);
                }
                i=products.indexOf(item.name);
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
            total+=transaction.getTotal();
        }
        for(int x=0;x<products.size();x++) {
            line=String.format(format,products.get(x),amounts.get(x));
            lines.add(line);
        }
        line="├──────────────────┴─────────┤";
        lines.add(line);
        format="│ Total: %,011.2f │";
        line=String.format(format,total);
        lines.add(line);
        d.addLines(lines);
        d.makeFooter();
        return d;
    }

    static Report prepareInventoryReport(){//called by BusinessLogic
        Report i = new Report(ReportType.INVENTORY);
        i.makeHeader();
        return i;
    }


}
