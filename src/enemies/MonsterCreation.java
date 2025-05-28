package enemies;

import character.Character; // Importiere die neue Basisklasse Character


public class MonsterCreation {


    public static Monster createGhoul() {
        // Monster-Konstruktor mit Inventar aufrufen
        return new Monster("Ghoul", 80, 100, new String[]{"trank", "bombe", "bombe"});
    }

    public static Monster createZombie() {
        // Monster-Konstruktor mit Inventar aufrufen
        return new Monster("Zombie", 110, 60, new String[]{"trank", "trank", "bombe"});
    }

    // Erwartet jetzt einen generischen Character als Spieler
    public static Monster createDarkMirror(Character player) {
        // Monster-Konstruktor mit Inventar aufrufen, Gesundheit und Ausdauer vom Spieler übernehmen
        return new Monster("Dunkler Spiegel", player.getHealth(), player.getAusdauer(), new String[]{"trank", "bombe", "leer"});
    }
}