package nl.itvitae.foo.monster;

public class Monster {

    private final MonsterType type;
    private int hp;

    public Monster(MonsterType type) {
        this.type = type;
        this.hp = type.getHp();
    }

    public String getName() {
        return this.type.getName();
    }

    public int getDamage() {
        return this.type.getDamage();
    }

    public void damage(int damage) {
        this.hp -= damage;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }
}
