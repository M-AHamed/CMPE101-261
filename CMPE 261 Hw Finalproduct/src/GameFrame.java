import javax.swing.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    GamePanel gamePanel =  new GamePanel();

    GameFrame(){

        this.setTitle("Ball Game"); // title of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gamePanel); // adding the game panel
        gamePanel.addMouseListener(new MouseAdapter() { // adding mouse listener to the panel
            // method to click on ball
            @Override
            public void mousePressed (MouseEvent e){
                if((e.getX() >= gamePanel.ball1.x-50 && e.getX() <= gamePanel.ball1.x+50) && (e.getY() >= gamePanel.ball1.y-50 && e.getY() <= gamePanel.ball1.y+50)){ // if user has clicked on location of the ball
                    System.out.println("you have clicked on the ball");
                    ballClick();
                } // if user has clicked on ball call the ballClick method

                // conditions to know if user clicked on any of the droplets
                // for droplet 1
                if((e.getX() >= gamePanel.droplet1.dropletx-50 && e.getX() <= gamePanel.droplet1.dropletx+50) && (e.getY() >= gamePanel.droplet1.droplety-50 && e.getY() <= gamePanel.droplet1.droplety+50)){ // if user has clicked on location of the ball
                    dropletClick(); // call the droplet click method
                    gamePanel.droplet1Destroyed =true; // sets the droplet destroyed to true. So the droplet dissapers

                }
                // for droplet 2
                if((e.getX() >= gamePanel.droplet2.dropletx-50 && e.getX() <= gamePanel.droplet2.dropletx+50) && (e.getY() >= gamePanel.droplet2.droplety-50 && e.getY() <= gamePanel.droplet2.droplety+50)){ // if user has clicked on location of the ball
                    dropletClick();
                    gamePanel.droplet2Destroyed =true;
                }
                // for droplet 3
                if((e.getX() >= gamePanel.droplet3.dropletx-50 && e.getX() <= gamePanel.droplet3.dropletx+50) && (e.getY() >= gamePanel.droplet3.droplety-50 && e.getY() <= gamePanel.droplet3.droplety+50)){ // if user has clicked on location of the ball
                    dropletClick();
                    gamePanel.droplet3Destroyed =true;
                }
                // for droplet 4
                if((e.getX() >= gamePanel.droplet4.dropletx-50 && e.getX() <= gamePanel.droplet4.dropletx+50) && (e.getY() >= gamePanel.droplet4.droplety-50 && e.getY() <= gamePanel.droplet4.droplety+50)){ // if user has clicked on location of the ball
                    dropletClick();
                    gamePanel.droplet4Destroyed =true;
                }
            }
        });

        this.pack();
        this.setLocationRelativeTo(null); // centers the location of the frame relative to the screen
        this.setResizable(false); // doesn't allow user to change size of panel
        setVisible(true); // sets the frame as visible
    }


    // ball click method
    public void ballClick(){
        gamePanel.life.ballClickedLifeIncrement(); // calls the life increment method in the life class

    }
    // DropletClick method
    public void dropletClick(){
        gamePanel.life.dropletClickedLifeIncrement(); // calls the method dropletClicked from the life class

    }

}
