public class Item implements Parsable<Item>
{
    String name, description;
    double price, discount;
    int quantity, invMinimum;
    boolean isBooze;

    public Item(String name, String description, double price, double discount, int quantity, boolean isBooze, int invMinimum){
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.isBooze = isBooze;
        this.invMinimum = invMinimum;
    }

    public static Item parse(String[] fields){
        if(fields.length != 7) throw new IllegalArgumentException("Item.parse() requires String[] with length of 7");
        return new Item(
            fields[0],
            fields[1],
            Double.parse(fields[2]),
            Double.parse(fields[3]),
            Integer.parse(fields[4]),
            Boolean.parse(fields[5]),
            Integer.parse(fields[6])
        );
    }

    public String[] toArray(){
        return [
            this.name, 
            this.description, 
            this.price.toString(), 
            this.discount.toString(), 
            this.quantity.toString(), 
            this.isBooze.toString(),
            this.invMinimum.toString()
        ];
    }
}
