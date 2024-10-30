public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();
        bank.addClient(1, 1234);
        bank.addClient(2, 5678);
        bank.addClient(3, 7890);

        bank.login(1, 1234);
        bank.addAccount("BE 1213121212121");
        bank.addAccount("BE 423231314131");

        bank.transfer("BE 1213121212121", "BE 423231314131", 100);

    }
}