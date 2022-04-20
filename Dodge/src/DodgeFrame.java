import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class DodgeFrame extends JFrame implements KeyListener {
    public DodgeFrame(String title) throws IOException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        DodgePanel p = new DodgePanel();
        Insets insets = getInsets();
        int width = p.getWidth()+insets.left+insets.right;
        int height = p.getHeight() + insets.top+ insets.bottom;
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        add(p);
        pack();
        setVisible(true);
        while (true)
        {
            Thread t = new Thread(p);
            t.start();
            try {
                if (p.d.Map.winLevel() && p.d.currentLevel != 6) {
                    p.d.currentLevel++;
                    p.d.lives++;
                    p.d.Map.ScanLevel(p.d.currentLevel);
                    System.out.println("WON New Level: "+p.d.currentLevel);
                }
                if (p.d.Map.isHit()) {
                    p.d.update();
                    p.d.Map.ScanLevel(p.d.currentLevel);
                    p.d.lives--;
                }
                p.run();
                Thread.sleep(p.msBetween);
            } catch(Exception e) {
                System.out.println("THREAD DIDNT WORK "+e.toString());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}