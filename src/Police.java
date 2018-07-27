import java.util.ArrayList;
import java.util.List;

public class Police extends Entity {

    private List<Police> police;

    private int POLICE_SPEED = 2;

    public Police(int x, int y) {
        super(x, y);

        initPolice();
    }

    private void initPolice() {

        police = new ArrayList<>();

        loadImage("src/resources/police.png");
        getImageDimensions();

        x = 1005;
        y = 450;

    }

    public void newPolice(){
        police.add(new Police(x, y));
    }

    public void moveSlow() {
        if (x < -50) {
            visible = false;
        }
        x -= POLICE_SPEED;
    }

    public List<Police> getPolices() {
        return police;
    }

}


