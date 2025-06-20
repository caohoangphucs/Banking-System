package Account.type;
import Account.*;

public class Saving extends AbstractAccount{
    private double interestRate = 0.01;
    public Saving(String accountID, String ownerName) {
        super("saving", accountID, ownerName);
    }
    public void gainProfit() {
        this.setBalance(this.getBalance() * (1 + (float)this.getInterestRate()));
    }
    public double getInterestRate() {
        return interestRate;
    }
}
