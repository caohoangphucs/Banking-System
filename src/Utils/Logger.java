package Utils;
import Account.*;
import Utils.*;
import client.Client;

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
    public void displayAccounts(Collection<AbstractAccount> accounts) {
        System.out.println("------Account list-----");
        for (AccountType acc : accounts) {
            String LogMessage = "Account type : " + acc.getType() + " | Account ID: " + acc.getAccountID()+ "| Account owner: " + acc.getOwnerName() + "| Account balance : " + acc.getBalance();
            System.out.println(LogMessage);
        }
    }
    public void displayUsers(Collection<Client> users) {
        System.out.println("------User list-----");
        for (Client user : users) {
            String LogMessage = "User ID: " + user.getID() + "| User name: " + user.getName()
                                + "| User info (age/address): " +user.getAge() + "|" +user.getAddress();
            System.out.println(LogMessage);
        }
    }
}