package Account;

public interface AccountType {
    String getAccountID();
    String getType();
    String getOwnerName();
    float getBalance();
    void withdraw(float amount);
    void deposit(float amount);
    boolean hasEnoughMoney(float amount);
    boolean isTouchDayLimit(float amount);
}
