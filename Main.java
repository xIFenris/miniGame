import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Spielername eingeben
        System.out.println("Gebe den Namen deines Characters ein: ");
        String name = scanner.nextLine();

        // Charaktere erstellen
        SpielCharakter spieler = new SpielCharakter(name);
        SpielCharakter gegner = new SpielCharakter("Dunkler Spiegel");

        // Startstatus anzeigen
        System.out.println("\n--- Dein Charakter ---");
        spieler.showCharacter();

        System.out.println("\n--- Dein Gegner ---");
        gegner.showCharacter();

        boolean spielLäuft = true;

        // Hauptspiel-Schleife
        while (spielLäuft && spieler.getHealth() > 0 && gegner.getHealth() > 0) {
            // Spieleraktion wählen
            System.out.println("\nWähle eine Aktion:");
            System.out.println("1. Angreifen");
            System.out.println("2. Blocken");
            System.out.println("3. Gegenstand benutzen");
            System.out.println("4. Ausruhen");
            System.out.println("0. Spiel beenden");

            int wahl = scanner.nextInt();

            // Spieleraktion ausführen
            switch (wahl) {
                case 1:
                    Aktion.angreifen(spieler, gegner);
                    break;
                case 2:
                    Aktion.blocken(spieler);
                    break;
                case 3:
                    System.out.println("Wähle einen Slot zum Benutzen:");
                    String[] inventar = spieler.getInventar();
                    for (int i = 0; i < inventar.length; i++) {
                        System.out.println((i + 1) + ": " + inventar[i]);
                    }
                    System.out.print("Slot (1-" + inventar.length + "): ");
                    int slot = scanner.nextInt();
                    Aktion.gegenstandBenutzen(spieler, slot - 1, gegner); // Den Gegner als dritten Parameter übergeben
                    break;


                case 4:
                    Aktion.ausruhen(spieler);
                    break;
                case 0:
                    spielLäuft = false;
                    System.out.println("Spiel beendet.");
                    continue; // Spiel beenden
                default:
                    System.out.println("Ungültige Eingabe.");
                    continue; // Zurück zur Auswahl
            }

            // Überprüfen, ob der Spieler noch lebt
            if (spieler.getHealth() <= 0) {
                System.out.println("\nDu wurdest besiegt!");
                break;
            }

            // Gegnerzug durchführen (strategisch)
            Aktion.gegnerZug(gegner, spieler);

            // Status nach der Runde anzeigen
            System.out.println("\nStatus nach der Runde:");
            spieler.showCharacter();
            gegner.showCharacter();

            // Überprüfen, ob der Gegner besiegt wurde
            if (gegner.getHealth() <= 0) {
                System.out.println("\nDu hast den Dunklen Spiegel besiegt!");
                break;
            }
        }

        scanner.close();
    }
}
