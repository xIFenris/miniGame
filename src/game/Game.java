package game;

import enemy.Monster;
import actions.MonsterActions;
import enemy.MonsterFactory;
import player.Character;
import actions.CharacterActions;

import java.util.List;
import java.util.Scanner;

public class Game {
    private Character player;
    private List<Monster> monsterList;
    private boolean isRunning;

    public Game() {
        setupGame(); // Spiel einrichten (Spieler erstellen, Gegnerliste generieren)
    }

    private void setupGame() {
        Scanner scanner = new Scanner(System.in);

        // Spieler erstellen
        System.out.println("Gebe den Namen deines Charakters ein: ");
        String name = scanner.nextLine();
        player = new Character(name, 100, 100, new String[]{"trank", "bombe", "leer"});

        // Gegnerliste erstellen
        monsterList = MonsterFactory.createShuffledMonsterList(player);

        isRunning = true;
    }

    public void start() {
        runGameLoop(); // Hauptspielschleife starten
    }

    private void runGameLoop() {
        Scanner scanner = new Scanner(System.in);

        // Solange das Spiel läuft und der Spieler lebt
        while (isRunning && player.getHealth() > 0 && !monsterList.isEmpty()) {
            Monster monster = monsterList.get(0); // Der aktuelle Gegner
            System.out.println("\n--- Dein Charakter ---");
            CharacterActions.showCharacter(player);
            System.out.println("\n--- Dein Gegner ---");
            MonsterActions.showMonster(monster);

            // Spieleraktion abfragen
            handlePlayerAction(scanner, monster);

            // Überprüfen, ob das Monster noch lebt
            if (monster.getHealth() > 0) {
                MonsterActions.monsterZug(monster, player);
            }

            // Spielerstatus überprüfen
            checkGameState(monster);
        }
        scanner.close();
    }

    private void handlePlayerAction(Scanner scanner, Monster monster) {
        System.out.println("\nWähle eine Aktion:");
        System.out.println("1. Angreifen");
        System.out.println("2. Blocken");
        System.out.println("3. Gegenstand benutzen");
        System.out.println("4. Ausruhen");
        System.out.println("0. Spiel beenden");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> CharacterActions.attack(player, monster);
            case 2 -> CharacterActions.blocken(player);
            case 3 -> handleUseItem(scanner);
            case 4 -> CharacterActions.ausruhen(player);
            case 0 -> isRunning = false;
            default -> System.out.println("Ungültige Eingabe.");
        }
    }

    private void handleUseItem(Scanner scanner) {
        // Inventar zeigen
        System.out.println("Wähle einen Slot im Inventar:");
        String[] inventory = player.getInventar();
        for (int i = 0; i < inventory.length; i++) {
            System.out.println((i + 1) + ": " + (inventory[i] == null ? "Leer" : inventory[i]));
        }

        // Eingabe abfragen
        try {
            System.out.print("Slot wählen (1-" + inventory.length + "): ");
            int slot = scanner.nextInt();
            if (slot < 1 || slot > inventory.length) {
                System.out.println("Ungültiger Slot.");
                return;
            }

            // Angabe: Slot wird von 1-basiert in 0-basiert umgerechnet
            int slotIndex = slot - 1;

            // Gegner für die Nutzung des Gegenstands auswählen (wenn relevant)
            Monster target = monsterList.isEmpty() ? null : monsterList.get(0);

            // Gegenstand verwenden
            CharacterActions.useItem(player, slotIndex, target);

        } catch (Exception e) {
            System.out.println("Ungültige Eingabe!");
            scanner.nextLine(); // Eingabepuffer leeren
        }
    }

    private void checkGameState(Monster monster) {
        if (player.getHealth() <= 0) {
            System.out.println("\nDu bist gestorben! Spiel beendet.");
            isRunning = false;
        }

        if (monster.getHealth() <= 0) {
            System.out.println("\nDu hast " + monster.getName() + " besiegt!");
            monsterList.remove(monster); // Besiegtes Monster aus der Liste entfernen

            if (monsterList.isEmpty()) {
                System.out.println("\nGratulation! Du hast alle Gegner besiegt!");
                isRunning = false;
            }
        }
    }
}

