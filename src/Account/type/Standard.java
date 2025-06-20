package Account.type;

import Utils.Logger;
import service.AccountService;
import Account.*;
public class Standard extends AbstractAccount {

        public Standard(String accountID, String ownerName ){
            super("standard", accountID, ownerName);
        }
    }


