package client;
import Account.AccountFactory;
import Account.AccountType;
import Utils.*;
import service.AccountService;
import service.TransactionService;

//private package
import java.sql.Array;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


public class Client {
    private String name;
    private String address;
    private int age;
    private LocalDate registerTime;
    private String ID;
    private Logger logger;
    private List<AccountType> accounts;
    private AccountService accountService = AccountService.getInstance();
    public Client(String name, int age, String address) {

        this.name = name;
        this.address = address;
        this.age = age;
        this.accounts = new ArrayList<>();
        logger = new Logger("Client " + name);
        logger.Log(Logger.status.SUCCESSFUL, "Created user " + name);
    }
    public void creatAccount(String type, String accountID) {
        this.accounts.add(AccountFactory.createAccount(type, this.name, accountID));
    }
    public String getName() {
        return this.name;
    }
    public void transfer(String sourceAccountID, String accountID, float amount) {
        TransactionService.getInstance().requestTransfer(sourceAccountID, accountID, amount);
    }
    public void deposit(String accountID, float amount) {
        AccountType targetAccount = accountService.findByID(accountID);
        targetAccount.deposit(amount);
    }
    public void withdraw(String accountID, float amount) {

        AccountType targetAccount = accountService.findByID(accountID);
        //Checking stuff
        if (!accountService.canTransfer(accountID, amount)) {
            logger.Log(Logger.status.ERROR, "Withdraw failed!");
            return;
        }
        targetAccount.withdraw(amount);
    }
}
