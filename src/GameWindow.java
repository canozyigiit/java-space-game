import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public GameWindow(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow("Space Game");
        gameWindow.setResizable(false);
        gameWindow.setFocusable(false);
        gameWindow.setSize(800, 600);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();
        game.requestFocus();
        game.addKeyListener(game);
        game.setFocusable(true);// odağı JPanele veriyoruz
        game.setFocusTraversalKeysEnabled(false);

        gameWindow.add(game);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    }
}
