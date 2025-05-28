package actions;

import character.Character;

public class CharacterActions {
    public static void attack(Character attacker, Character target) {
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

    public static void ausruhen(Character character) {
        if (character.getAusdauer() < character.getMaxAusdauer()) {
            System.out.println(character.getName() + " ruht sich aus.");
            character.setAusdauer(Math.min(character.getMaxAusdauer(), character.getAusdauer() + 40));
        } else {
            System.out.println(character.getName() + " hat maximale Ausdauer.");
        }
    }

    public static void blocken(Character character) {
        if (character.getAusdauer() >= 10) {
            character.setAusdauer(character.getAusdauer() - 10);
            character.setBlocking(true);
            System.out.println(character.getName() + " blockt den nächsten Angriff.");
        } else {
            System.out.println(character.getName() + " hat nicht genug Ausdauer zum Blocken.");
        }
    }

    public static void showCharacter(Character character) {
        System.out.println("=== Charakter Details ===");
        System.out.println("Name: " + character.getName());
        System.out.println("Gesundheit: " + character.getHealth() + "/" + character.getMaxHealth());
        System.out.println("Ausdauer: " + character.getAusdauer() + "/" + character.getMaxAusdauer());
        System.out.println("Inventar:");
        String[] inventory = character.getInventory().getItems(); // Zugriff auf Items
        for (int i = 0; i < inventory.length; i++) {
            System.out.println((i + 1) + ": " + (inventory[i] == null ? "Leer" : inventory[i]));
        }
    }

    public static boolean hasItem(Character character, String item) {
        String[] inventory = character.getInventory().getItems(); // Zugriff auf Items
        for (String s : inventory) {
            if (s != null && s.equalsIgnoreCase(item)) {
                return true;
            }
        }
        return false;
    }

    // Den Slot des Items finden (oder -1, wenn nicht vorhanden)
    public static int findItemSlot(Character character, String item) {
        String[] inventory = character.getInventory().getItems(); // Zugriff auf Items
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].equalsIgnoreCase(item)) {
                return i;
            }
        }
        return -1;
    }

    // Gegenstand im Inventar verwenden
    public static void useItem(Character character, int slot, Character target) {
        String[] inventory = character.getInventory().getItems(); // Zugriff auf Items
        if (slot < 0 || slot >= inventory.length || inventory[slot] == null) {
            System.out.println("Kein gültiger Gegenstand im gewählten Slot.");
            return;
        }

        String item = inventory[slot];
        System.out.println(character.getName() + " benutzt " + item + ".");

        // Gegenstand entfernen
        inventory[slot] = null;

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
