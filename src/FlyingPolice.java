import java.util.ArrayList;
import java.util.List;

public class FlyingPolice extends Entity {

    private List<FlyingPolice> flyingPolice;

    private int POLICE_SPEED = 2;

    public FlyingPolice(int x, int y) {
        super(x, y);

        initFlyingPolice();
    }

    private void initFlyingPolice() {

        flyingPolice = new ArrayList<>();

        loadImage("src/resources/flyingPolice.png");
        getImageDimensions();

        x = 1005;
        y = 400;

    }

    public void newFlyingPolice(){
        flyingPolice.add(new FlyingPolice(x, y));
    }

    public void moveFlying() {
        if (x < -50) {
            visible = false;
        }
        x -= POLICE_SPEED;
    }

    public List<FlyingPolice> getFlyingPolices() {
        return flyingPolice;
    }

}


