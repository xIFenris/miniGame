package src.enemy;

public class Monster {

    private String monsterName;
    private int monsterHealth;

    void angreifen() {
        System.out.println(monsterName + " greift dich an!");
    }
    public String toString() {
        return "Monster: " + monsterName + "| Lebenspunkte: " + monsterHealth;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        monsterName = monsterName;
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }

    public void setMonsterHealth(int monsterHealth) {
        monsterHealth = monsterHealth;
    }
}
