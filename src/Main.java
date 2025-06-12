import client.Client;
import Utils.Logger;
import service.AccountService;

public class Main{
    public static void main(String args[]) {
        Logger logger = new Logger("Banking system");
        AccountService accountService = AccountService.getInstance();
        Client Phucdeptrai = new Client("Phucdeptrai", 17, "Dinh quan town");
        Client Ngan = new Client("Ngan", 17, "Mytho");


        Phucdeptrai.creatAccount("Saving", "123455");
        Ngan.creatAccount("Standard", "13223");

        Phucdeptrai.deposit("123455", 50);
        Ngan.deposit("13223", 30);


        Ngan.transfer("13223", "123455", 10);

        Ngan.withdraw("13223", 30);
        logger.displayAccounts(accountService.getAllAccount());
    }
}