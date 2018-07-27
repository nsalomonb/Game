import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Powerup extends Entity {

    private List<Powerup> powerup;

    private int POWERUP_SPEED = 1;

    public Powerup(int x, int y) {
        super(x, y);

        initPowerup();
    }

    private void initPowerup() {

        powerup = new ArrayList<>();

        loadImage("src/resources/powerup.png");
        getImageDimensions();

        x = 1005;
        y = 400;

    }

    public void newPowerup(){
        powerup.add(new Powerup(x, y));
    }

    public void move() {
        x -= POWERUP_SPEED;

        if (x < -50) {
            visible = false;
        }
    }

    public List<Powerup> getPowerups() {
        return powerup;
    }

}


