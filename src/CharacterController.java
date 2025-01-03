import java.util.Random;

abstract class CharacterController {
    int x, y;
    MapReader map;


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

    public abstract char getCurrSymbol();
    public int getX() {return x;}
    public int getY() {return y;}
}
