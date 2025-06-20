package app;
import client.Client;
import Utils.Logger;
import service.AccountService;

public class Main{
    public static void main(String args[]) {
        Logger logger = new Logger("Banking system");
        AccountService accountService = AccountService.getInstance();
        Client Phucdeptrai = new Client("Phucdeptrai", 17, "Dinh quan town");
        Client Ngan = new Client("Ngan", 17, "Mytho");
        Client Tung = new Client("Tung", 17, "Cali");
        Client ThinhNgu = new Client("Thinh Ngu", 11, "Cali");


        ThinhNgu.deposit("11000", 12);
        Phucdeptrai.deposit("123455", 5000);
        Ngan.deposit("13223", 30);



        Ngan.withdraw("13223", 30);
        logger.displayAccounts(accountService.getAllAccount());
    }
}