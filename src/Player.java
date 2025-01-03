import java.util.Scanner;
// Player class is a subclass of charactercontroller
public class Player extends CharacterController {
    int gold;
    boolean goldFound = false;
    boolean hasWonCond = false;

    // Initialize the map and gold
    public Player(MapReader map) {
        super(map);
        this.gold = 0;

    }

    // method overriding of getCurrSymbol to represent the Player symbol 'P'
    @Override
    public char getCurrSymbol() {
        return 'P';
    }

    // Executes the input command
    public boolean executeInput(String input, Scanner scanFile) {
        input = input.toUpperCase();
        switch (input) {
            case "HELLO":
                System.out.println("Gold to win: " + map.goldToWin());
                return true;
            case "LOOK":
                look(x, y);
                return true;
            case "GOLD":
                System.out.println("Amount of Gold owned: " + gold);
                return true;
            case "PICKUP":
                return pickGold();
            case "MOVE N":
                return move(-1, 0);
            case "MOVE E":
                return move(0, 1);
            case "MOVE S":
                return move(1, 0);
            case "MOVE W":
                return move(0, -1);
            case "QUIT":
                return true;

            default:
                System.out.println("Invalid input");
                return false;
        }
    }

    // Move functionality to the player 1 tile based on its previous location
    private boolean move(int dx, int dy) {
        int X = x + dx;
        int Y = y + dy;
        System.out.println(map.getCurrTile(X, Y) + "Current tile");
        if (map.getCurrTile(X, Y) != '#') {
            if (map.getCurrTile(X, Y) == 'E' && gold < map.goldToWin()) {
                System.out.println("More gold needed to win!");
                return false;
            }
            if (map.getCurrTile(X, Y) == 'E' && gold >= map.goldToWin()) {
                hasWonCond = true;
            } else if (map.getCurrTile(X, Y) != 'E') {
                hasWonCond = false;
            }
            map.setCurrTile(x, y, goldFound ? 'G' : '.');
            goldFound = false;
            x = X;
            y = Y;
            if (map.getCurrTile(X, Y) == 'G') {
                goldFound = true;
            }
            map.setCurrTile(x, y, 'P');
            return true;
        }
        System.out.println("Move is not possible");
        return true;
    }

    // Allows the player to peak at the map but in a 5x5 area
    private void look(int x, int y) {
        System.out.println("Looking.....:");
        int gridSize = 5;
        int halfGrid = gridSize / 2;


        for (int i = x - halfGrid; i <= x + halfGrid; ++i) {
            for (int j = y - halfGrid; j <= y + halfGrid; ++j) {
                int row = map.getRow();
                int col = map.getCol();
                if (i == x && j == y) {
                    System.out.print('P');
                } else if (i >= 0 && i < row && j >= 0 && j < col) {
                    System.out.print(map.getCurrTile(i, j));
                } else {
                    System.out.print('#');
                }
            }
            System.out.println("\n"); // Newline after each row
        }
    }

    // Helps pick up the gold when player is on the gold tile
    private boolean pickGold() {
        if (map.getCurrTile(x, y) == 'G' || goldFound) {
            ++gold;
            map.setCurrTile(x, y, 'P');
            System.out.println("Picked up the Gold, Gold owned: " + gold);
            goldFound = false;
            return true;
        }
        System.out.print("No gold was found");
        return false;
    }

    // Returns the win condtion
    public boolean winCondition() {
        return hasWonCond;
    }

    
}
