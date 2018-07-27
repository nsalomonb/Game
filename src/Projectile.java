public class Projectile extends Entity {

    private final int BOARD_WIDTH = 1000;
    private final int PROJECTILE_SPEED = 2;

    private int[][] spriteSheetCoords = {{0,0,10,7},
                                        {10,0,10,7},
                                        {20,0,10,7},
                                        {30,0,10,7}};

    public Projectile(int x, int y) {
        super(x, y);

        initProjectile();
    }

    private void initProjectile() {

        loadImage("src/resources/bill.png");
        image = img.getSubimage(spriteSheetCoords[0][0], spriteSheetCoords[0][1], spriteSheetCoords[0][2], spriteSheetCoords[0][3]);
        getImageDimensions();

        delay = 100;

    }

    public void move() {
            image = img.getSubimage(spriteSheetCoords[i][0], spriteSheetCoords[i][1], spriteSheetCoords[i][2], spriteSheetCoords[i][3]);

        x += PROJECTILE_SPEED;

        if (x > BOARD_WIDTH) {
            visible = false;
        }
        if(x < 0){
            visible = false;
        }
    }
}