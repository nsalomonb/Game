import java.util.ArrayList;
import java.util.List;

public class FastPolice extends Entity {

    private List<FastPolice> fastPolice;

    private int POLICE_SPEED = 4;

    public FastPolice(int x, int y) {
        super(x, y);

        initFastPolice();
    }

    private void initFastPolice() {

        fastPolice = new ArrayList<>();

        loadImage("src/resources/fastPolice.png");
        getImageDimensions();

        x = 1005;
        y = 450;

    }

    public void newFastPolice(){
        fastPolice.add(new FastPolice(x, y));
    }

    public void moveFast() {
        if (x < -50) {
            visible = false;
        }
        x -= POLICE_SPEED;
    }

    public List<FastPolice> getFastPolices() { return fastPolice; }

}


