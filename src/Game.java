import java.util.Scanner;

public class Game {
    // Game class used to initialize the game and allows the player and the bot to
    // each take their turns
    private MapReader map;
    private Player player;
    private Bot bot;

    // Constructor that initializes the map, player and bot
    public Game(MapReader level) {
        map = level;
        player = new Player(level);
        bot = new Bot(level);
    }

    // Player enters the command and this method helps carry out the command.
    public void start(Scanner scanFile) {
        map.dispMap();
        System.out.print("\nPlayer please enter a command: ");

        while (true) {
            String input = scanFile.nextLine().trim();
            if (!input.isEmpty()) {
                // Player turn

                boolean executeInput = player.executeInput(input, scanFile);
                if (executeInput) {
                    map.dispMap();
                    System.out.print("\nPlayer please enter a command: ");
                    if (player.winCondition() && input.toUpperCase().equals("QUIT")) {
                        System.out.println("Congratulations! You won!");
                        break;
                    } else if (input.toUpperCase().equals("QUIT") && !player.winCondition()) {
                        System.out.print("Sorry You loose!,Not enough gold");
                        break;
                    }
                } else {
                    System.out.println("Invalid!");
                    continue;
                }
            }

            // bot turn
            bot.botTurn(player.getX(), player.getY());
            if (bot.capturedCond(player.getX(), player.getY())) {
                System.out.println("The bot has captured you, Game over!");
                break;
            }
            // wining condition
            if (bot.botWinCond()) {
                System.out.println("The bot has won by collecting all gold and exiting, Game Over!");
                break;
            }
        }
    }

}
