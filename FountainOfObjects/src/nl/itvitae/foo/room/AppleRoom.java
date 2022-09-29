package nl.itvitae.foo.room;

import nl.itvitae.foo.game.Player;

public class AppleRoom extends IRoom {

    private boolean eaten = false;

    public AppleRoom() {
        super('A');
    }

    @Override
    public void onInside(Player player) {
        if (this.eaten) {
            System.out.println("You can still smell the apple that used to be here...");
        } else if (player.getLives() < player.getMaxLives()) {
            player.addLive();
            this.eaten = true;
            System.out.println("You found an apple on the ground. Eating it gave you a life back!");
        } else {
            System.out.println("You found an apple on the ground. ");
        }
    }
}
