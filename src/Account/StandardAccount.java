package Account;

import Utils.Logger;
import service.AccountService;

public class StandardAccount extends AbstractAccount {

        public StandardAccount(String accountID, String ownerName ){
            super("Standard", accountID, ownerName);
        }
    }


