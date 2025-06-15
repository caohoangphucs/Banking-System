package client;
import Account.AccountFactory;
import Account.AccountType;
import Utils.*;
import service.AccountService;
import service.TransactionService;
import service.UserService;

//private package
import java.sql.Array;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


public class Client {
    private String ID;
    private String name;
    private String address;
    private int age;
    private LocalDate registerTime;
    private Logger logger;
    private List<AccountType> accounts;
    private AccountService accountService = AccountService.getInstance();
    public Client(String name, int age, String address) {
        this.ID = UserService.getInstance().generateUniqueID();
        this.name = name;
        this.address = address;
        this.age = age;
        this.accounts = new ArrayList<>();
        logger = new Logger("Client " + name);
    }
    public String getID() {
        return this.ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return this.name;
    }
    public void requestTransfer(String sourceAccountID, String accountID, float amount) {
        AccountService.getInstance().transfer(sourceAccountID, accountID, amount);
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
