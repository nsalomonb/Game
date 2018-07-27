import javax.swing.*;
import java.awt.*;

public class Background{

    private int bg1;
    private int bg2;
    private int x;
    private int width;
    private int height;
    private int minX;
    private Image image;

    public Background(int x, int y) {
        initBackground();
    }

    private void initBackground() {

        ImageIcon ii = new ImageIcon("src/resources/Background.png");
        image = ii.getImage();
        getImageDimensions();
        minX = width - (width *2);
        bg1 = 0;
        bg2 = width;
    }

    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public void move() {
        x -= 1;
        if(bg1 > minX){
            bg1 -= 1;
        }else if(bg1 == minX){
            bg1 = width - 1;
        }
        if(bg2 > minX){
            bg2 -= 1;
        }else if(bg2 == minX){
            bg2 = width - 1;
        }
    }

    public int getBackground1() { return bg1;}
    public int getBackground2(){
        return bg2;
    }
    public int getX() { return x; }
    public Image getImage() { return image; }

}
