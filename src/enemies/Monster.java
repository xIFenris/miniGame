package enemies;

import character.Character; // Erbt von Character und nutzt die neue Basisklasse
import inventory.MonsterInventory; // Neues Inventar für Monster

public class Monster extends Character {
    private MonsterInventory inventory; // Neues Monster-Inventory

    // Konstruktor mit Inventar
    public Monster(String name, int health, int ausdauer, String[] initialItems) {
        super(name, health, ausdauer, new MonsterInventory(initialItems));// Ruft den Character-Konstruktor auf
        this.inventory = new MonsterInventory(initialItems); // Initialisiert das Monsterinventar
    }

    // Konstruktor ohne vollständiges Inventar (Standard-Leer-Inventar)
    public Monster(String name, int health, int ausdauer) {
        super(name, health, ausdauer, new MonsterInventory(new String[]{"Leer"}));

        this.inventory = new MonsterInventory(new String[]{"Leer"}); // Standard-Inventar
    }

    // Zugriff auf das Inventar
    public MonsterInventory getInventory() {
        return inventory;
    }
}
