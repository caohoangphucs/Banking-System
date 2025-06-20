package  dto.db;
public class AccountDto {
    private String accountID;
    private String ownerName;
    private String type;
    private float balance = 0;
    private int dayTransferLimit = 0;
    private int dayTotalTransfer;
    private boolean isLock = false;

    public AccountDto( String ownerName, String type, float balance, int dayTransferLimit, int dayTotalTransfer, boolean isLock) {
        this.ownerName = ownerName;
        this.type = type;
        this.balance = balance;
        this.dayTransferLimit = dayTransferLimit;
        this.dayTotalTransfer = dayTotalTransfer;
        this.isLock = isLock;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getDayTransferLimit() {
        return dayTransferLimit;
    }

    public void setDayTransferLimit(int dayTransferLimit) {
        this.dayTransferLimit = dayTransferLimit;
    }

    public int getDayTotalTransfer() {
        return dayTotalTransfer;
    }

    public void setDayTotalTransfer(int dayTotalTransfer) {
        this.dayTotalTransfer = dayTotalTransfer;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }
}