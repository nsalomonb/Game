public class Background extends Entity {

    private int bg1;
    private int bg2;
    private int minX;

    public Background(int x, int y) {
        super(x, y);
        initBackground();
    }

    private void initBackground() {

        loadImage("src/resources/Background.png");
        getImageDimensions();
        minX = width - (width *2);
        bg1 = 0;
        bg2 = width;
    }

    public void move() {
        x -= 1;
        if(bg1 > minX){
            bg1 -= 1;
        }else if(bg1 == minX){
            bg1 = width - 1;
        }
        if(bg2 > minX){
            bg2 -= 1;
        }else if(bg2 == minX){
            bg2 = width - 1;
        }
    }

    public int getBackground1() { return bg1;}
    public int getBackground2(){
        return bg2;
    }
}
