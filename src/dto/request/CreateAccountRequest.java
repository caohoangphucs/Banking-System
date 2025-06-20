package dto.request;
public class CreateAccountRequest {
        String email;
        String ownerID;
        String accountID;
        String accountType;

    public CreateAccountRequest(String ownerID, String accountType, String email) {
        this.ownerID = ownerID;
        this.accountType = accountType;
        this.email = email;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}