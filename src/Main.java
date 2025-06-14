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
        Phucdeptrai.creatAccount("Saving", "123455");
        Ngan.creatAccount("Standard", "13223");
        Tung.creatAccount("Saving", "1111");
        ThinhNgu.creatAccount("Standard", "11000");

        ThinhNgu.deposit("11000", 12);
        Phucdeptrai.deposit("123455", 5000);
        Ngan.deposit("13223", 30);
        Phucdeptrai.transfer("123455", "13223", 50);
        ThinhNgu.transfer("11000", "123455", 11);

        Ngan.transfer("13223", "123455", 10);

        Ngan.withdraw("13223", 30);
        logger.displayAccounts(accountService.getAllAccount());
    }
}