import java.util.ArrayList;
import java.util.List;

public class FastPolice extends Entity {

    private List<FastPolice> fastPolice;

    private int POLICE_SPEED = 4;
    private int[][] spriteSheetCoords = {{0,0,32,64},
                                        {32,0,32,64},
                                        {64,0,32,64},
                                        {96,0,32,64}};

    public FastPolice(int x, int y) {
        super(x, y);

        initFastPolice();
    }

    private void initFastPolice() {

        fastPolice = new ArrayList<>();

        loadImage("src/resources/fastPolice.png");
        image = img.getSubimage(spriteSheetCoords[0][0], spriteSheetCoords[0][1], spriteSheetCoords[0][2], spriteSheetCoords[0][3]);
        getImageDimensions();

        x = 1005;
        y = 450;

        delay = 100;

    }

    public void newFastPolice(){
        fastPolice.add(new FastPolice(x, y));
    }

    public void moveFast() {
        image = img.getSubimage(spriteSheetCoords[i][0], spriteSheetCoords[i][1], spriteSheetCoords[i][2], spriteSheetCoords[i][3]);

        if (x < -50) {
            visible = false;
        }

        x -= POLICE_SPEED;
    }

    public List<FastPolice> getFastPolices() { return fastPolice; }

}


