package service;
import Utils.Logger;
import client.*;
import Account.*;
public class TransactionService {
    private static TransactionService instance;
    private final Logger logger = new Logger("Transaction service");
    public TransactionService(){};
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
        if (!accountService.canTransfer(accountIDA, amount)) {
            logger.Log(Logger.status.ERROR, "Transaction fail!");
            return;
        }

        // execute transaction
        executeTransfer(A, B, amount);
    }
}