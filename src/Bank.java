import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    public Client session;
    public Map<Client, List<Account>> clientAccountMap = new HashMap<>();

    public void logout() {
        if (this.session != null) {
            System.out.println("-----------------------------------");
            System.out.println("Logged out" + session.getId());
            System.out.println("-----------------------------------");
            this.session = null;
        }
    }

    public boolean transfer(String accFrom, String accTo, double amount) {
        if (this.session == null) {
            System.out.println("-----------------------------------");
            System.out.println("No Client is Logged In");
            System.out.println("-----------------------------------");
            return false;
        }
        List<Account> accounts = this.clientAccountMap.get(this.session);
        Account fromAccount = null;
        Account toAccount = null;
        for (Account account : accounts) {
            if (account.accountNumber == accFrom) {
                fromAccount = account;
            }
            if (account.accountNumber == accTo) {
                toAccount = account;
            }
        }
        if (fromAccount == null || toAccount == null) {
            return false;
        }
        if (fromAccount.withDraw(amount)) {
            toAccount.deposit(amount);
            System.out.println("-----------------------------------");
            System.out.println("Transaction successful from: " + fromAccount.accountNumber + " to " + toAccount.accountNumber);
            System.out.println("-----------------------------------");
            return true;
        }

        return false;
    }

    public boolean login(int id, int pin) {
        if (this.clientAccountMap == null) {
            System.out.println("-----------------------------------");
            System.out.println("No Client is Available");
            System.out.println("-----------------------------------");
            return false;
        }
        for (Client client : clientAccountMap.keySet()) {
            if (client.getId() == id && client.pinMatch(pin)) {
                this.session = client;
                System.out.println("-----------------------------------");
                System.out.println("Logged in as: " + this.session.getId());
                System.out.println("-----------------------------------");
                return true;
            }
        }
        return false;
    }

    public boolean addAccount(String accountNumber) {
        if (this.session == null) {
            System.out.println("-----------------------------------");
            System.out.println("No Client is Logged In");
            System.out.println("-----------------------------------");
            return false;
        }
        Account newAccount = new Account(accountNumber, 0);
        List<Account> accounts = clientAccountMap.get(session);
        accounts.add(newAccount);
        System.out.println("-----------------------------------");
        System.out.println("New account added for: " + session.getId() + " with Account Number : " + accountNumber);
        System.out.println("-----------------------------------");
        return true;
    }

    public boolean addClient(int clientId, int pin) {
        Client newClient = new Client(clientId, pin);
        if (this.clientAccountMap != null && this.clientAccountMap.containsKey(newClient)) {
            System.out.println("-----------------------------------");
            System.out.println("Client: " + clientId + " is already exists");
            System.out.println("-----------------------------------");
            return false;
        }
        this.clientAccountMap.put(newClient, new ArrayList<Account>());
        System.out.println("-----------------------------------");
        System.out.println("New client added with " + clientId);
        System.out.println("-----------------------------------");
        return true;
    }

    public void showAccounts() {
        if (this.session == null) {
            System.out.println("-----------------------------------");
            System.out.println("No Client is Logged In");
            System.out.println("-----------------------------------");
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
            System.out.println("-----------------------------------");
            System.out.println("No Client is Logged In");
            System.out.println("-----------------------------------");
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
