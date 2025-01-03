import java.util.Scanner;

public class ControlledPlayer extends Player {
    int gold;
    boolean goldFound = false;
    boolean hasWonCond = false;

    public ControlledPlayer(MapReader map) {
        super(map);
        this.gold = 0;

    }

    @Override
    public char getCurrSymbol() {
        return 'P';
    }

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
        return false;
    }

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

    public boolean winCondition() {
        return hasWonCond;
    }

    private boolean exit(Scanner scanFile) {
        if (winCondition()) {
            System.out.println("Congragulations!, You win!");
        } else {
            System.out.println("You lose!");
        }
        scanFile.close();
        return false;
    }
}
