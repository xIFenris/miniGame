package src.enemy;

public class MonsterFactory {

    public static Monster createGhoul(){
        Monster ghoul = new Monster();
        ghoul.setMonsterName("Ghoul");
        ghoul.setMonsterHealth(80);
        ghoul.angreifen();
        return ghoul;
    }



}
