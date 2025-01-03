import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


//Mapreader class allows us to read through the map in the txt file and render it on the terminal. It also provides some necessary 
// functions that can be used
public class MapReader {
    char[][] mapLevel;
    int row, col;
    int goldToWin;


    // constructor used to initialize the renderMap function
    public MapReader(String level) throws IOException {
        renderMap(level);
    }


    // reads the map and helps render it
    private void renderMap(String level) throws IOException {
        ArrayList<String> Grids = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(level))) {
            String grid;
            while ((grid = br.readLine()) != null) {
                Grids.add(grid);
            }
        }
        row = Grids.size();
        col = Grids.get(0).length();
        mapLevel = new char[row][col];
        char[] test = null;
        for (int i = 0; i < row; ++i) {
            mapLevel[i] = Grids.get(i).toCharArray();
            if (i == 1) {
                test = Grids.get(i).toCharArray();
            }
        }
        assert test != null;
        goldToWin = test[test.length - 1] - '0';
        System.out.println("Gold to win:" + goldToWin);

    }

    // returns the gold necessary to win, can be modified in the .txt file 
    public int goldToWin() {
        return goldToWin;
    }

    // gets the value of the current tile at the specific coordinates
    public char getCurrTile(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return '#';
        }
        return mapLevel[x][y];
    }

    // sets the value of the current tile at specific coordinates
    public void setCurrTile(int x, int y, char currTile) {
        if (x >= 0 || x < row || y >= 0 || y < col) {
            mapLevel[x][y] = currTile;
        }
    }

    // Displays the rendered map on terminal, here we use ? to obvuscate the actual map
    public void dispMap() {
        for (int i = 0; i < mapLevel.length; ++i) {
            char[] currRow = mapLevel[i];
            if (i <= 1) {
                System.out.print(new String(currRow) + '\n');
            } else {
                System.out.print("?".repeat(currRow.length) + '\n');
            }

        }
    }

// returns the row
    public int getRow() {
        return row;
    }
// returns the column
    public int getCol() {
        return col;
    }

    // gives the position of a current tile in the form of an integer array
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

