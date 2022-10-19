package nl.itvitae.foo.game;

import nl.itvitae.foo.exception.MissingItemException;
import nl.itvitae.foo.item.AppleItem;
import nl.itvitae.foo.item.BombItem;
import nl.itvitae.foo.item.Item;
import nl.itvitae.foo.util.LineType;

public class Player {

    private final int maxHp;

    private Location location;
    private int hp;
    private Item handItem;
    private Item[] inventory;

    public Player(int hp) {
        this.maxHp = hp;
        this.hp = hp;
        this.location = new Location();
        this.handItem = null;
        this.inventory = new Item[4];

        this.inventory[0] = new BombItem(); // TODO: Remove
    }

    /**
     * Moves the player in a compass direction
     *
     * @param direction The compass direction
     * @return whether the move was successful
     */
    public boolean move(String direction) {
        return this.location.move(direction);
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public int getHp() {
        return this.hp;
    }

    public void heal(int hp) {
        this.hp = Math.min(this.hp + hp, this.maxHp);
    }

    public void damage(int damage) {
        this.hp -= damage;
    }

    public Location getLocation() {
        return this.location;
    }

    public Item setHandItem(Item item) {
        Item old = this.handItem;
        this.handItem = item;
        return old;
    }

    public boolean addItem(Item item) {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] == null) {
                this.inventory[i] = item;
                return true;
            }
        }

        return false;
    }

    public void removeApple() throws MissingItemException {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] instanceof AppleItem) {
                this.inventory[i] = null;
                return;
            }
        }

        throw new MissingItemException(LineType.WARNING.makeLine("You do not have any apples."));
    }

    public void removeBomb() throws MissingItemException {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] instanceof BombItem) {
                this.inventory[i] = null;
                return;
            }
        }

        throw new MissingItemException(LineType.WARNING.makeLine("You do not have any bombs."));

    }
}
