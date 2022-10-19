package nl.itvitae.foo.game;

import nl.itvitae.foo.command.*;
import nl.itvitae.foo.exception.InvalidCommandException;
import nl.itvitae.foo.monster.MonsterParser;
import nl.itvitae.foo.room.Room;
import nl.itvitae.foo.util.Color;
import nl.itvitae.foo.util.LineType;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    public static final boolean PRINT_MAP = true;
    public static final int MAP_VISIBILITY = 4;
    public static final boolean HIDE_CHARS = true;

    private final Map<String, Command> commands;

    private final Player player;
    private final World world;
    private final Scanner scanner;

    private boolean hasEnded;
    private boolean shouldRerender;

    public Game(long seed, int lives) {
        this.commands = new HashMap<>();
        this.player = new Player(lives);
        this.world = new World(seed, this);
        this.scanner = new Scanner(System.in);

        this.commands.put("move", new MoveCommand(this));
        this.commands.put("enable", new FountainCommand(this));
        this.commands.put("pickup", new PickupCommand());
        this.commands.put("eat", new EatCommand());
        this.commands.put("throw", new ThrowCommand(this));
        this.commands.put("open", new InventoryCommand());
    }

    public void start() {
        MonsterParser.parse("monsters.json");

        while (!this.hasEnded) {
            System.out.println("------------------------------------------------------------------------------");

            this.shouldRerender = false;

            Queue<String> lines = new LinkedList<>();
            lines.add(Color.GREEN + "You are in the room at " + this.player.getLocation() + '.' + Color.RESET);

            // Room logic
            Room room = this.world.getRoom(this.player);
            room.setDiscovered();
            lines.addAll(room.handleInside(this.player));

            // Victory or death
            if (this.hasEnded)
                return;

            // Hints
            lines.addAll(this.getHint(this.world.getAdjacentRooms(this.player)));

            // Map
            if (PRINT_MAP)
                this.world.print(this.player, MAP_VISIBILITY, lines);

            // Command Loop
            while (!this.shouldRerender) {
                System.out.print(Color.GREEN + "What do you want to do: " + Color.RESET);
                this.handleCommand(this.scanner.nextLine());
            }
        }
    }

    public World getWorld() {
        return this.world;
    }

    public void rerender() {
        this.shouldRerender = true;
    }

    public void victory() {
        this.hasEnded = true;
        this.shouldRerender = true;
        System.out.println(Color.GREEN + "The Fountain of Objects has been reactivated, and you have escaped with your life!");
        System.out.println("You win!" + Color.RESET);
    }

    public void death() {
        this.hasEnded = true;
        this.shouldRerender = true;
        System.out.println(Color.RED + "You died... Better luck next time!" + Color.RESET);
    }

    /**
     * Generated a list of applicable hint
     *
     * @param rooms All rooms adjacent to the player
     * @return A list of hints
     */
    private List<String> getHint(Room... rooms) {
        Map<Class<? extends Room>, String> hints = new HashMap<>();

        for (Room r : rooms)
            hints.put(r.getClass(), r.getHint());

        return hints.values().stream().filter(s -> !s.isBlank()).map(LineType.HINT::makeLine).collect(Collectors.toList());
    }

    /**
     * Handles commands
     *
     * @param commandLine The command given by the used
     */
    private void handleCommand(String commandLine) {
        String[] args = commandLine.toLowerCase().split("\s");
        boolean rerender = false;

        try {
            Command command = this.commands.get(args[0]);
            if (command == null || !command.matches(args.length))
                throw new InvalidCommandException();

            command.execute(this.player, this.world, args);
        } catch (InvalidCommandException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
