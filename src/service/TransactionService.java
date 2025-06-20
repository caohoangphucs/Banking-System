package service;
import Utils.Logger;
import Account.*;
import dto.request.*;

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
    public void requestTransfer(TransferRequest request) {
        AccountService accountService = AccountService.getInstance();
        AbstractAccount A = accountService.findByID(request.getAccountIDA());
        AbstractAccount B = accountService.findByID(request.getAccountIDB());
        float amount = request.getAmount();

        // Checking stuff go here
        if (!accountService.canWithdraw(A, amount)) {
            logger.Log(Logger.status.ERROR, "Transaction fail!");
            return;
        }

        // execute transaction
        executeTransfer(A, B, amount);
    }
}