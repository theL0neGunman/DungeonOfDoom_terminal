import java.util.Random;

public class Bot extends CharacterController {
    // Bot class that is sub class of characterController, describes bot
    // functionality

    int gold;
    boolean goldFound = false;

    // Constructor initializes the bot
    public Bot(MapReader map) {
        super(map);
        this.gold = 0;
    }

    // Overiding the getCurrSymbol to the symbol of the bot 'B'
    @Override
    public char getCurrSymbol() {
        return 'B';
    }

    // Describes the bots moves,randomized to either target the player or go agter
    // the gold randomly
    public void botTurn(int playerX, int playerY) {
        Random rand = new Random();

        if (gold >= 5) {
            int[] exitPos = map.findTilePos('E');
            if (exitPos != null) {
                moveBot(exitPos[0], exitPos[1]);
                return;
            }
        }

        if (map.getCurrTile(x, y) == 'G' || goldFound) {
            botPickGold();
            return;
        }
        boolean randomBoolVal = rand.nextBoolean();
        // Condition that uses rand to either move or randomize the bots movements
        if (randomBoolVal) {
            moveBot(playerX, playerY);
        } else {
            randomMove();
        }

    }

    private void moveBot(int playerX, int playerY) {
        int dx = Integer.compare(playerX, x);
        int dy = Integer.compare(playerY, y);

        if (map.getCurrTile(x + dx, y) == '.' || map.getCurrTile(x + dx, y) == 'G') {
            move(dx, 0);
        } else if (map.getCurrTile(x, y + dy) == '.' || map.getCurrTile(x, y + dy) == 'G') {
            move(0, dy);
        }
    }

    private void randomMove() {
        Random rand = new Random();
        int[] directions = { -1, 0, 1 };
        int dx = directions[rand.nextInt(directions.length)];
        int dy = directions[rand.nextInt(directions.length)];
        if (map.getCurrTile(x + dx, y + dy) == '.' || map.getCurrTile(x + dx, y + dy) == 'G') {
            move(dx, dy);
        }
    }

    private void move(int dx, int dy) {
        // Similar to player logic of movement,but with slight modifications
        int updateX = x + dx;
        int updateY = y + dy;
        if (map.getCurrTile(updateX, updateY) == '.' || map.getCurrTile(updateX, updateY) == 'G') {
            map.setCurrTile(x, y, goldFound ? 'G' : '.');
            goldFound = false;
            x = updateX;
            y = updateY;
            if (map.getCurrTile(updateX, updateY) == 'G') {
                goldFound = true;
            }
            map.setCurrTile(x, y, 'B');
        }
    }

    // Function to allow the bot to pick up gold
    private void botPickGold() {
        if (map.getCurrTile(x, y) == 'G' || goldFound) {
            ++gold;
            map.setCurrTile(x, y, 'B');
            System.out.println("The bot has collected gold. It has: " + gold + " gold");
            goldFound = false;
        }
    }

    // Bot winning conditon
    public boolean botWinCond() {
        return map.getCurrTile(x, y) == 'E' && gold >= map.goldToWin();
    }

    // Condtion where if the bot comes across the player and captures it, basic is
    // where it is on the same tile
    public boolean capturedCond(int PlayerX, int PlayerY) {
        return x == PlayerX && y == PlayerY;
    }
}
