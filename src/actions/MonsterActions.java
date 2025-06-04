package actions;

import enemies.Monster;
import character.Character;

import java.util.Random;

public class MonsterActions {
    private static final Random random = new Random();

    public static void monsterZug(Monster monster, Character spieler) {
        // 1. Blockstatus prüfen und aufheben
        if (monster.isBlocking()) {
            monster.setBlocking(false); // Blockstatus nach einem Zug zurücksetzen
        }

        // 2. Monster heilt sich mit einer 50%-Chance
        if (monster.getHealth() < monster.getMaxHealth() / 2 && CharacterActions.hasItem(monster, "trank") && random.nextInt(100) < 50) {
            int slot = CharacterActions.findItemSlot(monster, "trank");
            CharacterActions.useItem(monster, slot, spieler);
            return;
        }

        // 3. Monster ruht sich aus (10%-Chance oder bei niedriger Ausdauer)
        if (monster.getAusdauer() < 10 || random.nextInt(100) < 10) {
            CharacterActions.ausruhen(monster);
            return;
        }

        // 4. Monster wirft eine Bombe (20%-Chance)
        if (CharacterActions.hasItem(monster, "bombe") && monster.getAusdauer() >= 10 && random.nextInt(100) < 20) {
            int slot = CharacterActions.findItemSlot(monster, "bombe");
            CharacterActions.useItem(monster, slot, spieler);
            return;
        }

        // 5. Monster blockt (10%-Chance)
        if (monster.getAusdauer() >= 10 && random.nextInt(100) < 10) {
            CharacterActions.blocken(monster);
            return;
        }

        // 6. Standardaktion: Monster greift an
        CharacterActions.attack(monster, spieler);
    }

    public static void showMonster(Monster monster) {
        CharacterActions.showCharacter(monster); // Wiederverwendung der Anzeige-Logik
    }
}
