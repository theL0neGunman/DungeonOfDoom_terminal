import java.util.Scanner;

public class Game {

    private MapReader map;
    private ControlledPlayer player;
    private Bot bot;

    public Game(MapReader level) {
        map = level;
        player = new ControlledPlayer(level);
        bot = new Bot(level);
    }

    public void start(Scanner scanFile) {
        map.dispMap();
        System.out.print("\nPlayer please enter a command: ");


        while (true) {
            String input = scanFile.nextLine().trim();
            if (!input.isEmpty()) {
                boolean executeInput = player.executeInput(input, scanFile);
                if (executeInput) {
                    map.dispMap();
                    System.out.print("\nPlayer please enter a command: ");

                    if (player.winCondition() && input.toUpperCase().equals("QUIT")) {
                        System.out.println("Congratulations! You won!");
                        break;
                    } else {
                        System.out.print("Sorry You loose!,Not enough gold");
                        break;
                    }
                } else {
                    System.out.println("Invalid!");
                    continue;
                }
            }


            bot.botTurn(player.getX(), player.getY());
            if (bot.capturedCond(player.getX(), player.getY())) {
                System.out.println("The bot has captured you, Game over!");
                break;
            }
            if (bot.botWinCond()) {
                System.out.println("The bot has won by collecting all gold and exiting, Game Over!");
                break;
            }
        }
    }


}
