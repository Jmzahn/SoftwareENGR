public class Item implements Parsable<Item>
{
    String name, description;
    double price, discount;
    int quantity, invMinimum;
    boolean isBooze;

    public static Item parse(String[] fields){
        assert (fields.length == 7) : "Invalid Item"; //ensure correct number of fields
        //TODO
    }

    public String[] toArray(){
        //TODO
        return null;
    }
}
