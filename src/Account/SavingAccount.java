package Account;
import service.*;
import Utils.*;
public class SavingAccount extends AbstractAccount{
    private double interestRate = 0.01;
    public SavingAccount(String accountID, String ownerName) {
        super("Saving", accountID, ownerName);
    }
    public void gainProfit() {
        this.setBalance(this.getBalance() * (1 + (float)this.getInterestRate()));
    }
    public double getInterestRate() {
        return interestRate;
    }
}
