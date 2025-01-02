public class ControlledPlayer extends Player {
    int gold;

    public ControlledPlayer(MapReader map) {
        super(map);
        this.gold = 0;

    }

    @Override
    public char getCurrSymbol() {
        return 'P';
    }

    public boolean executeInput(String input) {
        input = input.toUpperCase();
        switch (input) {
            case "LOOK":
                look();
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
                return exit();
            default:
                System.out.println("Invalid input");
                return false;
        }


    }

    private boolean move(int dx, int dy) {
        int X = x + dx;
        int Y = y + dy;
        if (map.getCurrTile(X, Y) != '#') {

            if (map.getCurrTile(X, Y) == 'E' && gold < 5) {
                System.out.println("More gold needed to win!");
                return false;
            }
            map.setCurrTile(x, y, '.');
            x = X;
            y = Y;
            map.setCurrTile(x, y, 'P');
            System.out.println("Move was successful");
            return true;
        }
        System.out.println("Move is not possible");
        return false;
    }

    private void look() {
        for (int i = x - 2; i <= x + 2; ++i) {
            for (int j = y - 2; j <= y + 2; ++j) {
                System.out.print(map.getCurrTile(i, j));
            }
            System.out.println();
        }
    }

    private boolean pickGold() {
        if (map.getCurrTile(x, y) == 'G') {
            ++gold;
            map.setCurrTile(x, y, '.');
            System.out.println("Picked up the Gold, Gold owned: " + gold);
            return true;
        }
        System.out.print("No gold was found");
        return false;
    }

    public boolean winCondition() {
        return map.getCurrTile(x, y) == 'E' && gold >= map.goldToWin();
    }

    private boolean exit() {
        if (winCondition()) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose!");
        }
        return true;
    }
}
