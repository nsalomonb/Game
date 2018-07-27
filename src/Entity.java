import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Entity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected boolean paused;
    protected boolean dead;
    protected boolean damageCollision;
    protected boolean itemCollision;
    protected Image image;
    protected BufferedImage img;
    protected Timer timer;
    protected int counter = 0;
    protected int delay;
    protected int i = counter;

    public Entity(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
        paused = false;

        timer = new Timer(delay, action);
        timer.setInitialDelay(0);
        timer.start();

    }

    protected void loadImage(String imageName) {
        try {
            img = ImageIO.read(new File(imageName));
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() { return height; }

    public int getWidth() { return width; }

    public int getMaxX() { return x + width; }

    public int getMaxY() { return y + height; }

    public boolean isPaused() { return paused; }

    public boolean isDead() { return dead; }

    public boolean isVisible() {
        return visible;
    }

    public boolean isColliding_Damage() { return damageCollision; }

    public boolean isColliding_Item() { return itemCollision; }

    public void setPaused(Boolean paused) { this.paused = paused; }

    public void setDead(Boolean dead) { this.dead = dead; }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void setDamageCollision(Boolean damageCollision) { this.damageCollision = damageCollision; }

    public void setItemCollision(Boolean itemCollision) { this.itemCollision = itemCollision; }

    ActionListener action = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if(i == 3)
            {
                timer.stop();
                i = counter;
                timer = new Timer(delay, action);
                timer.setInitialDelay(0);
                timer.start();
            }
            else
            {
                i++;
            }
        }
    };
}