package player;

import java.util.HashMap;
import java.util.Map;

public class Spellbook {
    private static final Map<String, Ability> abilities = new HashMap<>();

    static {
        // Flammenschwerthieb: Verursacht Schaden
        abilities.put("Flammenschwerthieb", new Ability(
                "Flammenschwerthieb",
                20,
                "Ein kraftvoller Hieb, der 30 Schaden verursacht. Kosten: 20 Ausdauer.",
                (user, target) -> {
                    target.setHealth(Math.max(0, target.getHealth() - 30));
                    System.out.println(user.getName() + " führt Flammenschwerthieb aus und verursacht 30 Schaden bei "
                            + target.getName() + "!");
                }
        ));

        // Wasserkugel: Blocken für 3 Runden
        abilities.put("Wasserkugel", new Ability(
                "Wasserkugel",
                15,
                "Schützt den Anwender für 3 Runden vor Angriffen. Kosten: 15 Ausdauer.",
                (user, target) -> {
                    user.setBlocking(true); // Setzt den Blocking-Status
                    System.out.println(user.getName() + " erstellt eine Wasserkugel und blockiert Angriffe!");
                }
        ));

        // Paralyse: Gegner setzt eine Runde aus
        abilities.put("Paralyse", new Ability(
                "Paralyse",
                10,
                "Lässt den Gegner eine Runde aussetzen. Kosten: 10 Ausdauer.",
                (user, target) -> {
                    target.setParalyzed(true); // Gegner wird paralysiert
                    System.out.println(user.getName() + " setzt Paralyse ein! " + target.getName()
                            + " ist für die nächste Runde außer Gefecht.");
                }
        ));
    }

    // Zugriffsmethoden
    public static Ability getAbility(String name) {
        return abilities.get(name);
    }

    public static Map<String, Ability> getAbilities() {
        return abilities;
    }
}
