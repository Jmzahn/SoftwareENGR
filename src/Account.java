public class Account extends Parsable<Account>
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

    public Account(String[] fields){
        if(fields.length != 5) throw new IllegalArgumentException("Account requires String[] with length of 5");
        this.cardNo = fields[0];
        this.balance = Double.parseDouble(fields[1]);
        this.cardType = fields[2];
        this.authNo = fields[3];
        this.pin = fields[4];
    }

    public String[] toArray(){
       return new String[]{
           this.cardNo,
           Double.toString(this.balance),
           this.cardType,
           this.authNo,
           this.pin
       };
    }
}
