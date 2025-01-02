import java.util.Random;

public class Bot extends Player {

    int gold;


    public Bot(MapReader map) {
        super(map);
        this.gold = 0;
    }

    @Override
    public char getCurrSymbol() {
        return 'B';
    }

    public void botTurn(int playerX, int playerY) {
        Random rand = new Random();

        if (gold >= 5) {
            int[] exitPos = map.findTilePos('E');
            if (exitPos != null) {
                moveBot(exitPos[0], exitPos[1]);
                return;
            }
        }

        if (map.getCurrTile(x, y) == 'G') {
            botPickGold();
            return;
        }

        if (rand.nextBoolean()) {
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
        int[] directions = {-1, 0, 1};
        int dx = directions[rand.nextInt(directions.length)];
        int dy = directions[rand.nextInt(directions.length)];
        if (map.getCurrTile(x + dx, y + dy) == '.' || map.getCurrTile(x + dx, y + dy) == 'G') {
            move(dx, dy);
        }
    }

    private void move(int dx, int dy) {
        int updateX = x + dx;
        int updateY = y + dy;
        if (map.getCurrTile(updateX, updateY) == '.' || map.getCurrTile(updateX, updateY) == 'G') {
            map.setCurrTile(updateX, updateY, '.');
            x = updateX;
            y = updateY;
            map.setCurrTile(x, y, 'B');
        }
    }


    private void botPickGold() {
        ++gold;
        map.setCurrTile(x, y, '.');
        System.out.println("The bot has collected gold. It has: " + gold + " gold");
    }


    public boolean botWinCond() {
        return map.getCurrTile(x, y) == 'E' && gold >= map.goldToWin();
    }

    public boolean capturedCond(int PlayerX, int PlayerY) {
        return x == PlayerX && y == PlayerY;
    }
}
