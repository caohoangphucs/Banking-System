package Account;

import Utils.Logger;
import service.AccountService;

public class StandardAccount implements AccountType {
        private String accountID;
        private String ownerName;
        private String type = "Standard";
        private float balance = 0;
        private Logger logger = new Logger("Standard account");
        AccountService service = AccountService.getInstance();
        public StandardAccount(String accountID, String ownerName ){
            this.accountID = accountID;
            this.ownerName = ownerName;
            logger.Log(Logger.status.SUCCESSFUL, "Account ID: "+this.accountID + " is created");
            service.add(this);
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


