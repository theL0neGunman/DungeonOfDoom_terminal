import java.util.Random;

// Abstract class for the character, it can either be a bot or a player
abstract class CharacterController {
    int x, y;
    MapReader map;

    // Constructor to initialize the map and the player
    public CharacterController(MapReader map) {
        this.map = map;
        InitializePlayer();
    }

    private void InitializePlayer() {
        Random rand = new Random();
        x = rand.nextInt(map.getRow());
        y = rand.nextInt(map.getCol());
        if(map.getCurrTile(x, y) != '.'){
            InitializePlayer();
        }else{
            map.setCurrTile(x, y,  getCurrSymbol());
        }
    }

    // gets the current symbol of either the player or the bot
    public abstract char getCurrSymbol();
    // Gives the coordinates x and y
    public int getX() {return x;}
    public int getY() {return y;}
}
