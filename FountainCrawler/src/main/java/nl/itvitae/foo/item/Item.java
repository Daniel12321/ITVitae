package nl.itvitae.foo.item;

public abstract class Item {

    private final String name;
    private final boolean hand;

    public Item(String name, boolean hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return this.name;
    }

    public boolean isHand() {
        return this.hand;
    }

    public static Item random(double sr) {
        if (sr > 0.8) return new SwordItem();
        else if (sr > 0.6) return new ShieldItem();
        else if (sr > 0.35) return new BombItem();
        else return new AppleItem();
    }
}
