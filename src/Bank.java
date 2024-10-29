import java.util.List;
import java.util.Map;

public class Bank {

    public Client session;
    public Map<Client, List<Account>> clientAccountMap;

    public void logout() {

    }

    public boolean transfer(String accFrom, String accTo, double amount) {
        return true;
    }

    public boolean login() {
        return true;
    }

    public boolean addAccount(String accountNumber) {
        return true;
    }

    public boolean addClient(int clientId) {
        return true;
    }

    public void showAccounts() {

    }

    public boolean removeClient(int clientId) {
        return true;
    }

    public boolean removeAccount(String accountNumber) {
        return true;
    }

}
