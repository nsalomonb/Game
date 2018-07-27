import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Powerup extends Entity {

    private List<Powerup> powerup;

    private int POWERUP_SPEED = 1;
    private int[][] spriteSheetCoords = {{0,0,19,19},
                                        {19,0,19,19},
                                        {38,0,19,19},
                                        {57,0,19,19}};

    public Powerup(int x, int y) {
        super(x, y);

        initPowerup();
    }

    private void initPowerup() {

        powerup = new ArrayList<>();

        loadImage("src/resources/powerup.png");
        image = img.getSubimage(spriteSheetCoords[0][0], spriteSheetCoords[0][1], spriteSheetCoords[0][2], spriteSheetCoords[0][3]);
        getImageDimensions();

        x = 1005;
        y = 400;

        delay = 250;

    }

    public void newPowerup(){
        powerup.add(new Powerup(x, y));
    }

    public void move() {
        image = img.getSubimage(spriteSheetCoords[i][0], spriteSheetCoords[i][1], spriteSheetCoords[i][2], spriteSheetCoords[i][3]);

        x -= POWERUP_SPEED;

        if (x < -50) {
            visible = false;
        }
    }

    public List<Powerup> getPowerups() {
        return powerup;
    }

}


