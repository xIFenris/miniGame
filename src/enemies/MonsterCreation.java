package enemies;

import character.Character;

public class MonsterCreation {

    public static Monster createGhoul() {
        // Monster-Konstruktor mit neuem Inventar aufrufen
        return new Monster("Ghoul", 80, 100, new String[]{"trank", "bombe", "bombe"});
    }

    public static Monster createZombie() {
        // Monster-Konstruktor mit neuem Inventar aufrufen
        return new Monster("Zombie", 110, 60, new String[]{"trank", "trank", "bombe"});
    }

    public static Monster createDarkMirror(Character player) {
        // Monster-Konstruktor verwendet Werte vom Spieler und übergibt ein Inventar
        return new Monster("Dunkler Spiegel", player.getHealth(), player.getAusdauer(), new String[]{"trank", "bombe", "leer"});
    }
}
