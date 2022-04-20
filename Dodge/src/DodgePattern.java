public class DodgePattern extends DodgeTile {
    private int moveShape;
    private int moveLength;
    private int[][] movement;
    private int index;
    public static final int EAST = 2;
    public static final int SOUTH = 3;
    public static final int WEST = 4;
    public static final int NONE = 0;
    public static final int EMPTY = 0;
    public static final int PLAYER = 2;
    public static final int END = 3;
    public static final int BASIC = 4;
    public static final int PATTERN = 5;
    public static final int LASER = 6;
    public DodgePattern(int x, int y, int length, int height, int type, int moveShape,int moveLength) {
        super(x, y, length, height, 1, NONE, PATTERN);
        index = 0;
        this.moveLength = moveLength;
        int ax = getX();
        int ay = getY();
        switch (moveShape) {
            case 1:
                movement = new int[2][4 * moveLength];
                for (int i = 0; i < moveLength; i++) {
                    movement[0][i] = x += 1;
                    movement[1][i] = y += 1;
                }
                for (int i = 0; i < moveLength; i++) {
                    movement[0][i + moveLength] = x -= 1;
                    movement[1][i + moveLength] = y += 1;
                }
                for (int i = 0; i < moveLength; i++) {
                    movement[0][i + moveLength * 2] = x -= 1;
                    movement[1][i + moveLength * 2] = y -= 1;
                }

                for (int i = 0; i < moveLength; i++) {
                    movement[0][i + moveLength * 3] = x += 1;
                    movement[1][i + moveLength * 3] = y -= 1;
                }
                break;
            case 2:
                movement = new int[2][4 * moveLength];
                for (int i = 0; i < moveLength; i++) {
                    movement[0][i] = x += 1;
                    movement[1][i] = y += 1;
                }
                for (int i = 0; i < moveLength * 2; i++) {
                    movement[0][i + moveLength] = x -= 1;
                    movement[1][i + moveLength] = y;
                }
                for (int i = 0; i < moveLength; i++) {
                    movement[0][i + moveLength * 3] = x += 1;
                    movement[1][i + moveLength * 3] = y -= 1;
                }
                break;
            case 3:
                movement = new int[2][4 * moveLength];
                for (int i = 0; i < moveLength; i++) {
                    movement[0][i] = x += 1;
                    movement[1][i] = y;
                }
                for (int i = 0; i < moveLength; i++) {
                    movement[0][i + moveLength] = x;
                    movement[1][i + moveLength] = y += 1;
                }
                for (int i = 0; i < moveLength; i++) {
                    movement[0][i + moveLength * 2] = x -= 1;
                    movement[1][i + moveLength * 2] = y;
                }

                for (int i = 0; i < moveLength; i++) {
                    movement[0][i + moveLength * 3] = x;
                    movement[1][i + moveLength * 3] = y -= 1;
                }
                break;
        }
    }
    @Override
    public void update(){
        index += getSpeed();
        if (index >= movement[0].length){
            index = 0;
        }
        this.setX(movement[0][index]);
        this.setY(movement[1][index]);
    }
}