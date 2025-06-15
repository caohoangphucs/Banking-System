package dto;
public class CreateAccountRequest {
        String ownerID;
        String accountID;
        String accountType;

    public CreateAccountRequest(String ownerID, String accountType) {
        this.ownerID = ownerID;
        this.accountType = accountType;
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