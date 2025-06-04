package character;

import inventory.Inventory; // Allgemeine Inventar-Klasse
import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name;
    private int health;
    private int maxHealth;
    private int ausdauer;
    private int maxAusdauer;
    private boolean blocking;
    private boolean paralyzed; // Neuer Zustand für Paralyse
    private Inventory inventory; // Nutzen der allgemeinen Inventar-Klasse

    private List<Ability> abilities; // Liste von Fähigkeiten

    public Character(String name, int health, int ausdauer, Inventory inventory) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.ausdauer = ausdauer;
        this.maxAusdauer = ausdauer;
        this.blocking = false;
        this.paralyzed = false; // Standardmäßig nicht paralysiert
        this.inventory = inventory; // Weist das übergebene Inventar zu
        this.abilities = new ArrayList<>(); // Initialisierung der Fähigkeitenliste
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
    public boolean isParalyzed() { return paralyzed; }
    public void setParalyzed(boolean paralyzed) { this.paralyzed = paralyzed; }

    // Zugriff auf das Inventory-Objekt
    public Inventory getInventory() {
        return inventory;
    }

    // Fähigkeiten-Logik
    public List<Ability> getAbilities() {
        return abilities;
    }

    public void addAbility(Ability ability) {
        abilities.add(ability);
    }
}
