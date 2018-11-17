import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Transaction extends Parsable<Transaction>
{
    double total;
    List<Item> cart;

    public Transaction(double total, List<Item> cart){
        this.total = total;
        this.cart = cart;
    }

    public Transaction(String[] fields){
        if(fields.length % 5 != 1) throw new IllegalArgumentException("Transaction requires String[] with length of 1 + 7n");
        List<Item> list = new ArrayList<>();
        for(int i = 1; i < fields.length; i += 7){
            list.add(new Item(Arrays.copyOfRange(fields, i, i+8)));
        }

        this.total = Double.parseDouble(fields[0]);
        this.cart = list;
    }

    public String[] toArray(){
        String[] arr = new String[this.cart.size() * 7 + 1];
        arr[0] = Double.toString(this.total);
        
        List<String> list = new ArrayList<>();
        for(Item item : this.cart){
            String[] itemArr = item.toArray();
            for(int i = 0; i < itemArr.length; i++){
                list.add(itemArr[i]);
            }
        }

        for(int i = 1; i < arr.length; i += 7){
            arr[i] = this.cart.get(i - 1).toString();
        }
        return arr;
    }
}
