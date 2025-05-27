package player;

public class Character {
    private String name;
    private int health;
    private int maxHealth;
    private int ausdauer;
    private int maxAusdauer;
    private boolean blocking;
    protected String[] inventar;

    public Character(String name, int health, int ausdauer, String[] inventar) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.ausdauer = ausdauer;
        this.maxAusdauer = ausdauer;
        this.blocking = false;
        this.inventar = inventar;
    }

    // Getter und Setter
    public String getName() { return name; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getMaxHealth() { return maxHealth; }
    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
    public int getAusdauer() { return ausdauer; }
    public void setAusdauer(int ausdauer) { this.ausdauer = ausdauer; }
    public int getMaxAusdauer() { return maxAusdauer; }
    public void setMaxAusdauer(int maxAusdauer) { this.maxAusdauer = maxAusdauer; }
    public boolean isBlocking() { return blocking; }
    public void setBlocking(boolean blocking) { this.blocking = blocking; }
    public String[] getInventar() { return inventar; }
    public void setInventar(String[] inventar) { this.inventar = inventar; }
}
