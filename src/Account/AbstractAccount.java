package Account;

import Utils.Logger;
import service.AccountService;

public class AbstractAccount implements AccountType {
    private String accountID;
    private String ownerName;
    private String type;
    private float balance = 0;
    private Logger logger;
    AccountService service = AccountService.getInstance();
    public AbstractAccount(String type, String accountID, String ownerName ){
        this.accountID = accountID;
        this.ownerName = ownerName;
        this.type = type;
        logger = new Logger(type + "Account ");
        logger.Log(Logger.status.SUCCESSFUL, "Account ID: "+this.accountID + " is created");
        service.add(this);
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
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
    }
    public void deposit(float amount) {
        this.balance += amount;
    }
    public boolean hasEnoughMoney(float amount) {
        return (balance - amount >= 0);
    }
}


