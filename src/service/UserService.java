package service;
import Account.*;
import client.*;
import Utils.*;
import dto.db.*;
import dto.request.*;
import repository.UserRepository;

import java.sql.SQLException;
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

    public boolean canCreateAccount(CreateAccountRequest request) {
        //TODO: Creat a basic condition for creating account
        Client targetUser = findUserByID(request.getOwnerID());
        String accountType = request.getAccountType();
        if (targetUser.getAge() < 18 && accountType.equals("credit")) {
            logger.Log(Logger.status.ERROR, "User ID " + targetUser.getID() + " not enough age");
            return false;
        }
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
        return Clients.get(IDTarget);
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
    public void createUser(CreateUserRequest userInfo) throws SQLException {
        UserDto newUser = new UserDto(userInfo.getId(), userInfo.getAge(), userInfo.getName(), userInfo.getAddress(), userInfo.getEmail());
        UserRepository.getInstance().save(newUser);
    }
    public void handleCreateUser(CreateUserRequest request){
        if (!canCreateUser(request)) {
            logger.Log(Logger.status.ERROR, "Cant creat user");
        }

        //Implement here
        try {
            createUser(request);
        } catch (SQLException e) {
                logger.Log(Logger.status.ERROR, "Saving fail: " + e.toString());
        }
    }
    //Add user
    public void addUser(Client client) {
        Clients.put(client.getID(), client);
        logger.Log(Logger.status.SUCCESSFUL, "Created user " + client.getName()+ " With ID: " +client.getID());
    }

}