package character;

public class Ability {
    private String abilityname;                // Name der Fähigkeit
    private int staminaCost;            // Ausdauerkosten
    private String effectDescription;   // Beschreibung des Effekts
    private EffectLogic effectLogic;    // Die Effektlogik als Methode (siehe unten)

    // Konstruktor
    public Ability(String name, int staminaCost, String effectDescription, EffectLogic effectLogic) {
        this.abilityname = name;
        this.staminaCost = staminaCost;
        this.effectDescription = effectDescription;
        this.effectLogic = effectLogic; // Funktion wird hier übergeben
    }

    // Getter
    public String getAbilityname() {
        return abilityname;
    }

    public int getStaminaCost() {
        return staminaCost;
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    // Effekt auf Anwender und Ziel anwenden
    public void applyEffect(Character user, Character target) {
        if (user.getAusdauer() >= staminaCost) {
            effectLogic.effect(user, target); // Die Logik wird ausgeführt
            user.setAusdauer(user.getAusdauer() - staminaCost);
        } else {
            System.out.println(user.getName() + " hat nicht genug Ausdauer, um " + abilityname + " einzusetzen!");
        }
    }

    // Innere Logik-Klasse (direkt hier definiert)
    public static interface EffectLogic {
        void effect(Character user, Character target);
    }
}
