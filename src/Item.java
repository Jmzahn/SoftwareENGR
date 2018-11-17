public class Item extends Parsable<Item>
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

    public Item(String[] fields){
        if(fields.length != 7) throw new IllegalArgumentException("Item requires String[] with length of 7");
        this.name = fields[0];
        this.description = fields[1];
        this.price = Double.parseDouble(fields[2]);
        this.discount = Double.parseDouble(fields[3]);
        this.quantity = Integer.parseInt(fields[4]);
        this.isBooze = Boolean.parseBoolean(fields[5]);
        this.invMinimum = Integer.parseInt(fields[6]);
    }

    public String[] toArray(){
        return new String[]{
            this.name,
            this.description,
            Double.toString(this.price),
            Double.toString(this.discount),
            Integer.toString(this.quantity),
            Boolean.toString(this.isBooze),
            Integer.toString(this.invMinimum)
        };
    }
}
