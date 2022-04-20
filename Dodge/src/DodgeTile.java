public class DodgeTile {
    private int x;
    private int y;
    private int length;
    private int height;
    private int speed;
    private int type;
    private int dir;
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

    public DodgeTile(int x, int y, int length, int height, int speed, int dir, int type) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
        this.type = type;
        this.speed = speed;
        this.dir = dir;
    }

    public void update() {
        int s = getSpeed();
        int y = getY();
        int x = getX();
        if (dir == 1)
            setY(y - s);
        else if (dir == 2)
            setX(x + s);
        else if (dir == 3)
            setY(y + s);
        else if (dir == 4)
            setX(x - s);
    }

    public int getDir() {
        return dir;
    }

    public int getLength() {
        return this.length;
    }

    public int getHeight() {
        return this.height;
    }
    public void setDir(int dir) {
        this.dir = dir;
    }

    public void changeDir() {
        if (dir == NORTH)
            dir = SOUTH;
        else if(dir == EAST)
            dir = WEST;
        else if(dir == SOUTH)
            dir = NORTH;
        else if(dir == WEST)
            dir = EAST;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean intersect(DodgeTile d) {
        if (d.getX() > x + getLength()) {
            return false;
        } else if (d.getX() + d.getLength() < getX()) {
            return false;
        } else if (d.getY() > y + getHeight()) {
            return false;
        } else if (d.getY() + d.getHeight() < getY()) {
            return false;
        } else {
            return true;
        }
    }
}