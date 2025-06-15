package dto;
public class TransferRequest {
    String accountIDA;
    String accountIDB;
    float amount;
    public TransferRequest(String accountIDA, String accountIDB, String amount) {
        this.accountIDA = accountIDA;
        this.accountIDB = accountIDB;
        this.amount = Float.parseFloat(amount);
    }

    public String getAccountIDA() {
        return accountIDA;
    }

    public void setAccountIDA(String accountIDA) {
        this.accountIDA = accountIDA;
    }

    public String getAccountIDB() {
        return accountIDB;
    }

    public void setAccountIDB(String accountIDB) {
        this.accountIDB = accountIDB;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}