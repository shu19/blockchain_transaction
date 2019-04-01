import java.util.Date;

public class Transaction {


    public static int id;
    public int transactionId;
    public String sender;
    public String receiver;
    public double amount;
    public long transactionTime;
    public String description;

    static {
        id=100;
    }

    public Transaction() {

    }

    public Transaction(String sender, String receiver, double amount, String description) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.description = description;
        this.transactionTime = new Date().getTime();
        this.transactionId=id++;

    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                ", transactionTime=" + transactionTime +
                ", description='" + description + '\'' +
                '}';
    }


}
