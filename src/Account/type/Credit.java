package Account.type;
import Account.*;

public class Credit extends AbstractAccount {

    public Credit(String accountID, String ownerName) {
        super("credit", accountID, ownerName);
    }
}