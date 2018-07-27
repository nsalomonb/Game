import java.awt.*;
import javax.swing.*;

public class Game extends JFrame{

    private JPanel panel = new JPanel();

    public static void main(String[] args){
        new Game();
    }
    public Game(){
        setupWindow();
        this.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(0,1));
        panel.add(new Panel());
        this.setVisible(true);
    }

    public void setupWindow(){
        this.setTitle("Game");
        this.setSize(1000,610);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
}