package Account;
import Account.type.*;
public class AccountFactory {
    public static AbstractAccount createAccount(String type, String ownerName, String accountID) {
        switch (type) {
            case "saving":
                return new Saving(accountID, ownerName);
            case "standard":
                return new Standard(accountID, ownerName);
            case "credit":
                return new Credit(accountID, ownerName);
            default:
                throw new IllegalArgumentException("Invalid account type: " + type);
        }
    }

}