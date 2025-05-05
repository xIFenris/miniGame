import java.util.Random;

public class Aktion {

    public static void angreifen(SpielCharakter angreifer, SpielCharakter verteidiger) {
        if (angreifer.getAusdauer() >= 5) {
            int schaden = 10;

            if (verteidiger.isBlocking()) {
                schaden /= 2;
                verteidiger.setBlocking(false);
                System.out.println(verteidiger.getName() + " blockt den Angriff! Schaden wird halbiert.");
            }

            verteidiger.setHealth(verteidiger.getHealth() - schaden);
            angreifer.setAusdauer(angreifer.getAusdauer() - 5);

            System.out.println(angreifer.getName() + " greift an und verursacht " + schaden + " Schaden!");
        } else {
            System.out.println(angreifer.getName() + " hat nicht genug Ausdauer für einen Angriff.");
        }
    }

    public static void blocken(SpielCharakter blockierer) {
        if (blockierer.getAusdauer() >= 10) {
            blockierer.setAusdauer(blockierer.getAusdauer() - 10);
            blockierer.setBlocking(true);
            System.out.println(blockierer.getName() + " blockt! Der nächste Angriff verursacht nur halben Schaden.");
        } else {
            System.out.println(blockierer.getName() + " hat nicht genug Ausdauer zum Blocken.");
        }
    }

    public static void gegenstandBenutzen(SpielCharakter benutzer, int slotIndex, SpielCharakter gegner) {
        String[] inventar = benutzer.getInventar();

        if (slotIndex < 0 || slotIndex >= inventar.length) {
            System.out.println("Ungültiger Slot! Wähle einen Slot zwischen 1 und " + inventar.length + ".");
            return;
        }

        String item = inventar[slotIndex];

        switch (item.toLowerCase()) {
            case "trank":
                benutzer.setHealth(benutzer.getHealth() + 20);
                inventar[slotIndex] = "Leer";
                benutzer.setInventar(inventar);
                System.out.println(benutzer.getName() + " benutzt einen Trank und heilt 20 HP.");
                break;

            case "bombe":
                benutzer.setAusdauer(Math.max(0, benutzer.getAusdauer() - 10)); // Bombe kostet Ausdauer
                if (gegner != null) {  // Falls Gegner angegeben wurde
                    gegner.setHealth(gegner.getHealth() - 20);  // Schaden am Gegner
                }
                inventar[slotIndex] = "Leer";
                benutzer.setInventar(inventar);
                System.out.println(benutzer.getName() + " wirft eine Bombe!");
                break;

            case "leer":
                System.out.println("Der Slot ist leer.");
                break;

            default:
                System.out.println("Unbekannter Gegenstand: " + item);
                break;
        }
    }

    public static void ausruhen(SpielCharakter charakter) {
        int aktuelleAusdauer = charakter.getAusdauer();
        int neueAusdauer = Math.min(aktuelleAusdauer + 20, 100);
        charakter.setAusdauer(neueAusdauer);
        System.out.println(charakter.getName() + " ruht sich aus und regeneriert 20 Ausdauer.");
    }

    public static void gegnerZug(SpielCharakter gegner, SpielCharakter spieler) {
        Random random = new Random();

        // 50% Chance auf Heilung bei HP <= 100 (statt 75%)
        if (gegner.getHealth() <= 100 && hatItem(gegner, "trank") && random.nextInt(100) < 50) {
            int slot = findeItemSlot(gegner, "trank");
            gegenstandBenutzen(gegner, slot, spieler);
            return;
        }

        // 10% Chance auf Ausruhen (statt 15%) oder immer bei Ausdauer < 10
        if (gegner.getAusdauer() < 10 || random.nextInt(100) < 10) {
            ausruhen(gegner);
            return;
        }

        // 20% Chance Bombe (statt 30%)
        if (hatItem(gegner, "bombe") && gegner.getAusdauer() >= 10 && random.nextInt(100) < 20) {
            int slot = findeItemSlot(gegner, "bombe");
            gegenstandBenutzen(gegner, slot, spieler);
            return;
        }

        // 10% Chance Blocken (statt 20%)
        if (gegner.getAusdauer() >= 10 && random.nextInt(100) < 10) {
            blocken(gegner);
            return;
        }

        // Standardaktion: Angreifen
        angreifen(gegner, spieler);
    }




    private static boolean hatItem(SpielCharakter charakter, String itemName) {
        for (String item : charakter.getInventar()) {
            if (item.equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    private static int findeItemSlot(SpielCharakter charakter, String itemName) {
        String[] inventar = charakter.getInventar();
        for (int i = 0; i < inventar.length; i++) {
            if (inventar[i].equalsIgnoreCase(itemName)) {
                return i;
            }
        }
        return -1;
    }
}
