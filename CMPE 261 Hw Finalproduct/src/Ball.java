import javax.swing.*;
import java.awt.*;

public class Ball {

    // ball location
   public int y = 100;
   public int x = 0;
   // ball velocity
   public int xVelocity = 2;
   public int yVelocity = 4;
   // ball image
    Image BallImage;

  public  Ball(int x, int y){
        // save the ball image into the ball image object
        BallImage = new ImageIcon("ball-transparent-background-png-image-101577037157edahcplbm6.png").getImage();
    }
}
