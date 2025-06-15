package service;
import Account.*;
import client.*;
import Utils.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class UserService {
    private static UserService instance;

    public UserService(){};

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    Logger logger = new Logger("User service");


    //User list
    private final Map<String, Client> Clients = new HashMap<>();
    public boolean canCreateAccount(String userID) {
        //TODO: Creat a basic condition for creating account
        return true;
    }
    public void transfer(String accountIDA, String accountIDB, float amount) {
        AccountService.getInstance().transfer(accountIDA, accountIDB, amount);
    }
    public Client findUserByID(String IDTarget) {
        for (String ID: Clients.keySet()) {
            if (ID.equals(IDTarget)) return Clients.get(ID);
        }
        return null;
    }
    public String generateUniqueID() {
        String ID = "1111";
        while (findUserByID(ID) != null) {
            ID = String.valueOf(math.getRandomInt(1000, 9999));
        }
        return ID;
    }
    //Add user
    public void addUser(Client client) {
        Clients.put(client.getID(), client);
        logger.Log(Logger.status.SUCCESSFUL, "Created user " + client.getName()+ " With ID: " +client.getID());
    }

}