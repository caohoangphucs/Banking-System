import java.util.Scanner;

import Account.AccountType;
import client.Client;
import Utils.Logger;
import dto.*;
import service.AccountService;
import service.TransactionService;
import service.UserService;

public class CommandLineInterface {
    private static UserService userService;
    private static AccountService accountService;
    private static TransactionService transactionService;
    private static final Logger logger = new Logger("Main class");
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        initSystem();
        while (true) {
            String command;
            System.out.print(">>> ");
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) break;
            handleCommand(command);
        }
    }
    private static void initSystem() {
        printInitMessage();

        //Init modules go here
        initService();
    }
    private static void initService() {
        logger.Log(Logger.status.LOADING, "Preparing System...");
        userService = new UserService();
        accountService = new AccountService();
        transactionService = new TransactionService();
        logger.Log(Logger.status.SUCCESSFUL, "Banking System is ready to use");
    }
    private static void printInitMessage() {
        System.out.println("Welcome to basic banking system, this is command line interface");
    }
    public static void annouce(String message) {
        System.out.println(message);
    }
    public static void printUse(String message) {
        annouce("Use: " + message);
    }

    public static void handleCreatCommand(String args[]) {

        if (!Utils.utils.hasEnoughArgs(args, 2)) {
            printUse("Create user/account");
            return;
        }
        if (args[1].equals("user")) {
            handleCreateUser(args);
        }
        if (args[1].equals("account")) {
            handleCreatAccount(args);
        }
    }
    public static void handleCreatAccount(String args[]) {
        if (!Utils.utils.hasEnoughArgs(args, 4)) {
            printUse("creat account <userID> <accountType>");
            return;
        }
        //implement here
        CreateAccountRequest rq = new CreateAccountRequest(args[2], args[3]);
        accountService.handleCreatAccountRequest(rq);
    }
    public static void handleCreateUser(String args[]) {
        if (!Utils.utils.hasEnoughArgs(args, 5)) {
            printUse("create user <name> <age> <address>");
            return;
        }
        String name = args[2];
        int age = Integer.parseInt(args[3]);
        String address = args[4];
        Client newClient = new Client(name, age, address);
        userService.addUser(newClient);

    }
    public static void handleTransferCommand(String args[]){

    }
    public static void handleEmptyCommand() {

    }
    private static void handleCommand(String command) {
        String[] parts = command.split(" ");
        if (!Utils.utils.hasEnoughArgs(parts, 1)) {
            handleEmptyCommand();
            return;
        }
        switch (parts[0]) {
            case "create":
                handleCreatCommand(parts);
                break;
            case "transfer":
                handleTransferCommand(parts);
                break;
            default:
                printUse("creat/transfer");
        }
    }
}