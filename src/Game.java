import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


class Fire {
    private int x;
    private int y;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
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
}

public class Game extends JPanel implements KeyListener, ActionListener {
    private int passingTime = 0;
    private int wastedFire = 0;
    private BufferedImage bufferedImage;
    ArrayList<Fire> fires = new ArrayList<Fire>();
    private int firedirY = 5;
    private int ballX = 350;
    private int balldirX = 7;
    private int spaceShipX = 350;
    private int dirSpaceShipX = 20;
    private final int DELAY = 5;
    Timer timer = new Timer(DELAY, this);

    @Override
    public int getWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    public Game() {
        timer.start();
        try {
            bufferedImage = ImageIO.read(new FileImageInputStream(new File("uzayGemisi.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBackground(Color.BLACK);
    }

    public boolean control(){
        for(Fire fire : fires){
            if(new Rectangle(fire.getX(),fire.getY(),10,20).intersects(new Rectangle(ballX,0,20,20))){
                return true;
            }
        }
        return false;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.passingTime +=5;
        g.setColor(Color.red);
        g.fillOval(ballX, 0, 20, 20);
        g.drawImage(bufferedImage, spaceShipX, 480, bufferedImage.getWidth() / 15, bufferedImage.getHeight() / 15, this);
        for (Fire fire : fires) {
            if (fire.getY() < 0) {
                fires.remove(fire);
            }
        }
        g.setColor(Color.ORANGE);
        for (Fire fire : fires) {
            g.fillRect(fire.getX(), fire.getY(), 10, 20);
        }
        if (control()){
            timer.stop();
            String message= "Kazandınız ...\n" +
                    "Harcanan Ateş : " + wastedFire +
                    "\n Geçen Süre : " + passingTime / 1000.0 + " saniye . ";
            JOptionPane.showMessageDialog(this,message);
            System.exit(0);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (Fire fire : fires) {
            fire.setY(fire.getY() - firedirY);
        }
        ballX += balldirX;
        if (ballX >= getWidth()) {
            balldirX = -balldirX;
        }
        if (ballX <= 0) {
            balldirX = -balldirX;
        }
        repaint();

    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            if (spaceShipX <= 0) {
                spaceShipX = 0;
            } else {
                spaceShipX -= dirSpaceShipX;
            }
        } else if (keyCode == KeyEvent.VK_D ) {
            if (spaceShipX >= 730) {
                spaceShipX = 730;
            } else {
                spaceShipX += dirSpaceShipX;
            }
        } else if (keyCode == KeyEvent.VK_SPACE ) {
            fires.add(new Fire(spaceShipX + 18, 470));
            wastedFire++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
