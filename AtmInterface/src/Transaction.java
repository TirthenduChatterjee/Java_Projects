
import java.sql.Timestamp;

public class Transaction {
  private  String particulars;
    private Timestamp date;
   private  double credit;
    private double amt;
    private double debit;

    public String getParticulars() {
        return particulars;
    }

    public Timestamp getDate() {
        return date;
    }

    public double getCredit() {
        return credit;
    }

    public double getAmt() {
        return amt;
    }

    public double getDebit() {
        return debit;
    }

    public Transaction( Timestamp date,String particulars, double debit, double credit, double amt) {
        this.particulars = particulars;
        this.date = date;
        this.debit = debit;
        this.credit = credit;
        this.amt = amt;
    }
    
   
}
