package nl.itvitae.gog;

import nl.itvitae.gog.game.Color;
import nl.itvitae.gog.game.Game;
import nl.itvitae.gog.game.Goose;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Game of Goose!");
        System.out.print("How many players are there: ");
        int players = Integer.parseInt(scanner.nextLine());
        Goose[] goose = new Goose[players];
        System.out.println();

        List<Color> picked = new ArrayList<>(players);
        for (int i = 0; i < players; i++) {
            Color color = null;

            while (color == null) {
                System.out.print("Player " + (i + 1) + ", choose your color: ");
                try {
                    color = Color.valueOf(scanner.nextLine().toUpperCase());
                    if (picked.contains(color)) {
                        System.out.println("That color was picked already.");
                        color = null;
                    } else {
                        picked.add(color);
                    }
                } catch (Exception exc) {
                    System.out.println("Invalid color!");
                }
            }

            goose[i] = new Goose(color);
        }

        new Game(goose).start();
    }
}
