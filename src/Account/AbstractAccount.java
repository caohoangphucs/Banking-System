package Account;

import Utils.Logger;
import service.AccountService;

public class AbstractAccount implements AccountType {
    private String accountID;
    private String ownerName;
    private String type;
    private float balance = 0;
    private Logger logger;
    private int dayTransferLimit = 0;
    private int dayTotalTransfer;
    AccountService service = AccountService.getInstance();
    public AbstractAccount(String type, String accountID, String ownerName ){
        this.accountID = accountID;
        this.ownerName = ownerName;
        this.type = type;
        setDefaultDayTransferLimit();
        logger = new Logger(type + "Account ");
        logger.Log(Logger.status.SUCCESSFUL, "Account ID: "+this.accountID + " is created");
        service.add(this);
    }
    private int getDefaultTransferLimit() {
        return switch (getType()) {
            case "Standard" -> 1000;
            case "Saving" -> 100;
            default -> 0;
        };
    }

    public void setDayTransferLimit(int dayTransferLimit) {
        this.dayTransferLimit = dayTransferLimit;
    }

    private void setDefaultDayTransferLimit() {
        setDayTransferLimit(getDefaultTransferLimit());
    }
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
    public void addTotalDayTransfer(float amount) {
        this.dayTotalTransfer += amount;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void setService(AccountService service) {
        this.service = service;
    }
    public void setOwner(String ownerName) {
        this.ownerName = ownerName;
    }
    public int getDayTransferLimit() {return this.dayTransferLimit;}
    public String getType() {
        return this.type;
    }
    public String getOwnerName() {return this.ownerName;}
    public String getAccountID() {return this.accountID;}
    public float getBalance() {
        return this.balance;
    }
    public void withdraw(float amount) {
        this.balance -= amount;
        addTotalDayTransfer(amount);
    }
    public void deposit(float amount) {
        this.balance += amount;
    }
    public boolean hasEnoughMoney(float amount) {
        return (this.balance - amount >= 0);
    }
    public boolean isTouchDayLimit(float amount) {
        return (this.dayTotalTransfer + amount > this.dayTransferLimit);
    }

}


