package dto.db;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class TransactionDto {
    String id;
    LocalDate time;
    double amount;
    String sourceID;
    String destinationID;
    String message;

    public TransactionDto( LocalDate time, double amount, String sourceID, String destinationID, String message) {
        this.time = time;
        this.amount = amount;
        this.sourceID = sourceID;
        this.destinationID = destinationID;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(String destinationID) {
        this.destinationID = destinationID;
    }

    public String getToAccount() {
        return sourceID;
    }

    public void setToAccount(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}