import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{
    int id;
    int yVelocity; // how fast the paddle moves up or down

    Paddle(int x, int y,int paddleWidth, int PaddleHeight, int id){
        super(x,y, paddleWidth, PaddleHeight);
        this.id=id;

    }
    public void keyPressed(KeyEvent e){}
    public void keyReleased(KeyEvent e){

    }
    public void setYDirection(int yDirection){

    }
    public void move(){

    }
    public void draw(Graphics g){
        if(id ==1){
            g.setColor(Color.blue);
        }
        else{
            g.setColor(Color.red);
        }
        g.fillRect(x,y,width,height);
    }

}
