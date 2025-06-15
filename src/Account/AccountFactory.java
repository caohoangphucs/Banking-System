package Account;
public class AccountFactory {
    public static AbstractAccount createAccount(String type, String ownerName, String accountID) {
        switch (type) {
            case "Saving":
                return new SavingAccount(accountID, ownerName);
            case "Standard":
                return new StandardAccount(accountID, ownerName);
            default:
                throw new IllegalArgumentException("Invalid account type: " + type);
        }
    }

}