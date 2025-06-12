package service;
import Account.*;
import client.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class AccountService {
    private static final AccountService instance = new AccountService();

    private AccountService() {}

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
    public Collection<AccountType> getAllAccount() {
        return accountList.values();
    }
}