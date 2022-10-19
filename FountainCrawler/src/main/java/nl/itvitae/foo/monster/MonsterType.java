package nl.itvitae.foo.monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterType {

    static final List<MonsterType> TYPES = new ArrayList<>();

/*
    ZOMBIE("Zombie", 3, 10, 40, 60),
    GOBLIN("Smelly Goblin", 5, 12, 50, 60),
    WASP("Giant Wasp", 5, 15, 40, 50),
    TROLL("Troll", 8, 18, 30, 100),
    BASILISK("Basilisk", 10, 20, 60, 100);
*/

    private static final Random R = new Random();

    private String name;
    private int minDmg, maxDmg;
    private int minHp, maxHp;

    MonsterType() {
        this(null, 0, 0, 0, 0);
    }

    MonsterType(String name, int minDmg, int maxDmg, int minHp, int maxHp) {
        this.name = name;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.minHp = minHp;
        this.maxHp = maxHp;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinDmg() {
        return minDmg;
    }

    public void setMinDmg(int minDmg) {
        this.minDmg = minDmg;
    }

    public int getMaxDmg() {
        return maxDmg;
    }

    public void setMaxDmg(int maxDmg) {
        this.maxDmg = maxDmg;
    }

    public int getMinHp() {
        return minHp;
    }

    public void setMinHp(int minHp) {
        this.minHp = minHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getDamage() {
        return R.nextInt(this.maxDmg - this.minDmg) + this.minDmg;
    }

    public int getHp() {
        return R.nextInt(this.maxHp - this.minHp) + this.minHp;
    }

    public static MonsterType random(double sr) {
        return TYPES.get((int) (sr * TYPES.size()));

//        if (sr > 0.8) return BASILISK;
//        else if (sr > 0.6) return TROLL;
//        else if (sr > 0.4) return WASP;
//        else if (sr > 0.2) return GOBLIN;
//        else return ZOMBIE;
    }

    @Override
    public String toString() {
        return "MonsterType{" +
                "name='" + name + '\'' +
                ", minDmg=" + minDmg +
                ", maxDmg=" + maxDmg +
                ", minHp=" + minHp +
                ", maxHp=" + maxHp +
                '}';
    }
}
