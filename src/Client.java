public class Client {

    private int id;
    private int pin;

    public Client(int id, int pin) {
        this.id = id;
        this.pin = pin;
    }

    public int getId() {
        return this.id;
    }

    public boolean pinMatch(int pin) {
        return this.pin == pin;
    }

}
