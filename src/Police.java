import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Police extends Entity {

    private List<Police> police;

    private int[][] spriteSheetCoords = {{0,0,32,64},
                                        {32,0,32,64},
                                        {64,0,32,64},
                                        {96,0,32,64}};


    private int POLICE_SPEED = 2;

    public Police(int x, int y) {
        super(x, y);

        initPolice();
    }

    private void initPolice() {

        police = new ArrayList<>();

        loadImage("src/resources/police.png");
        image = img.getSubimage(spriteSheetCoords[0][0], spriteSheetCoords[0][1], spriteSheetCoords[0][2], spriteSheetCoords[0][3]);
        getImageDimensions();

        x = 1005;
        y = 450;

        delay = 200;

    }

    public void newPolice(){
        police.add(new Police(x, y));
    }

    public void moveSlow() {
        image = img.getSubimage(spriteSheetCoords[i][0], spriteSheetCoords[i][1], spriteSheetCoords[i][2], spriteSheetCoords[i][3]);

        if (x < -50) {
            visible = false;
        }

        x -= POLICE_SPEED;
    }

    public List<Police> getPolices() {
        return police;
    }

}


