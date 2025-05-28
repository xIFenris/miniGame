package enemies;

import character.Character; // Importiere die neue Basisklasse Character
import java.util.Random;

// Monster erbt von Character und ist somit auch Attackable
public class Monster extends Character {
    private Random random = new Random();

    // Konstruktor für Monster
    // Ruft den Konstruktor der Basisklasse Character auf
    public Monster(String name, int health, int ausdauer, String[] inventar) { // Inventar-Parameter hinzugefügt
        // Ruft den Character-Konstruktor auf: name, initialHealth, initialAusdauer, initialInventar
        super(name, health, ausdauer, inventar);
        // Monster-spezifische Initialisierungen, falls vorhanden
    }

    // Wenn du einen Konstruktor ohne Inventar haben willst:
    public Monster(String name, int health, int ausdauer) {
        super(name, health, ausdauer, new String[]{"Leer"}); // Standard-Inventar für Monster
    }
}
