package nl.itvitae.foo.game;

import nl.itvitae.foo.room.IRoom;

import java.util.Scanner;

public class FountainGame {

    private static final boolean PRINT_MAP = true;

    private final Player player;
    private final Map map;
    private final Scanner scanner;

    private boolean hasEnded;

    public FountainGame(int mapX, int mapY, int lives, int monsters) {
        this.player = new Player(lives);
        this.map = new Map(this, mapX, mapY, monsters);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("You are in the room at [" + this.player.getX() + "," + this.player.getY() + "]");
            if (PRINT_MAP)
                this.map.print(this.player);

            IRoom room = this.map.getRoom(player);
            if (room != null) {
                room.onInside(player);
            } else {
                System.out.println("This room is empty.");
            }

            if (this.hasEnded)
                break;

            boolean valid = false;
            while (!valid) {
                System.out.print("What do you want to do: ");
                valid = this.handleCommand(this.scanner.nextLine());
            }

            System.out.println("------------------------------------------------------------");
        }
    }

    public Map getMap() {
        return this.map;
    }

    public void victory() {
        this.hasEnded = true;
        System.out.println("The Fountain of Objects has been reactivated, and you have escaped with your life!");
        System.out.println("You win!");
    }

    public void death() {
        this.hasEnded = true;
        System.out.println("You died... Better luck next time!");
    }

    private boolean handleCommand(String command) {
        String[] args = command.split("\s");

        if (args.length < 2) {
            System.out.println("Invalid command!");
            return false;
        }

        if (args[0].equalsIgnoreCase("move")) {
            return this.player.move(this.map, args[1]);
        } else if (args[0].equalsIgnoreCase("enable") && args[1].equalsIgnoreCase("fountain")) {
            return this.enableFountain();
        } else {
            System.out.println("Invalid command!");
            return false;
        }
    }

    private boolean enableFountain() {
        IRoom room = this.map.getRoom(this.player);
        if (room != null)
            room.enableFountain();

        return true;
    }
}
