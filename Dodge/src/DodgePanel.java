import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class DodgePanel extends JPanel implements KeyListener, Runnable {
    public int msBetween = 10, msLast = 10;
    private BufferedImage buffer;
    private int level = 0;
    private boolean success = false;
    DodgeGame d;
    char lastCharX=0, lastCharY = 0;
    boolean left, up, down, right;
    public DodgePanel() throws IOException {
        super();
        addKeyListener(this);
        setSize(700, 700);
        level = 0;
        this.d = new DodgeGame();
        buffer = new BufferedImage(700, 700, BufferedImage.TYPE_4BYTE_ABGR);
        left = false; up = false; down = false; right = false;
    }

    public void paint(Graphics g) {
        Graphics G = buffer.getGraphics();
        G.setColor(Color.black);
        G.fillRect(0, 0, 700, 700);
        Font f = new Font("Serif", Font.PLAIN, 20);
        G.setFont(f);
        if (d.Map.returnToBeginning) {
            d.Map.returnToBeginning = false;
            G.setColor(Color.white);
            G.drawString("RESET", 0, 0);
        }
        try {
            if (d.win()) {
                G.setColor(Color.white);
                G.drawString("YOU WIN!", 350, 350);
                g.drawImage(buffer, 0, 0, null);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        G.setColor(Color.white);
        if(d.lose())
        {
            G.drawString("YOU lose!", 350, 350);
            g.drawImage(buffer, 0, 0, null);
            return;
        }
        G.setColor(Color.WHITE);
        G.setColor(Color.CYAN);
        int y = d.Map.Player.getY();
        int x = d.Map.Player.getX();
        int offX = x - 300, offY = y - 300;
        ArrayList<DodgeTile> notBackGround = new ArrayList<>();
        for (int i = 0; i < d.Map.WorldLength; i++) {
            for (int j = 0; j < d.Map.WorldWidth; j++) {
                DodgeTile t = d.Map.getTile(i, j);
                if (t.getType() == 0) {
                    G.setColor(Color.BLACK);
                    G.fillRect(t.getX()-offX /*- px*/, t.getY()-offY /*- py*/, t.getLength(), t.getHeight());
                } else{
                    notBackGround.add(t);
                }
                if (t.getType() == 1) // wall
                {
                    G.setColor(Color.WHITE);
                }
                if (t.getType() == 2)// player
                {
                    G.setColor(Color.BLUE);
                }
                if (t.getType() == 3)// player exit
                {
                    G.setColor(Color.GREEN);
                }
                if (t.getType() == 4)// basic
                {
                    G.setColor(Color.YELLOW);
                }
                if (t.getType() == 5)// pattern obstacle
                {
                    G.setColor(Color.CYAN);
                }
                if (t.getType() == 6) // laser obstacle
                {
                    G.setColor(Color.ORANGE);
                }
            }
        }
        for (DodgeTile t : notBackGround){
            if (t.getType() == 1) // wall
            {
                G.setColor(Color.WHITE);
            }
            if (t.getType() == 2)// player
            {
                G.setColor(Color.BLUE);
            }
            if (t.getType() == 3)// player exit
            {
                G.setColor(Color.GREEN);
            }
            if (t.getType() == 4)// basic
            {
                G.setColor(Color.YELLOW);
            }
            if (t.getType() == 5)// pattern obstacle
            {
                G.setColor(Color.CYAN);
            }
            if (t.getType() == 6) {
                DodgeLaser temp = (DodgeLaser) t;
                Color laser = new Color(250, 0, 0);
                //draw charging box with increasing color
                if(temp.evil) {
                    laser = new Color(0,0, 250);
                }
                if (temp.getOn()) {
                    G.setColor(laser);
                    G.fillRect(temp.getEmitX1() - offX /*- px*/, temp.getEmitY1() - offY /*- py*/, temp.getemitLength(), temp.getemitHeight());
                    if (temp.getemitHeight()>temp.getemitLength()) {
                        G.fillRect(temp.getEmitX1() - offX+7 /*- px*/, temp.getEmitY1() - offY /*- py*/, temp.getemitLength(), temp.getemitHeight());
                    } else {
                        G.fillRect(temp.getEmitX1() - offX /*- px*/, temp.getEmitY1() - offY+7 /*- py*/, temp.getemitLength(), temp.getemitHeight());
                    }
                } else {
                    int a = 150 / temp.timer;
                    Color increaseRed = new Color(100 + temp.cooldown * a, 0, 0);
                    if(temp.evil) {
                        increaseRed = new Color(0, 0, 100 + temp.cooldown * a);
                    }
                    G.setColor(increaseRed);

                    if (temp.getemitHeight()>temp.getemitLength()) {
                        G.fillRect(t.getX() - offX+7/*- px*/, t.getY() - offY /*- py*/, t.getLength()*2, t.getHeight()*2);
                    } else {
                        G.fillRect(t.getX() - offX/*- px*/, t.getY() - offY+7 /*- py*/, t.getLength()*2, t.getHeight()*2);
                    }
                }
            } else {
                G.fillRect(t.getX() - offX /*- px*/, t.getY() - offY/*- py*/, t.getLength(), t.getHeight());
            }
        }
        DodgePlayer p = d.Map.getPlayer();
        G.setColor(Color.RED);
        G.drawString("Lives: "+d.lives, 600, 650);
        G.setColor(Color.white);
        G.fillRect(300, 300, p.getHeight(), p.getLength());
        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == 'R' || c == 'r') {
            try {
                reset();
            } catch (Exception x) {
                System.out.println(x.toString());
            }
        } else {
            if (c == 'a' || c == 'A') {
                left = true;
                if (right) right = false;
            }
            else if (c == 'd' || c == 'D') {
                right = true;
                if (left) left = false;
            }
            else if (c == 's' || c == 'S') {
                down = true;
                if (up) up = false;
            }
            else if(c =='W' || c =='w') {
                up = true;
                if (down) down = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if (c =='a' || c=='A') {
            left = false;
        }
        else if (c == 'd' || c == 'D') {
            right = false;
        }
        else if (c == 's' || c == 'S') {
            down = false;
        }
        else if (c == 'W' || c == 'w') {
            up = false;
        }
        // run player stop moving method through dodgegame->map->player
    }

    public void reset() throws IOException {
        d.refresh();
    }

    @Override
    public void run() {
        try {
            d.update();
            if (left) {
                d.Map.Player.moveDir('a');
                d.Map.Player.update();
                if(d.Map.hitsWall()) {
                    d.Map.Player.returnToPrev();
                }
            }
            if (right) {
                d.Map.Player.moveDir('d');
                d.Map.Player.update();
                if(d.Map.hitsWall()) {
                    d.Map.Player.returnToPrev();
                }
            }
            if (down) {
                d.Map.Player.moveDir('s');
                d.Map.Player.update();
                if(d.Map.hitsWall()) {
                    d.Map.Player.returnToPrev();
                }
            }
            if (up) {
                d.Map.Player.moveDir('w');
                d.Map.Player.update();
                if(d.Map.hitsWall()) {
                    d.Map.Player.returnToPrev();
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        repaint();
    }

    public void addNotify() {
        super.addNotify();
        this.setFocusable(true);
        requestFocus();
    }
}