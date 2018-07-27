import java.util.ArrayList;
import java.util.List;

public class FlyingPolice extends Entity {

    private List<FlyingPolice> flyingPolice;

    private int POLICE_SPEED = 2;
    private int[][] spriteSheetCoords = {{0,0,64,20},
                                        {64,0,64,20},
                                        {128,0,64,20},
                                        {192,0,64,20}};

    public FlyingPolice(int x, int y) {
        super(x, y);

        initFlyingPolice();
    }

    private void initFlyingPolice() {

        flyingPolice = new ArrayList<>();

        loadImage("src/resources/flyingPolice.png");
        image = img.getSubimage(spriteSheetCoords[0][0], spriteSheetCoords[0][1], spriteSheetCoords[0][2], spriteSheetCoords[0][3]);
        getImageDimensions();

        x = 1005;
        y = 400;

        delay = 200;

    }

    public void newFlyingPolice(){
        flyingPolice.add(new FlyingPolice(x, y));
    }

    public void moveFlying() {
        image = img.getSubimage(spriteSheetCoords[i][0], spriteSheetCoords[i][1], spriteSheetCoords[i][2], spriteSheetCoords[i][3]);

        if (x < -50) {
            visible = false;
        }

        x -= POLICE_SPEED;
    }

    public List<FlyingPolice> getFlyingPolices() {
        return flyingPolice;
    }

}


