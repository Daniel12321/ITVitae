package nl.itvitae.foo;

import nl.itvitae.foo.game.FountainGame;

public class Main {

    public static void main(String[] args) {
//        new Game().start();
        new FountainGame(12, 5, 3, 16).start();
    }
}
