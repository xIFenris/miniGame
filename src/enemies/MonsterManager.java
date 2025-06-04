package enemies;

import character.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonsterManager {

    // Methode zur Erstellung der Gegner-Liste
    public static List<Monster> createShuffledMonsterList(Character player) {
        // "Tutorial"-Gegner
        List<Monster> monsterList = new ArrayList<>();
        monsterList.add(MonsterCreation.createDarkMirror(player)); // Tutorial-Gegner

        // Erstelle andere Gegner und mische sie
        List<Monster> otherMonsters = new ArrayList<>();
        otherMonsters.add(MonsterCreation.createGhoul());
        otherMonsters.add(MonsterCreation.createZombie());
        Collections.shuffle(otherMonsters);

        // Kombiniere Tutorial-Gegner mit den gemischten Monstern
        monsterList.addAll(otherMonsters);

        return monsterList;
    }
}
