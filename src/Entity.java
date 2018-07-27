import java.awt.Image;
import javax.swing.ImageIcon;

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

    public Entity(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
        paused = false;
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
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
}