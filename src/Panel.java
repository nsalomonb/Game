import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {

    private final int PLAYER_X = -50;
    private final int PLAYER_Y = 450;
    private final int SPAWN_POINT = 1005;
    private final int POWERUP_DEFAULT_Y = 400;
    private final int BACKGROUND_X = 0;
    private final int BACKGROUND_START = 0;
    private final int DELAY = 10;
    private Timer timer;
    private Player player;
    private Police police;
    private FastPolice fastPolice;
    private FlyingPolice flyingPolice;
    private Powerup powerup;
    private Background background;

    private int policeSpawnValue = 200;
    private int policeLower = 100;
    private int policeHigher = 400;

    private int fastPoliceSpawnValue = 650;
    private int fastPoliceLower = 300;
    private int fastPoliceHigher = 800;

    private int flyingPoliceSpawnValue = 800;
    private int flyingPoliceLower = 500;
    private int flyingPoliceHigher = 1000;

    private int powerupSpawnValue = 100;
    private int powerupLower = 1500;
    private int powerupHigher = 2000;

    private int playerHealth;
    private int playerScore;
    private int playerAmmo;

    private boolean debug = false;

    public Panel() {
        initPanel();
    }

    private void initPanel() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        player = new Player(PLAYER_X, PLAYER_Y);
        background = new Background(BACKGROUND_X, BACKGROUND_START);
        police = new Police(SPAWN_POINT, PLAYER_Y);
        fastPolice = new FastPolice(SPAWN_POINT, PLAYER_Y);
        flyingPolice = new FlyingPolice(SPAWN_POINT, POWERUP_DEFAULT_Y);
        powerup = new Powerup(SPAWN_POINT, POWERUP_DEFAULT_Y);

        timer = new Timer(DELAY, this);
        timer.start();

        playerHealth = player.getHealth();
        playerScore = player.getScore();
        playerAmmo = player.getAmmo();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        //Define Fonts
        Font sansFont = new Font("Sans", Font.BOLD, 12);
        Font bigFont = sansFont.deriveFont(50F);

        //Draw Backgrounds
        g.drawImage(background.getImage(), background.getBackground1(), 0, this);
        g.drawImage(background.getImage(), background.getBackground2(), 0, this);

        //Draw Score & Ammo(Disappears if dead)
        if(!player.isDead()) {
            g.setFont(bigFont);
            g.setColor(Color.RED);
            g.drawString("" + player.getScore(), 10, 50);
            g.setColor(Color.BLACK);
            g.drawString("" + player.getAmmo(), 10, 100);
        }

        //Define Lists
        List<Projectile> projectiles = player.getProjectiles();
        List<Police> polices = police.getPolices();
        List<Powerup> powerups = powerup.getPowerups();
        List<FastPolice> fastPolices = fastPolice.getFastPolices();
        List<FlyingPolice> flyingPolices = flyingPolice.getFlyingPolices();

        //Draw Police
        for(Police police : polices){
            g2d.drawImage(police.getImage(), police.getX(), police.getY(), this);
        }

        //Draw Fast Police
        for(FastPolice fastPolice : fastPolices){
            g2d.drawImage(fastPolice.getImage(), fastPolice.getX(), fastPolice.getY(), this);
        }

        //Draw Flying Police
        for(FlyingPolice flyingPolice : flyingPolices){
            g2d.drawImage(flyingPolice.getImage(), flyingPolice.getX(), flyingPolice.getY(), this);
        }

        //Draw Projectiles
        for(Projectile projectile : projectiles){
            g2d.drawImage(projectile.getImage(), projectile.getX(), projectile.getY(), this);
        }

        //Draw PowerUps
        for(Powerup powerup : powerups){
            g2d.drawImage(powerup.getImage(), powerup.getX(), powerup.getY(), this);
        }

        //Paused Screen
        if(player.isPaused()){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 5 * 0.1f));
            g.setColor(Color.GRAY);
            g2d.fillRect(0, 0, 1000, 600);

            g.setFont(bigFont);
            g.setColor(Color.BLACK);
            g.drawString("PAUSED", 400, 300);
        }

        //Debug Screen (Accessed with 'D' Key)
        if(player.getDebug()) {
            g.setFont(sansFont);
            g.setColor(Color.RED);
            g.drawString("Background 1: " + background.getBackground1(), 850, 20);
            g.drawString("Background 2: " + background.getBackground2(), 850, 40);
            g.drawString("Police Visible: " + polices.size() + " " + fastPolices.size() + " " + flyingPolices.size() , 850, 60);
            g.drawString("PowerUp Visible: " + powerups.size(), 850, 80);
            g.drawString("Shots: " + player.getShots(), 850, 100);
            g.drawString("Health: " + player.getHealth(), 850, 120);
            g.drawString("Slow Police SV: " + policeSpawnValue, 850, 140);
            g.drawString("Fast Police SV: " + fastPoliceSpawnValue, 850, 160);
            g.drawString("Flying Police SV: " + flyingPoliceSpawnValue, 850, 180);
            g.drawString("PowerUp SV: " + powerupSpawnValue, 850, 200);

            g.drawString("X: " + (background.getX() * -1) + " | Y: "+ player.getY(), player.getX(), (player.getY() - 10));
            g.drawRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());

            for(Projectile projectile : projectiles){
                g.drawString("X: " + projectile.getX() + " | Y: " + projectile.getY(), projectile.getX(), (projectile.getY() - 10));
                g.drawRect(projectile.getX(), projectile.getY(), projectile.getWidth(), projectile.getHeight());
            }

            for(Police police : polices){
                g.drawString("X: " + police.getX() + " | Y: " + police.getY(), police.getX(), (police.getY() - 10));
                g.drawRect(police.getX(), police.getY(), police.getWidth(), police.getHeight());
            }

            for(FastPolice fastPolice : fastPolices){
                g.drawString("X: " + fastPolice.getX() + " | Y: " + fastPolice.getY(), fastPolice.getX(), (fastPolice.getY() - 10));
                g.drawRect(fastPolice.getX(), fastPolice.getY(), fastPolice.getWidth(), fastPolice.getHeight());
            }

            for(FlyingPolice flyingPolice : flyingPolices){
                g.drawString("X: " + flyingPolice.getX() + " | Y: " + flyingPolice.getY(), flyingPolice.getX(), (flyingPolice.getY() - 10));
                g.drawRect(flyingPolice.getX(), flyingPolice.getY(), flyingPolice.getWidth(), flyingPolice.getHeight());
            }

            for(Powerup powerup : powerups){
                g.drawString("X: " + powerup.getX() + " | Y: " + powerup.getY(), powerup.getX(), (powerup.getY() - 10));
                g.drawRect(powerup.getX(), powerup.getY(), powerup.getWidth(), powerup.getHeight());
            }
        }

        //Draw Player
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);

        //Dead Screen
        if(player.isDead()){
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 5 * 0.1f));
            g.setColor(Color.RED);
            g2d.fillRect(0, 0, 1000, 600);

            g.setFont(bigFont);
            g.setColor(Color.BLACK);
            g.drawString("YOU DIED", 400, 300);
            g.setFont(sansFont);
            g.drawString("After " + player.getShots() + " shots you managed to neutralize " + player.getScore() + " enemies.", 370, 320);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!player.isPaused() && !player.isDead()) {
            updateProjectiles();
            updateSlowPolice();
            updateFastPolice();
            updateFlyingPolice();
            updatePowerups();
            updatePlayer();
            updateBackground();
            updateCollision();
        }
        repaint();
    }

    private void updateProjectiles() {
        List<Projectile> projectiles = player.getProjectiles();

        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);

            if (projectile.isVisible()) {
                projectile.move();
            } else {
                projectiles.remove(i);
            }
        }
    }

    private void updateSlowPolice() {
        Random rand = new Random();
        List<Police> polices = police.getPolices();

        if((background.getX() * -1) == policeSpawnValue){
            police.newPolice();
            policeSpawnValue += rand.nextInt(policeHigher) + policeLower;
        }

        for (int i = 0; i < polices.size(); i++) {
            Police police = polices.get(i);

            if (police.isVisible()) {
                police.moveSlow();

                if((police.getX() < player.getMaxX()) && (police.getMaxX() > player.getX()) && player.getMaxY() > police.getY()){
                    updateHealth();
                }
            } else {
                polices.remove(i);
            }
        }
    }

    private void updateFastPolice() {
        Random rand = new Random();
        List<FastPolice> fastPolices = fastPolice.getFastPolices();

        if((background.getX() * -1) == fastPoliceSpawnValue){
            fastPolice.newFastPolice();
            fastPoliceSpawnValue += rand.nextInt(fastPoliceHigher) + fastPoliceLower;
        }

        for (int i = 0; i < fastPolices.size(); i++) {
            FastPolice fastPolice = fastPolices.get(i);

            if (fastPolice.isVisible()) {
                fastPolice.moveFast();

                if((fastPolice.getX() < player.getMaxX()) && (fastPolice.getMaxX() > player.getX()) && player.getMaxY() > fastPolice.getY()){
                    updateHealth();
                }
            } else {
                fastPolices.remove(i);
            }
        }
    }

    private void updateFlyingPolice() {
        Random rand = new Random();
        List<FlyingPolice> flyingPolices = flyingPolice.getFlyingPolices();

        if((background.getX() * -1) == flyingPoliceSpawnValue){
            flyingPolice.newFlyingPolice();
            flyingPoliceSpawnValue += rand.nextInt(flyingPoliceHigher) + flyingPoliceLower;
        }

        for (int i = 0; i < flyingPolices.size(); i++) {
            FlyingPolice flyingPolice = flyingPolices.get(i);

            if (flyingPolice.isVisible()) {
                flyingPolice.moveFlying();

                if((flyingPolice.getX() < player.getMaxX()) && (flyingPolice.getMaxX() > player.getX())
                        && player.getMaxY() > flyingPolice.getY() && player.getY() < flyingPolice.getMaxY()){
                            updateHealth();
                }
            } else {
                flyingPolices.remove(i);
            }
        }
    }

    private void updatePowerups() {
        Random rand = new Random();
        List<Powerup> powerups = powerup.getPowerups();

        if((background.getX() * -1) == powerupSpawnValue){
            powerup.newPowerup();
            powerupSpawnValue += rand.nextInt(powerupHigher) + powerupLower;
        }

        for (int i = 0; i < powerups.size(); i++) {
            Powerup powerup = powerups.get(i);

            if (powerup.isVisible()) {
                powerup.move();

                if(player.getMaxX() > powerup.getX() && player.getMaxY() > powerup.getY() && player.getY() < powerup.getMaxY()){
                    powerup.setVisible(false);
                    playerAmmo += 5;
                    playerHealth += 25;

                    player.setHealth(playerHealth);
                    player.setAmmo(playerAmmo);

                    if(player.getHealth() > 100){
                        player.setHealth(100);
                    }
                }
            } else {
                powerups.remove(i);
            }
        }
    }

    private void updatePlayer() { player.move(); }

    private void updateBackground(){
        background.move();
    }

    private void updateCollision() {
        List<Projectile> projectiles = player.getProjectiles();
        List<Police> polices = police.getPolices();
        List<FastPolice> fastPolices = fastPolice.getFastPolices();
        List<FlyingPolice> flyingPolices = flyingPolice.getFlyingPolices();

        for (int i = 0; i < projectiles.size(); i++) {
            for (int j = 0; j < polices.size(); j++){
                Projectile projectile = projectiles.get(i);
                Police police = polices.get(j);

                if(projectile.isVisible()) {
                    if (projectile.getX() > police.getX() && projectile.getMaxY() > police.getY()) {
                        projectile.setVisible(false);
                        police.setVisible(false);

                        playerScore += 1;

                        player.setScore(playerScore);
                    }
                }
            }
        }

        for (int i = 0; i < projectiles.size(); i++) {
            for (int j = 0; j < fastPolices.size(); j++){
                Projectile projectile = projectiles.get(i);
                FastPolice fastPolice = fastPolices.get(j);

                if(projectile.isVisible()) {
                    if (projectile.getX() > fastPolice.getX() && projectile.getMaxY() > fastPolice.getY()) {
                        projectile.setVisible(false);
                        fastPolice.setVisible(false);

                        playerScore += 1;

                        player.setScore(playerScore);
                    }
                }
            }
        }

        for (int i = 0; i < projectiles.size(); i++) {
            for (int j = 0; j < flyingPolices.size(); j++){
                Projectile projectile = projectiles.get(i);
                FlyingPolice flyingPolice = flyingPolices.get(j);

                if(projectile.isVisible()) {
                    if (projectile.getX() > flyingPolice.getX() && projectile.getY() < flyingPolice.getMaxY()
                            && projectile.getMaxY() > flyingPolice.getY()) {
                        projectile.setVisible(false);
                        flyingPolice.setVisible(false);

                        playerScore += 1;

                        player.setScore(playerScore);
                    }
                }
            }
        }
    }

    private void updateHealth(){
        playerHealth -= 1;

        player.setHealth(playerHealth);

        if(player.getHealth() == 0){
            player.setDead(true);
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}