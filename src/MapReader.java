import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapReader {
    char[][] mapLevel;
    int row, col;
    int goldToWin;

    public MapReader(String level) throws IOException {
        renderMap(level);
    }


    private void renderMap(String level) throws IOException {
        ArrayList<String> Grids = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(level))) {
            goldToWin = Integer.parseInt(br.readLine().split(" ")[1]); //
            String grid;
            while ((grid = br.readLine()) != null) {
                Grids.add(grid);
            }
        }
        row = Grids.size();
        col = Grids.getFirst().length();
        mapLevel = new char[row][col];
        for (int i = 0; i < row; ++i) {
            mapLevel[i] = Grids.get(i).toCharArray();
        }
    }

    public int goldToWin() {
        return goldToWin;
    }

    public char getCurrTile(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return '#';
        }
        return mapLevel[x][y];
    }

    public void setCurrTile(int x, int y, char currTile) {
        if (x >= 0 || x < row || y >= 0 || y < col) {
            mapLevel[x][y] = currTile;
        }
    }

    public void dispMap() {
        for (int i = 0; i < mapLevel.length; ++i) {
            char[] currRow = mapLevel[i];
            System.out.print(new String(currRow) + '\n');
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int[] findTilePos(char currTile) {
        int[] pos = null;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (mapLevel[i][j] == currTile) {
                    pos = new int[]{i, j};
                }
            }
        }
        return pos;
    }
}

