package client;
import Account.AccountType;
import Utils.*;
import dto.TransferRequest;
import service.AccountService;

//private package
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
    public void requestTransfer(TransferRequest rq) {
        AccountService.getInstance().transfer(rq);
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<AccountType> getAccounts() {
        return accounts;
    }

    public void deposit(String accountID, float amount) {
        AccountType targetAccount = accountService.findByID(accountID);
        targetAccount.deposit(amount);
    }
    public void withdraw(String accountID, float amount) {

        accountService.handleWithdraw(accountID, amount);
    }
}
