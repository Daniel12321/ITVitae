package nl.itvitae.foo.monster;

import java.util.Random;

public enum MonsterType {

    ZOMBIE("Zombie", 3, 10, 40, 60),
    GOBLIN("Smelly Goblin", 5, 12, 50, 60),
    WASP("Giant Wasp", 5, 15, 40, 50),
    TROLL("Troll", 8, 18, 30, 100),
    BASILISK("Basilisk", 10, 20, 60, 100);

    private static final Random R = new Random();

    private final String name;
    private final int minDmg, maxDmg;
    private final int minHp, maxHp;

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

    public int getDamage() {
        return R.nextInt(this.maxDmg - this.minDmg) + this.minDmg;
    }

    public int getHp() {
        return R.nextInt(this.maxHp - this.minHp) + this.minHp;
    }

    public static MonsterType random(double sr) {
        if (sr > 0.8) return BASILISK;
        else if (sr > 0.6) return TROLL;
        else if (sr > 0.4) return WASP;
        else if (sr > 0.2) return GOBLIN;
        else return ZOMBIE;
    }
}
