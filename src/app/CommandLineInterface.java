package app;
import java.util.Scanner;

import Utils.Logger;
import dto.request.*;
import dto.db.*;
import dto.request.*;
import repository.*;
import test.TestLoader;
import service.AccountService;
import service.TransactionService;
import service.UserService;
import java.sql.*;
public class CommandLineInterface {
    private static UserService userService;
    private static AccountService accountService;
    private static TransactionService transactionService;
    private static Connection repo;
    private static final Logger logger = new Logger("Main class");
    public static void main(String args[]) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        initSystem();
        connectDatabase();
        for (String command: TestLoader.loadCommand("test/UserAndAccount.txt")) {
            handleCommand(command);
        }
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

        userService = UserService.getInstance();
        accountService = AccountService.getInstance();
        transactionService = TransactionService.getInstance();
        logger.Log(Logger.status.SUCCESSFUL, "Banking System is ready to use");
    }
    private static void connectDatabase() throws SQLException {
        logger.Log(Logger.status.LOADING, "Connecting to database....");
        repo = Connector.getConnection();
        logger.Log(Logger.status.SUCCESSFUL, repo.getClientInfo().toString());
        logger.Log(Logger.status.SUCCESSFUL, "Connected");
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
        if (!Utils.utils.hasEnoughArgs(args, 5)) {
            printUse("creat account <userID> <accountType> <email>");
            return;
        }
        //implement here
        CreateAccountRequest rq = new CreateAccountRequest(args[2], args[3], args[4]);
        accountService.handleCreatAccountRequest(rq);
    }
    public static void handleCreateUser(String args[]) {
        if (!Utils.utils.hasEnoughArgs(args, 6)) {
            printUse("create user <name> <age> <address> <email>");
            return;
        }
        String name = args[2];
        int age = Integer.parseInt(args[3]);
        String address = args[4];
        String email = args[5];
        CreateUserRequest user = new CreateUserRequest(
            name, age, address, email
        );

        userService.handleCreateUser(user);

    }
    public static void handleTransferCommand(String args[]){
        if (!Utils.utils.hasEnoughArgs(args, 4))  {
            printUse("transfer <account ID A> <account ID B> <amount>");
            return;
        }
        TransferRequest request = new TransferRequest(args[1], args[2], args[3]);
        transactionService.requestTransfer(request);

    }
    public static void handleListCommand(String args[]) {
        if (!Utils.utils.hasEnoughArgs(args, 2)) {
            printUse("list account/user");
            return;
        }

        String object = args[1];

        switch (object) {
            case "account":
                logger.displayAccounts(accountService.getAllAccount());
                break;
            case "user":
                logger.displayUsers(userService.getAllUsers());
                break;
            default:
                printUse("account | user");
        }

    }
    public static void handleInvalidCommand() {
        printUse("creat : Creat a user or account");
        printUse("transfer : Request a transaction");
        printUse("list: List of account or user");
    }
    public static void handleDeposit(String[] args) {
        if (!Utils.utils.hasEnoughArgs(args,3)) {
            printUse("deposit <account id> <amount>");
            return;
        }

        accountService.handleDeposit(args[1], Float.parseFloat(args[2]));
    }
    public static void handleWithdraw(String[] args) {
        if (!Utils.utils.hasEnoughArgs(args,3)) {
            printUse("withdraw <account id> <amount>");
            return;
        }

        accountService.handleWithdraw(args[1], Float.parseFloat(args[2]));
    }
    private static void handleCommand(String command) {
        String[] parts = command.split(" ");
        if (!Utils.utils.hasEnoughArgs(parts, 1)) {
            handleInvalidCommand();
            return;
        }
        switch (parts[0]) {
            case "create":
                handleCreatCommand(parts);
                break;
            case "transfer":
                handleTransferCommand(parts);
                break;
            case "list":
                handleListCommand(parts);
                break;
            case "deposit":
                handleDeposit(parts);
                break;
            case "withdraw":
                handleWithdraw(parts);
                break;
            default:
                handleInvalidCommand();
        }
    }
}