package service;
import Account.*;
import client.*;
import Utils.*;
import dto.*;
import dto.TransferRequest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class UserService {
    private final Map<String, Client> Clients = new HashMap<>();
    private static UserService instance;

    private UserService(){};

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    Logger logger = new Logger("User service");


    //User list

    public boolean canCreateAccount(String userID) {
        //TODO: Creat a basic condition for creating account
        return true;
    }
    public boolean canCreateUser(CreateUserRequest userInfo) {
        //TODO: Creat some basic condition for creating user
        return true;
    }
    public void transfer(TransferRequest rq) {
        AccountService.getInstance().transfer(rq);
    }
    public Client findUserByID(String IDTarget) {
        for (String ID: this.Clients.keySet()) {

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
    public Collection<Client> getAllUsers() {
        return Clients.values();
    }
    public void createUser(CreateUserRequest userInfo) {
        Client newUser = new Client(userInfo.getName(), userInfo.getAge(), userInfo.getAddress());
        newUser.setID(generateUniqueID());
        addUser(newUser);
    }
    public void handleCreateUser(CreateUserRequest request) {
        if (!canCreateUser(request)) {
            logger.Log(Logger.status.ERROR, "Cant creat user");
        }

        //Implement here
        createUser(request);
    }
    //Add user
    public void addUser(Client client) {
        Clients.put(client.getID(), client);
        logger.Log(Logger.status.SUCCESSFUL, "Created user " + client.getName()+ " With ID: " +client.getID());
    }

}