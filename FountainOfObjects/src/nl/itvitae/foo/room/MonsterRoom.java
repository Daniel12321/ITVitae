package nl.itvitae.foo.room;

import nl.itvitae.foo.game.FountainGame;
import nl.itvitae.foo.game.Player;

import java.util.Random;

public class MonsterRoom extends IRoom {

    private static final String[] MONSTERS = {"Smelly Goblin", "Troll", "Basilisk", "Zombie", "Giant Wasp"};

    private final FountainGame game;
    private final String monster;

    public MonsterRoom(FountainGame game) {
        super('M');
        this.game = game;
        this.monster = MONSTERS[new Random().nextInt(MONSTERS.length)];
    }

    @Override
    public void onInside(Player player) {
        System.out.println("You encountered a " + this.monster + "! You lost a live.");
        player.removeLife();
        if (player.getLives() <= 0) {
            this.game.death();
        } else {
            System.out.println("You have " + player.getLives() + " lives left!");
        }
    }
}
