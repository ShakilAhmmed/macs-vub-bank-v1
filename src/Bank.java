import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bank {

    public Client session;
    public Map<Client, List<Account>> clientAccountMap;

    public void logout() {
        if (this.session != null) {
            System.out.println("Logged out" + session.getId());
            this.session = null;
        }
    }

    public boolean transfer(String accFrom, String accTo, double amount) {
        return true;
    }

    public boolean login(int id, int pin) {
        for (Client client : clientAccountMap.keySet()) {
            if (client.getId() == id && client.pinMatch(pin)) {
                this.session = client;
                System.out.println("Logged in");
                return true;
            }
        }
        return false;
    }

    public boolean addAccount(String accountNumber) {
        if (this.session == null) {
            System.out.println("No Client is Logged In");
            return false;
        }
        Account newAccount = new Account(accountNumber, 0);
        List<Account> accounts = clientAccountMap.get(session);
        accounts.add(newAccount);
        System.out.println("New account added for" + session.getId() + " with Account Number : " + accountNumber);
        return true;
    }

    public boolean addClient(int clientId, int pin) {
        Client newClient = new Client(clientId, pin);
        if (clientAccountMap.containsKey(newClient)) {
            System.out.println("Client: " + clientId + " is already exists");
            return false;
        }
        clientAccountMap.put(newClient, new ArrayList<Account>());
        System.out.println("New client added with " + clientId);
        return true;
    }

    public void showAccounts() {
        if (this.session == null) {
            System.out.println("No Client is Logged In");
            return;
        }

        List<Account> accounts = clientAccountMap.get(session);
        for (Account account : accounts) {
            account.getOverview();
        }
    }

    public boolean removeClient(int clientId) {
        for (Client client : clientAccountMap.keySet()) {
            if (client.getId() == clientId) {
                clientAccountMap.remove(client);
                return true;
            }
        }
        return false;
    }

    public boolean removeAccount(String accountNumber) {
        if (this.session == null) {
            System.out.println("No Client is Logged In");
            return false;
        }
        List<Account> accounts = clientAccountMap.get(session);
        for (Account account : accounts) {
            if (account.accountNumber == accountNumber) {
                accounts.remove(account);
                return true;
            }
        }
        return false;
    }

}
