import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

    private List<Projectile> projectile;
    private long lastShot = System.currentTimeMillis();
    private final long threshold = 500;
    private int health = 100;
    private int score;
    private int shots;
    private int ammo;

    private int playerGround;
    private float vDelta;
    private float rbDelta;
    private float rbDegDelta;
    private float gDelta;
    private boolean bounce = false;

    private boolean debug;

    private Police police;
    private Powerup powerup;

    public Player(int x, int y) {
        super(x, y);

        initPlayer();
    }

    private void initPlayer() {

        projectile = new ArrayList<>();

        police = new Police(1005, 450);
        powerup = new Powerup(1005, 400);

        loadImage("src/resources/1.png");
        getImageDimensions();

        score = 0;
        shots = 0;
        ammo = 5;

        playerGround = 450;
        vDelta = 0;
        rbDegDelta = 8f;
        gDelta = 0.25f;
    }

    public void move() {

        movingSprites();

        if (x < 10) {
            x += 1;
        }

        if (bounce) {
            y += vDelta;
            vDelta += gDelta;
            if (y + 5 >= playerGround) {
                y = playerGround - 5;
                if (rbDelta >= 0) {
                    bounce = false;
                    y = playerGround;
                } else {
                    rbDelta += rbDegDelta;
                    vDelta = rbDelta;
                }
            }
        }
    }

    public void movingSprites(){}

    public List<Projectile> getProjectiles() {
        return projectile;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (!dead) {
            if (key == KeyEvent.VK_RIGHT) {
                long now = System.currentTimeMillis();
                if(ammo > 0) {
                    if (now - lastShot > threshold) {
                        fire();
                        lastShot = now;
                        shots += 1;
                        ammo -= 1;
                    }
                }
            }
            if (key == KeyEvent.VK_UP) {
                if (y == playerGround) {
                    vDelta = -8;
                    rbDelta = vDelta;
                    bounce = true;
                }
            }
            if (key == KeyEvent.VK_P) {
                if (paused) {
                    paused = false;
                } else if (!paused) {
                    paused = true;
                }
            }
            if (key == KeyEvent.VK_D) {
                if (debug) {
                    debug = false;
                } else {
                    debug = true;
                }
            }
        }
    }

    public boolean getDebug(){
        return debug;
    }

    public void fire() {
        projectile.add(new Projectile(x + width, y + height / 2));
    }

    public int getScore() {
        return score;
    }

    public int getShots() { return shots; }

    public int getAmmo() { return ammo; }

    public int getHealth() { return health; }

    public void setScore(int score) {this.score = score; }

    public void setAmmo(int ammo) {this.ammo = ammo; }

    public void setHealth(int health){ this.health = health; }

    //Unused.
    public void keyReleased(KeyEvent e) { int key = e.getKeyCode(); }

}


