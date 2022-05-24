import javax.swing.*;
import java.awt.*;

public class Droplet {
    // ball location:
    public int dropletx = 200;
    public int droplety = 100;
    public int dropletVelocity = 4;

    Image dropletImage;
    // class constructor
   public Droplet() {
        // initialising the image of the droplet
        dropletImage = new ImageIcon("rightImage.png").getImage();
//        dropletImage.setBorder(new EmptyBorder(0,0,0,0));
    }
    // to change the place of spawn of the droplet on the x axis

}

//
