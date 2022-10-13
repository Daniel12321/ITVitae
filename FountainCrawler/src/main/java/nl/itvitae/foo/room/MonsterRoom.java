package nl.itvitae.foo.room;

import nl.itvitae.foo.game.Game;
import nl.itvitae.foo.game.Player;
import nl.itvitae.foo.monster.Monster;
import nl.itvitae.foo.monster.MonsterType;
import nl.itvitae.foo.util.Color;
import nl.itvitae.foo.util.LineType;

import java.util.ArrayList;
import java.util.List;

public class MonsterRoom extends Room {

    private final Game game;
    private final Monster monster;

    public MonsterRoom(Game game, MonsterType type) {
        super("You smell a foul stench!", 'M', Color.GREEN);

        this.game = game;
        this.monster = new Monster(type);
    }

    @Override
    public List<String> handleInside(Player player) {
        List<String> lines = new ArrayList<>();

        if (!this.monster.isAlive()) {
            lines.add(LineType.ROOM.makeLine("You foul stench of a monster still lingers."));
            return lines;
        }

        int damage = this.monster.getDamage();

        lines.add(LineType.MONSTER.makeLine("You encountered a " + this.monster.getName() + "! You lost " + damage + " hp."));
        player.damage(damage);
        if (player.getHp() <= 0) {
            this.game.death();
        } else {
            lines.add(LineType.MONSTER.makeLine("You have " + player.getHp() + " lives left!"));
        }

        return lines;
    }

    public Monster getMonster() {
        return this.monster;
    }

    public void setDead() {
        super.setMapCharColor(Color.RED);
    }
}
