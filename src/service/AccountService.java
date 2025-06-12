package service;
import Account.*;
import client.*;
import Utils.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class AccountService {
    private static final AccountService instance = new AccountService();
    Logger logger = new Logger("Account service");
    private AccountService() {
    }

    public static AccountService getInstance() {
        return instance;
    }
    public void creatAccount(String type, String ownerName, String accountID) {
        AccountType newAccount = AccountFactory.createAccount(type, ownerName, accountID );

    }
    final Map<String, AccountType> accountList = new HashMap<>();
    public void add(AccountType account) {
        accountList.put(account.getAccountID(), account);
    }
    public AccountType findByID(String accountID) {
        return accountList.get(accountID);
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