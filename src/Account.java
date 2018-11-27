public class Account extends Parsable<Account>
{
    private String cardNo, cardType, authNo, pin;

    public String getCardNo() {
        return cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public String getAuthNo() {
        return authNo;
    }

    public String getPin() {
        return pin;
    }

    public Account(String cardNo, String cardType, String authNo, String pin){
        this.cardNo = cardNo;
        this.cardType = cardType;
        this.authNo = authNo;
        this.pin = pin;
    }

    public Account(String[] fields){
        if(fields.length != 4) throw new IllegalArgumentException("Account requires String[] with length of 4");
        this.cardNo = fields[0];
        this.cardType = fields[1];
        this.authNo = fields[2];
        this.pin = fields[3];
    }

    public String[] toArray(){
       return new String[]{
           this.cardNo,
           this.cardType,
           this.authNo,
           this.pin
       };
    }
}
