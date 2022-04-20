public class DodgeBasic extends DodgeTile {
    private int dir;
    private int[][] points;
    private int index;
    private boolean back;
    public static final int BASIC = 4;
    public int max;
    public int min = 0;
    public DodgeBasic(int x, int y, int length, int height, int speed, int dir) {
        super(x, y, length, height, speed, dir, BASIC);
        points = new int[2][dir];
        if (dir % 2 == 0) {
            for (int i = 0; i < dir; i++) {
                points[0][i] = x + i;
                points[1][i] = y;
            }
        } else {
            for (int i = 0; i < dir; i++) {
                points[0][i] = x;
                points[1][i] = y + i;
            }
        }
        back = false;
        index = 0;
        max = dir;
    }
    @Override
    public void changeDir() {
        if (back && min == 0 && max < index - 40) {
            min = index;
        } else if (max == dir) {
            max = index;
        }
    }
    @Override
    public void update(){
        if (back) {
            setX(points[0][index]);
            setY(points[1][index]);
            index -= getSpeed();
            if (index < min) {
                back = false;
                index = min;
            }
        } else {
            setX(points[0][index]);
            setY(points[1][index]);
            index += getSpeed();
            if (index >= max) {
                back = true;
                index = max- 1;
            }
        }
    }
}
