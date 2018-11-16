import java.util.ArrayList;
import java.util.List;

public class Transaction implements Parsable
{
    double total;
    List<Item> cart;

    public static Transaction parse(String[] fields){
        assert (fields.length == 2) : "Invalid Transaction"; //ensure correct number of fields
        //TODO
    }

    public String[] toArray(){
        //TODO
        return null;
    }
}
