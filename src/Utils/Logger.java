package Utils;
import Account.*;
import Utils.*;
import java.time.LocalDate;
import java.util.Collection;

public class Logger {
    private String name;
    public enum status {
        LOADING,
        WARNING,
        ERROR,
        SUCCESSFUL,
        COMPLETE
    }
    public Logger(String From) {
        this.name = From;
        this.Log(status.SUCCESSFUL, "Logger for " + From + " is inited");
    }
    public void Log(status type, String message) {
        LocalDate now = LocalDate.now();
        String logMessage = "Logger | " + now + " | " + "[" + type + "] " + this.name + " > " +message;
        System.out.println(logMessage);
        return;
    }
    public void displayAccounts(Collection<AccountType> accounts) {
        System.out.println("------Account list-----");
        for (AccountType acc : accounts) {
            String LogMessage = "Account type : " + acc.getType() + " | Account ID: " + acc.getAccountID() + "| Account balance : " + acc.getBalance();
            System.out.println(LogMessage);
        }
    }
}