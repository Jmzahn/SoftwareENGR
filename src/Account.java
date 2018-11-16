public class Account implements Parsable
{
    String cardNo, cardType, authNo, pin;
    double balance;

    public static Account parse(String[] fields){
        assert (fields.length == 5) : "Invalid Account"; //ensure correct number of fields
        //TODO
    }

    public String[] toArray(){
        //TODO
        return null;
    }
}
