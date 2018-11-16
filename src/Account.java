public class Account implements Parsable<Account>
{
    String cardNo, cardType, authNo, pin;
    double balance;

    public Account(String cardNo, double balance, String cardType, String authNo, String pin){
        this.cardNo = cardNo;
        this.balance = balance;
        this.cardType = cardType;
        this.authNo = authNo;
        this.pin = pin;
    }

    public static Account parse(String[] fields){
        if(fields.length != 5) throw new IllegalArgumentException("Account.parse() requires String[] with length of 5");
        return new Account(
            fields[0],
            Double.parse(fields[1]),
            fields[2],
            fields[3],
            fields[4]
        );
    }

    public String[] toArray(){
       return [
           this.cardNo,
           this.balance.toString(),
           this.cardType,
           this.authNo,
           this.pin
       ];
    }
}
