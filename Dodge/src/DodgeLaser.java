public class DodgeLaser extends DodgeTile {
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
    private int emitterLength;
    private int emitterHeight;
    public int timer;
    boolean on = false;
    int cooldown = 0;
    //    private int x;
    //    private int y;
    //xLaser,yLaser = emitter coords(top left and bottom right)
    //(x1,y1) = top left of emitter
    //(x2, y2) = bottom right of emitter
    //(x,y) = top left of laser
    //(length, width) = length/width of laser
    boolean evil = false;
    public DodgeLaser(int x, int y, int length, int height, int laserHeight, int laserWidth, int timer) {
        super(x, y, length, height, NONE, NONE, LASER);
        this.on = true;
        this.emitterHeight = laserHeight;
        this.emitterLength = laserWidth;
        this.timer = timer;
        evil = (int)(Math.random() * 10) == 0;
    }

    @Override
    public void update() {
        cooldown++;
        if (cooldown >= timer) {
            cooldown = 0;
            on = !on;
        }
    }

    public int getemitHeight() {
        return this.emitterHeight;
    }

    public boolean getOn() {
        return on;
    }

    public int getemitLength() {
        return this.emitterLength;
    }

    public int getEmitX1() {
        return this.getX();
    }
    public int getEmitY1() {
        return this.getY();
    }

    @Override
    public boolean intersect(DodgeTile o){
        if (o.getX() > getEmitX1() + getemitLength()){
            return false;
        }
        if (o.getX() + o.getLength() < getEmitX1()){
            return false;
        }
        if (o.getY() > getEmitY1() + getemitHeight()){
            return false;
        }
        if (o.getY() + o.getHeight() < getEmitY1()){
            return false;
        }
        return true;
    }
}
