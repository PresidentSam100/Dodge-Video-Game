import java.io.IOException;
public class DodgeGame {
    DodgeMap Map;
    int currentLevel;
    int lives = 5;

    public DodgeGame() throws IOException {
        currentLevel = 1;
        Map = new DodgeMap();
        System.out.println("Currently: " + currentLevel);
    }

    public boolean win() throws IOException {
        return currentLevel > 5;
    }

    public boolean lose() {
        return lives <= 0;
    }

    public int getLevel() {
        return currentLevel;
    }

    public void update() throws IOException {
        if(win())
            return;
        Map.update();
        if(Map.returnToBeginning)
        {
            currentLevel = 1;
            Map.returnToBeginning = false;
        }
    }
    public void refresh() throws IOException {
        currentLevel = 1;
        Map = new DodgeMap();
        lives = 5;
    }
}
