import java.util.Arrays;

public class SpielCharakter {

    private String name;
    private int health;
    private int ausdauer;
    private String[] inventar;
    private boolean isBlocking = false;

    // Konstruktor mit Name
    public SpielCharakter(String name) {
        this.name = name;
        this.health = 150;
        this.ausdauer = 100;
        this.inventar = new String[] { "Trank", "Trank", "Trank" };
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, health); // Keine negativen Lebenspunkte
    }

    public int getAusdauer() {
        return ausdauer;
    }

    public void setAusdauer(int ausdauer) {
        this.ausdauer = Math.max(0, ausdauer); // Keine negativen Ausdauer
    }

    public String[] getInventar() {
        return inventar;
    }

    public void setInventar(String[] inventar) {
        this.inventar = inventar;
    }

    public boolean isBlocking() {
        return isBlocking;
    }

    public void setBlocking(boolean blocking) {
        isBlocking = blocking;
    }

    public void showCharacter() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health);
        System.out.println("Ausdauer: " + ausdauer);
        System.out.println("Inventar: " + Arrays.toString(inventar));
    }
}

