import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DodgeMap {
    //length is rows, width is columns
    public int WorldLength = 30, WorldWidth = 30;
    public int startLength = 15, startWidth = 15;
    int playerI = 0, playerJ = 0;
    DodgePlayer Player;
    DodgeTile endZone = new DodgeTile(0,0,0,0,0,0,0);
    DodgeTile[][] tiles;
    int playerX = -1, playerY = -1;
    int c = 700/startLength;
    DodgeTile key ;
    boolean returnToBeginning = false;
    public DodgeMap() throws IOException
    {
        tiles = new DodgeTile[WorldLength][WorldWidth];
        ScanLevel(1);
        key = new DodgeTile(-1, -1, c, c, 0, 0, 0);
    }
    public DodgePlayer getPlayer()
    {
        return Player;
    }
    public DodgeTile getTile(int i, int j)
    {
        return tiles[i][j];
    }

    public void ScanLevel (int l) throws IOException {
        Scanner sc = new Scanner(new File("C:\\Downloads\\Dodge\\Levels\\level" + l + ".txt"));
        for (int i = 0; i < WorldLength; i++) {
            for (int j = 0; j < WorldWidth; j++) {
                int x = sc.nextInt();
                if (x == 0) {
                    tiles[i][j] = new DodgeTile(i * c, j * c, c, c, 0, 0, x);
                }
                if (x == 1)
                    tiles[i][j] = new DodgeTile(i * c, j * c, c, c, 0, 0, x);
                if (x == 2) {
                    tiles[i][j] = new DodgeTile(i * c, j * c, c, c, 0, 0, x);
                    Player = new DodgePlayer(i * c + 5, j * c + 5, 10, 10);
                }
                if (x == 3) {
                    tiles[i][j] = new DodgeTile(i * c, j * c, c, c, 0, 0, x);
                    endZone = tiles[i][j];
                }
                if (x == 4) {
                    int dir = sc.nextInt();
                    tiles[i][j] = new DodgeBasic(i * c, j * c, c/3, c/3, (int)(Math.random()*2)+1, dir+1);
                }
                if (x == 5) {
                    //need moveshape and moveLength
                    int moveShape = sc.nextInt();
                    int moveLength = sc.nextInt();
                    tiles[i][j] = new DodgePattern(i * c, j * c, c, c, (int)(Math.random()*2)+1, moveShape, moveLength); //random numbers for shape and length
                }
                if (x == 6) {
                    //need coordinates for laser. Maybe a timer that isn't random
                    int height = sc.nextInt();
                    int width = sc.nextInt();
                    tiles[i][j] = new DodgeLaser(i * c + 5, j * c + 5, 10, 10, height, width, (int)(Math.random()*150)+100); //random numbers for xPos and yPos
                }
            }
        }
    }
    public boolean winLevel()
    {
        return (Player.intersect(endZone));
    }
    public void update() {
        for (int i = 0; i < WorldLength; i++) {
            for (int j = 0; j < WorldWidth; j++) {
                if (tiles[i][j].getType() >= 4) {
                    for (int k = 0; k < WorldLength; k++) {
                        for (int l = 0; l < WorldWidth; l++) {
                            if (tiles[l][k].getType() == 1 && tiles[i][j].intersect(tiles[l][k])) {
                                tiles[i][j].changeDir();
                                break;
                            }
                        }
                    }
                }
                tiles[i][j].update();
            }
        }
    }

    boolean isHit() {
        boolean b = false;
        for (int i = 0; i < WorldLength; i++) {
            for (int j = 0; j < WorldWidth; j++) {
                if (Player.intersect(tiles[i][j]) && tiles[i][j].getType() >= 4 && tiles[i][j].getType() != 6) {
                    b = true;
                }
                if (Player.intersect(tiles[i][j]) && tiles[i][j].getType() == 1) {
                    Player.setDir(0);
                }
                if (tiles[i][j].getType()==6){
                    DodgeLaser l = (DodgeLaser)tiles[i][j];
                    if (l.on&&l.intersect(Player)){
                        if(l.evil) {
                            returnToBeginning = true;
                        }
                        b = true;
                    }
                }
            }
        }
        return b;
    }
    boolean hitsWall(){
        boolean b = false;
        for (int i = 0; i < WorldLength; i++) {
            for (int j = 0; j < WorldWidth; j++) {
                if (Player.intersect(tiles[i][j]) && tiles[i][j].getType() == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
