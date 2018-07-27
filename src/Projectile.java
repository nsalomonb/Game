public class Projectile extends Entity {

    private final int BOARD_WIDTH = 1000;
    private final int PROJECTILE_SPEED = 2;

    public Projectile(int x, int y) {
        super(x, y);

        initProjectile();
    }

    private void initProjectile() {

        loadImage("src/resources/bill.png");
        getImageDimensions();
    }

    public void move() {

        x += PROJECTILE_SPEED;

        if (x > BOARD_WIDTH) {
            visible = false;
        }
        if(x < 0){
            visible = false;
        }
    }
}