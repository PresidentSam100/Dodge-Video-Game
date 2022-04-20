public class DodgePlayer extends DodgeTile {
    private int length = 25;
    private int height = 25;
    private int dir = 0;
    public static final int NORTH = 1;
    public static final int EAST = 2;
    public static final int SOUTH = 3;
    public static final int WEST = 4;
    public static final int NONE = 0;
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int PLAYER = 2;
    public static final int END = 3;
    public static final int BASIC = 4;
    public static final int PATTERN = 5;
    public static final int LASER = 6;
    private int prevX = 0;
    private int prevY = 0;

    public DodgePlayer(int x, int y, int length, int height) {
        super(x, y, length, height, 3, NONE, PLAYER);
    }

    public void moveDir(char c){
        if (c == 'W' || c == 'w') {
            dir = NORTH;
        } else if (c == 'A' || c == 'a') {
            dir = WEST;
        } else if (c == 'D' || c == 'd') {
            dir = EAST;
        } else if (c == 'S' || c == 's') {
            dir = SOUTH;
        }
    }

    public void update(){
        int s = getSpeed();
        int y = getY();
        int x = getX();
        prevX = x;
        prevY = y;
        if (dir == 1)
            setY(y - s);
        else if (dir == 2)
            setX(x + s);
        else if (dir == 3)
            setY(y + s);
        else if (dir == 4)
            setX(x - s);
    }

    public void setDir(int dir){
        this.dir = dir;
    }

    public void returnToPrev(){
        super.setX(prevX);
        super.setY(prevY);
    }

    public int getDir() {
        return this.dir;
    }
    public void setLength(int length){
        this.length = length;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getLength() {
        return this.length;
    }
}

