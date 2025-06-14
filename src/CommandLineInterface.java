import java.util.Scanner;

import Account.AccountType;
import client.Client;
import Utils.Logger;
import service.AccountService;
import service.TransactionService;
import service.UserService;

public class CommandLineInterface {
    private static final Logger logger = new Logger("Main class");
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to basic banking system, this is command line interface");
        logger.Log(Logger.status.WARNING, "Preparing System...");
        initSystem();
        logger.Log(Logger.status.SUCCESSFUL, "Banking System is ready to use");
        String command;
        while (true) {
            System.out.print(">>> ");
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) break;
            handleCommand(command);
        }
    }
    public static void initSystem() {
        new UserService();
        new AccountService();
        new TransactionService();
    }
    public static void annouce(String message) {
        System.out.println(message);
    }
    public static void printUse(String message) {
        annouce("Use: " + message);
    }
    public static void handleCreateUser(String args[]) {
        if (args.length < 5) {
            printUse("create user <name> <age> <address>");
            return;
        }
        String name = args[2];
        int age = Integer.parseInt(args[3]);
        String address = args[4];
        Client newClient = new Client(name, age, address);
        UserService.getInstance().addUser(newClient);

    }
    public static void handleCreatCommand(String args[]) {

        if (args.length < 2) {
            printUse("Create user/account");
        }
        if (args[1].equals("user")) {
            handleCreateUser(args);
        }
    }
    public static void handleTransferCommand(String args[]){

    }
    public static void handleEmptyCommand() {

    }
    private static void handleCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length <1) {
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