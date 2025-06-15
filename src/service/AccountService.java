package service;
import Account.*;
import Utils.*;
import dto.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class AccountService {
    private final Map<String, AbstractAccount> accountList = new HashMap<>();
    private final Logger logger = new Logger("Account Service");
    private static AccountService instance;
    private AccountService() {};
    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    private boolean isValidOwner(String ownerID) {
        if (UserService.getInstance().findUserByID(ownerID) == null) return false;

        return true;
    }
    private void createAccount(CreateAccountRequest request) {
        request.setAccountID(getUniqueID());
        if (!isValidOwner(request.getOwnerID())) {
            logger.Log(Logger.status.ERROR, "User not valid");
            return;
        }
        String ownerName = UserService.getInstance().findUserByID(request.getOwnerID()).getName();
        AbstractAccount newAccount = AccountFactory.createAccount(request.getAccountType(), request.getOwnerID(), request.getAccountID());
        newAccount.setOwner(ownerName);
        add(newAccount);
    }
    public void handleCreatAccountRequest(CreateAccountRequest request) {
        // Checking
        if (!UserService.getInstance().canCreateAccount(request.getOwnerID())) {
            logger.Log(Logger.status.ERROR, "This user can't creat account");
            return;
        }
        //Implement
        createAccount(request);
    }
    public void add(AbstractAccount account) {
        accountList.put(account.getAccountID(), account);
    }
    public AbstractAccount findByID(String accountID) {
        return accountList.get(accountID);
    }
    public void transfer(TransferRequest request) {
        TransactionService.getInstance().requestTransfer(request);
    }
    public String getUniqueID() {
        String rootID = "1010";
        while (findByID(rootID) != null) {
            rootID = String.valueOf(Utils.math.getRandomInt(1000, 9999));
        }
        return rootID;
    }
    public boolean canWithdraw(AbstractAccount account, float amount) {
        if (!account.hasEnoughMoney(amount)) {
            logger.Log(Logger.status.ERROR, "Account " + account.getAccountID() + " is not enough money");
            return false;
        }

        if (account.isTouchDayLimit(amount)) {
            logger.Log(Logger.status.ERROR, "Account " + account.getAccountID() + " is touching amount day limit ");
            return false;
        }

        return true;
    }
    public boolean canDeposit(AbstractAccount account, float amount) {

        //TODO: is account locked, is account have day limit, ...
        return true;
    }
    public void handleWithdraw(String accountID, float amount) {
        AbstractAccount acc = findByID(accountID);
        //Checking stuff
        if (acc == null) {
            logger.Log(Logger.status.ERROR, "Not found any account have ID " +accountID);
            return;
        }
        if (!canWithdraw(acc, amount)) {
            logger.Log(Logger.status.ERROR, "Withdraw fail!");
            return;
        }

        //Withdraw
        acc.withdraw(amount);
    }

    public void handleDeposit(String accountID, float amount) {
        AbstractAccount acc = findByID(accountID);
        //Checking stuff here
        if (acc == null) {
            logger.Log(Logger.status.ERROR, "Not found any account have ID " +accountID);
            return;
        }
        if (!canDeposit(acc, amount)) {
            logger.Log(Logger.status.ERROR, "Account cant deposit");
        }

        //deposit
        acc.deposit(amount);
    }
    public Collection<AbstractAccount> getAllAccount() {
        return accountList.values();
    }
}