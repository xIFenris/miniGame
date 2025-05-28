package actions;

import player.Character;

public class CharacterActions {
    public static void attack(player.Character attacker, player.Character target) {
        if (attacker.getAusdauer() >= 5) {
            int damage = 10;

            if (target.isBlocking()) {
                damage /= 2;
                target.setBlocking(false);
                System.out.println(target.getName() + " blockt den Angriff! Schaden wird halbiert.");
            }

            target.setHealth(Math.max(0, target.getHealth() - damage));
            attacker.setAusdauer(attacker.getAusdauer() - 5);

            System.out.println(attacker.getName() + " greift " + target.getName() + " an und verursacht " + damage + " Schaden!");
        } else {
            System.out.println(attacker.getName() + " hat nicht genug Ausdauer für einen Angriff.");
        }
    }

    public static void ausruhen(player.Character character) {
        if (character.getAusdauer() < character.getMaxAusdauer()) {
            System.out.println(character.getName() + " ruht sich aus.");
            character.setAusdauer(Math.min(character.getMaxAusdauer(), character.getAusdauer() + 40));
        } else {
            System.out.println(character.getName() + " hat maximale Ausdauer.");
        }
    }

    public static void blocken(player.Character character) {
        if (character.getAusdauer() >= 10) {
            character.setAusdauer(character.getAusdauer() - 10);
            character.setBlocking(true);
            System.out.println(character.getName() + " blockt den nächsten Angriff.");
        } else {
            System.out.println(character.getName() + " hat nicht genug Ausdauer zum Blocken.");
        }
    }

    public static void showCharacter(player.Character character) {
        System.out.println("=== Charakter Details ===");
        System.out.println("Name: " + character.getName());
        System.out.println("Gesundheit: " + character.getHealth() + "/" + character.getMaxHealth());
        System.out.println("Ausdauer: " + character.getAusdauer() + "/" + character.getMaxAusdauer());
        System.out.println("Inventar:");
        for (int i = 0; i < character.getInventar().length; i++) {
            System.out.println((i + 1) + ": " + (character.getInventar()[i] == null ? "Leer" : character.getInventar()[i]));
        }
    }

    public static boolean hasItem(player.Character character, String item) {
        for (String s : character.getInventar()) {
            if (s != null && s.equalsIgnoreCase(item)) {
                return true;
            }
        }
        return false;
    }

    // Den Slot des Items finden (oder -1, wenn nicht vorhanden)
    public static int findItemSlot(player.Character character, String item) {
        for (int i = 0; i < character.getInventar().length; i++) {
            if (character.getInventar()[i] != null && character.getInventar()[i].equalsIgnoreCase(item)) {
                return i;
            }
        }
        return -1;
    }

    // Gegenstand im Inventar verwenden
    public static void useItem(player.Character character, int slot, Character target) {
        if (slot < 0 || slot >= character.getInventar().length || character.getInventar()[slot] == null) {
            System.out.println("Kein gültiger Gegenstand im gewählten Slot.");
            return;
        }

        String item = character.getInventar()[slot];
        System.out.println(character.getName() + " benutzt " + item + ".");

        // Gegenstand entfernen
        character.getInventar()[slot] = null;

        // Gegenstandslogik: Trank fügt Gesundheit hinzu
        if (item.equalsIgnoreCase("trank")) {
            character.setHealth(Math.min(character.getHealth() + 20, character.getMaxHealth()));
            System.out.println(character.getName() + " regeneriert 20 Gesundheitspunkte.");
        }

        // Gegenstandslogik: Bombe verursacht Schaden am Gegner
        if (item.equalsIgnoreCase("bombe")) {
            target.setHealth(Math.max(target.getHealth() - 15, 0));
            System.out.println(character.getName() + " wirft eine Bombe und verursacht 15 Schaden bei " + target.getName() + ".");
        }
    }


}
