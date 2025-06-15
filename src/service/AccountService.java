package service;
import Account.*;
import client.*;
import Utils.*;
import dto.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class AccountService {
    private final Map<String, AccountType> accountList = new HashMap<>();
    private final Logger logger = new Logger("Account Service");
    private static AccountService instance;
    public AccountService() {};
    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    private void createAccount(CreateAccountRequest request) {
        request.setAccountID(getUniqueID());
        AccountType newAccount = AccountFactory.createAccount(request.getAccountType(), request.getOwnerID(), request.getAccountID());
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
    public void add(AccountType account) {
        accountList.put(account.getAccountID(), account);
    }
    public AccountType findByID(String accountID) {
        return accountList.get(accountID);
    }
    public void transfer(String accountIDA, String accountIDB, float amount) {
        TransactionService.getInstance().requestTransfer(accountIDA, accountIDB, amount);
    }
    public String getUniqueID() {
        String rootID = "1010";
        while (findByID(rootID) != null) {
            rootID = String.valueOf(Utils.math.getRandomInt(1000, 9999));
        }
        return rootID;
    }
    public boolean canTransfer(String accountID, float amount) {
        AccountType targetAccount = findByID(accountID);
        if (targetAccount == null) {
            logger.Log(Logger.status.ERROR, "Not found any account have ID " +accountID);
            return false;
        }

        if (!targetAccount.hasEnoughMoney(amount)) {
            logger.Log(Logger.status.ERROR, "Account " + accountID + " is not enough money");
            return false;
        }

        if (targetAccount.isTouchDayLimit(amount)) {
            logger.Log(Logger.status.ERROR, "Account " + accountID + " is touching amount day limit ");
            return false;
        }

        return true;
    }
    public Collection<AccountType> getAllAccount() {
        return accountList.values();
    }
}