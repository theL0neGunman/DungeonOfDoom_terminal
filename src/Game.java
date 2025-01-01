import java.util.Scanner;

public class Game {

    private MapReader map;
    private ControlledPlayer player;
    private Bot bot;

    public Game(MapReader level) {
        this.map = level;
        player = new ControlledPlayer(level);
        bot = new Bot(level);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            map.dispMap();

            // Player turn
            System.out.print("\nPlayer please enter a command: ");
            String input = scanner.nextLine();
            if (player.executeInput(input)) {
                if (player.winCondition()) {
                    System.out.println("Congratulations! You won!");
                    break;
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
        scanner.close();
    }


}
