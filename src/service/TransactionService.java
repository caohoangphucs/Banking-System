package service;
import Utils.Logger;
import client.*;
import Account.*;
public class TransactionService {
    private static TransactionService instance;
    private static Logger logger = new Logger("TransactionService");
    private TransactionService() {
        logger.Log(Logger.status.SUCCESSFUL, "transaction service is inited");

    }
    public static TransactionService getInstance() {
        if (instance == null) {
            instance =  new TransactionService();
        }
        return instance;
    }
    private void executeTransfer(AccountType A, AccountType B, float amount) {
        A.withdraw(amount);
        B.deposit(amount);
        logger.Log(Logger.status.COMPLETE, "User " + A.getOwnerName() + " transfer " + amount + " to user " + B.getOwnerName());

    }
    public void requestTransfer(String accountIDA, String accountIDB, float amount) {
        AccountService accountService = AccountService.getInstance();
        AccountType A = accountService.findByID(accountIDA);
        AccountType B = accountService.findByID(accountIDB);


        // Checking stuff go here
        if (!A.hasEnoughMoney(amount)) {
            logger.Log(Logger.status.ERROR, "Account " + A.getOwnerName() + "is not enough money");
            return;
        }

        // execute transaction
        executeTransfer(A, B, amount);
    }
}