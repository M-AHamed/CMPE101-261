import java.awt.*;

public class Road implements Drawable {

    // declares how big the road ist
    private final int roadSize = 200;
    private final GuiMain guiMain;

    //      Constructor with reference to guiMain
//      takes in gui main as a parameter
    public Road(GuiMain guiMain) {
        this.guiMain = guiMain;
    }

    //      draws the road on the screen
    @Override
    public void draw(Graphics2D g) {
        // gets with and high of the screen
        int width = guiMain.getWidth();
        int height = guiMain.getHeight();

        // draws the roads
        g.setColor(Color.WHITE);
        g.fillRect(0, height / 2 - roadSize / 2, width, roadSize);
        g.fillRect(width / 2 - roadSize / 2, 0 / 2, roadSize, height);

        // draws the middle of the crossroad in yellow
        g.setColor(Color.YELLOW);
        g.fillRect(width / 2 - roadSize / 2, height / 2 - roadSize / 2, roadSize, roadSize);
    }

    public int getRoadSize() {
        return roadSize;
    }
}
