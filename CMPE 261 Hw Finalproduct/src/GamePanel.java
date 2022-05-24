import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import static java.lang.Math.abs;


public class GamePanel extends JPanel implements ActionListener {



    // panel size:
    final int panel_width = 700;
    final int panel_height = 700;
    volatile boolean running = false; // sets the state of the game
    int delay = 50; // determines the timer of the game
    Timer timer;
    Ball ball1;
    Droplet droplet1;
    Droplet droplet2;
    Droplet droplet3;
    Droplet droplet4;

    boolean droplet1Destroyed = false;
    boolean droplet2Destroyed = false;
    boolean droplet3Destroyed = false;
    boolean droplet4Destroyed = false;

    boolean collision = false;
    int score = 5; // starting score of the player

    // life of player
    Life life = new Life();

    private JMenuBar menuBar;
    private JMenu menuGame, menuDebug;
    private JMenuItem menuItemStart, menuItemStop, menuItemPause, menuItemIncreaseBallSpeed, menuItemIncreaseDropletSpeed;

    // constructor
    GamePanel() {


        // menu bar and its items
        setLayout(new BorderLayout());
        menuBar = new JMenuBar();
        // adding the JMenu
        menuGame = new JMenu("Game");
        menuDebug = new JMenu("Debug");
        // adding the Jmenu Items
        menuItemStart = new JMenuItem("Start");
        menuItemPause = new JMenuItem("Pause");
        menuItemStop = new JMenuItem("Exit");
        menuItemIncreaseBallSpeed = new JMenuItem("Increase Ball Speed");
        menuItemIncreaseDropletSpeed = new JMenuItem("Increase Droplet Speed");
        // adding the actionListener to the menu items
        menuItemPause.addActionListener(this);
        menuItemStart.addActionListener(this);
        menuItemStop.addActionListener(this);
        menuItemIncreaseBallSpeed.addActionListener(this);
        menuItemIncreaseDropletSpeed.addActionListener(this);

        // adding the menuItems to Game
        menuGame.add(menuItemStart);
        menuGame.add(menuItemPause);
        menuGame.add(menuItemStop);
        // adding the menuItems to Debut
        menuDebug.add(menuItemIncreaseBallSpeed);
        menuDebug.add(menuItemIncreaseDropletSpeed);
        // setting the layout of the menuBar
        menuBar.setLayout(new GridLayout(1, 0));
        // adding the menus to menuBar
        menuBar.add(menuGame);
        menuBar.add(menuDebug);

        add(menuBar, BorderLayout.NORTH); // add the menu bar to application...

        newDroplet();
        newBall();

        // setting the panel size
        this.setPreferredSize(new Dimension(panel_width, panel_height));
        // for testing only setting the background of the game:
        this.setBackground(Color.white);
    }


    // to start the game
    public void startGame() {
        running = true;
        timer = new Timer(delay, this);
        timer.start();
    }


    public void newBall() {
        ball1 = new Ball(0, 150);
    }

    public void newDroplet() {

        droplet1 = new Droplet();
        droplet2 = new Droplet();
        droplet3 = new Droplet();
        droplet4 = new Droplet();


        Random rand = new Random();
        int randx = rand.nextInt(600);
        int randy = rand.nextInt(600);
        int randz = rand.nextInt(600);
        int randw = rand.nextInt(600);

        // x value of the ball
        droplet1.dropletx = randx;
        droplet2.dropletx = randy;
        droplet3.dropletx = randw;
        droplet4.dropletx = randz;
    }


    public void paint(Graphics g) {
        super.paint(g); // paints the background
        // casting graphics2d to graphics to give more options
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(ball1.BallImage, ball1.x, ball1.y, null); // draws the image of the ball
        if (droplet1Destroyed == false) {
            g2d.drawImage(droplet1.dropletImage, droplet1.dropletx, droplet1.droplety, null);
        }

        // droplet2
        if (droplet2Destroyed == false) {
            g2d.drawImage(droplet2.dropletImage, droplet2.dropletx, droplet2.droplety, null);
        }

        // droplet 3
        if (droplet3Destroyed == false) {
            g2d.drawImage(droplet3.dropletImage, droplet3.dropletx, droplet3.droplety, null);
        }

        // droplet4
        if (droplet4Destroyed == false) {
            g2d.drawImage(droplet4.dropletImage, droplet4.dropletx, droplet4.droplety, null);
        }
        Font font = new Font("serif",Font.BOLD, 15);
        g2d.setFont(font);
        g2d.setColor(Color.blue);
        g2d.drawString( "Life: "+Integer.toString(life.getLife()), 50,650);

    }

    // pause the game
    public void pauseGame() {
        running = false;
        timer.stop();
    }
    // exit the game
    public void stopGame() {
        System.exit(0); // quits the game
    }

    // this method is to increase the speed of the ball
    public void increaseBallSpeed()  {

        if (ball1.xVelocity < 10){
            ball1.xVelocity = abs(ball1.xVelocity) + 2;
        } else
        {
            ball1.xVelocity = 2;
        }

        if (ball1.yVelocity < 20){
            ball1.y = abs(ball1.yVelocity) + 4;
        }
        else
        {
            ball1.yVelocity = 4;
        }

//
    }
    // this method is to increase the speed of the droplet
    public void increaseDropletSpeed() {
        if(droplet1.dropletVelocity < 15){ // if the droplets velocity is under 15 increase the velocity
            droplet1.dropletVelocity = droplet1.dropletVelocity + 2;
            droplet2.dropletVelocity = droplet2.dropletVelocity + 2;
            droplet3.dropletVelocity = droplet3.dropletVelocity + 2;
            droplet4.dropletVelocity = droplet4.dropletVelocity + 2;
        }
        else{ // if the velocity is over 15 set the velocity to 4 again
            droplet1.dropletVelocity = 4;
            droplet2.dropletVelocity = 4;
            droplet3.dropletVelocity = 4;
            droplet4.dropletVelocity = 4;
        }
    }
    // checks collisions between the ball and the droplets
    public void checkCollision() {
        if ((ball1.x >= droplet1.dropletx-50 && ball1.x <= droplet1.dropletx+50) && (ball1.y >= droplet1.droplety-50 && ball1.y <= droplet1.droplety+50)) {
            collision = true; // collision flag is set to true
            droplet1Destroyed = true;
            life.ballHitDropletDecrement();
        }

        if ((ball1.x >= droplet2.dropletx-50 && ball1.x <= droplet2.dropletx+50) && (ball1.y >= droplet2.droplety-50 && ball1.y <= droplet2.droplety+50)) {
            collision = true;
            droplet2Destroyed = true;
            life.ballHitDropletDecrement();
        }

        if ((ball1.x >= droplet3.dropletx-50 && ball1.x <= droplet3.dropletx+50) && (ball1.y >= droplet3.droplety-50 && ball1.y <= droplet3.droplety+50)) {
            collision = true;
            droplet3Destroyed = true;
            life.ballHitDropletDecrement();
        }

        if ((ball1.x >= droplet4.dropletx-50 &&
                ball1.x <= droplet4.dropletx+50) &&
                (ball1.y >= droplet4.droplety-50 &&
                        ball1.y <= droplet4.droplety+50)) {
            collision = true;
            droplet4Destroyed = true;
            life.ballHitDropletDecrement();
        }
    }
    // when a droplet hits the floor. If the y location of the droplet is bigger than the frame height
    public void DropletHitFloor(){
    life.dropletHitFloorDecrement(); // calls the life class method decrement
    }


    // Action preformed
    @Override
    public void actionPerformed(ActionEvent e)  {
        // to move the ball in a fixed pattern
        if (ball1.x >= panel_width - ball1.BallImage.getWidth(null) || ball1.x < 0) {
            ball1.xVelocity = ball1.xVelocity * -1;
        }
        ball1.x = ball1.x + ball1.xVelocity;
        if (ball1.y >= panel_height - ball1.BallImage.getHeight(null) || ball1.y < 0) {
            ball1.yVelocity = ball1.yVelocity * -1;
        }
        ball1.y = ball1.y + ball1.yVelocity;

    // creating random numbers for the value of x, to randomize the x spawn
        Random rand = new Random();
        int randx1 = rand.nextInt(600); // droplet can spawn between 0 and 600 on the x-axis
        int randx2 = rand.nextInt(600);
        int randx3 = rand.nextInt(600);
        int randx4 = rand.nextInt(600);

    // check 1, checks before drawing that the droplets are not on the same x values
    // to make sure that the numbers dont equal each other so the droplets don't spawn on top of each other
        if((randx1 >= randx2-25 && randx1 <= randx2+25) ||(randx1 >= randx3-25 && randx1 <= randx3+25) || (randx1 >= randx4-25 && randx1 <= randx4+25)) {
            randx1 = panel_width - 30; // we move the droplet 100 pixels to the right
        }
        if((randx2 >= randx1-50 && randx2 <= randx1+50) ||(randx2 >= randx3-50 && randx2 <= randx3+50) || (randx2 >= randx4-50 && randx2 <= randx4+50)) {
                randx2 = panel_width - 30; // we move the droplet 100 pixels to the right
            }
        if((randx3 >= randx2-50 && randx3 <= randx2+50) ||(randx3 >= randx1-50 && randx3 <= randx1+50) || (randx3 >= randx4-50 && randx1 <= randx4+50)) {
            randx3 = panel_width - 30; // we move the droplet 100 pixels to the right
        }
        if((randx4 >= randx2-50 && randx4<= randx2+50) ||(randx4 >= randx3-50 && randx4 <= randx3+50) || (randx4 >= randx1-50 && randx4 <= randx1+50)) {
            randx4 = panel_width - 30; // we move the droplet 100 pixels to the right
        }



    // creating random numbers for the values of y
        int randy1 = rand.nextInt(300); // allows the number to be in the upper region of the frame
        int randy2 = rand.nextInt(300);
        int randy3 = rand.nextInt(400);
        // randy4 is not used as one droplet will always spawn at y = 1
        // droplet animation logic
        if(droplet1Destroyed == false) { // is the ball destroyed
            droplet1.droplety = droplet1.droplety + droplet1.dropletVelocity; // if not increase y variable with a fixed interval of velocity
        }else if(droplet1Destroyed == true){
            droplet1.droplety = randy1; // if droplet is destroyed randomize spawn position
            droplet1.dropletx = randx1;
            droplet1Destroyed = false; // set flag to false again
        }
        if (droplet1.droplety >= panel_height) { // if the ball exceeds the panel height
            droplet1.dropletx = randx1; // randomize  position
            droplet1.droplety = randy1;
            droplet1Destroyed = true; // set flag to true
            DropletHitFloor(); // call the method that will reduce life
        }
        // droplet 2
        if(droplet2Destroyed == false) {
            droplet2.droplety = droplet2.droplety + droplet2.dropletVelocity;
        }else if(droplet2Destroyed == true){
            droplet2.droplety = randy2;
            droplet2.dropletx = randx2;
            droplet2Destroyed = false;
        }
        if (droplet2.droplety >= panel_height) {
            droplet2.dropletx = randx2;
            droplet2.droplety = randy2;
            droplet2Destroyed = true;
            DropletHitFloor();
        }
        // droplet 3
        if(droplet3Destroyed == false) {
            droplet3.droplety = droplet3.droplety + droplet3.dropletVelocity;
        }else if(droplet3Destroyed == true){
            droplet3.droplety = randy3;
            droplet3.dropletx = randx3;
            droplet3Destroyed = false;
        }
        if (droplet3.droplety >= panel_height) {
            droplet3.dropletx = randx3;
            droplet3.droplety = randy3;
            droplet3Destroyed = true;
            DropletHitFloor();
        }
        // droplet 4
        if(droplet4Destroyed == false) {
            droplet4.droplety = droplet4.droplety + droplet4.dropletVelocity;
        }else if(droplet4Destroyed == true){
            droplet4.droplety = 1;
            droplet4.dropletx = randx4;
            droplet4Destroyed = false;
        }
        if (droplet4.droplety >= panel_height) {
            droplet4.dropletx = randx4;
            droplet4.droplety = 1;
            droplet4Destroyed = true;
            DropletHitFloor();
        }

        // checks after the new values of x have been assigned if the droplets are ontop of each other or not
        // to make sure that the numbers dont equal each other so the droplets dont spawn on top of each other
        if((randx1 >= randx2-25 && randx1 <= randx2+25) ||(randx1 >= randx3-25 && randx1 <= randx3+25) || (randx1 >= randx4-25 && randx1 <= randx4+25)) {
            randx1 = panel_width - 30; // we move the droplet 100 pixels to the right


        }
        if((randx2 >= randx1-50 && randx2 <= randx1+50) ||(randx2 >= randx3-50 && randx2 <= randx3+50) || (randx2 >= randx4-50 && randx2 <= randx4+50)) {
            randx2 = panel_width - 30; // we move the droplet 100 pixels to the right

        }
        if((randx3 >= randx2-50 && randx3 <= randx2+50) ||(randx3 >= randx1-50 && randx3 <= randx1+50) || (randx3 >= randx4-50 && randx1 <= randx4+50)) {
            randx3 = panel_width - 30; // we move the droplet 100 pixels to the right


        }
        if((randx4 >= randx2-50 && randx4<= randx2+50) ||(randx4 >= randx3-50 && randx4 <= randx3+50) || (randx4 >= randx1-50 && randx4 <= randx1+50)) {
            randx4 = panel_width - 30; // we move the droplet 100 pixels to the right

        }
        if(life.getLife() > 0) {
            checkCollision();
            repaint();
        }
        else{

            GameOverPage gameOverPage = new GameOverPage();
            timer.stop();
            this.setVisible(false);
        }
        // Menu items and their functions
        if (e.getSource() == menuItemPause) {
            pauseGame(); // if the user clicks on menuItemPause call pauseGame method
        }
        if (e.getSource() == menuItemStart) {
            startGame(); // if the user clicks on menuItemStart call the startGame method
        }
        if (e.getSource() == menuItemStop) {
            stopGame(); // if user clicks on the menuITemStop call the stopGame method
        }
        if (e.getSource() == menuItemIncreaseBallSpeed) {
            increaseBallSpeed(); // if the user clicks on increaseBallSpeed call the increaseBallSpeed Method
        }
        if (e.getSource() == menuItemIncreaseDropletSpeed) {
            increaseDropletSpeed(); // If the user clicks on the IncreaseDropletSpeed menu item call the increaseDropletSpeed method
        }
    }
}