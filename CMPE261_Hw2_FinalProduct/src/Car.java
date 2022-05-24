import javax.swing.*;
import java.awt.*;

public class Car implements Drawable {

    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_DOWN = 2;
    public static final int DIRECTION_LEFT = 3;
    private final GuiMain guiMain;
    private final Thread carThread;

    private static Image carImageUp;
    private static Image carImageDown;
    private static Image carImageLeft;
    private static Image carImageRight;

    private boolean running = true;
    private Image image;
    private int traveledDistance;
    private final int carSize = 75;

    private final int direction;
    private final Road road;


    //  loads up the images for the car depending on the direction that the car will be travelling in
    static {


            carImageUp = new ImageIcon("up.png").getImage();
            carImageDown = new ImageIcon("down.png").getImage();
            carImageLeft = new ImageIcon("left.png").getImage();
            carImageRight = new ImageIcon("right.png").getImage();

    }



    //  Car constructor, takes in the guiMain, direction that the car is driving in, and the road that the car is on
    public Car(GuiMain guiMain, int direction, Road road) {
        this.direction = direction;
        this.road = road;
        this.guiMain = guiMain;
        // loads the right image for the direction
        if (direction == DIRECTION_UP) {
            image = carImageUp;
        } else if (direction == DIRECTION_DOWN) {
            image = carImageDown;
        } else if (direction == DIRECTION_RIGHT) {
            image = carImageRight;
        } else if (direction == DIRECTION_LEFT) {
            image = carImageLeft;
        }
        // starts a new thread for this car to drive
        carThread = new Thread(() -> {
            while (running) {
                // delays 25 ms
                driveDelay();
                // checks if the car is in the middle of the crossroad
                boolean onCrossRoad = this.guiMain.isOnCrossRoad(this);
                if (onCrossRoad) {
                    // if it is, it tries to request the crossroad
                    guiMain.requestDrive(Car.this);
                    synchronized (guiMain.getCrossroadLock()) {
                        // synchronizing on the crossroad lock while driving on the crossroad
                        boolean endedNormal = false;
                        while (onCrossRoad) {
                            if (!guiMain.canDrive(Car.this)) {
                                // if the car claims the crossroad synchronized lock but is not allowed to drive
                                // it returns from the synchronized method here
                                break;
                            }
                            // delay 25ms
                            driveDelay();
                            // drive forward
                            drive();
                            // checks if the car is still in the middle of the crossroad
                            onCrossRoad = this.guiMain.isOnCrossRoad(this);
                            // helper variable that is used to check if the crossroad lock was rightfully claimed
                            endedNormal = true;
                        }
                        if (endedNormal) {
                            guiMain.removeFromWaiting(Car.this);
                        }
                    }
                } else {
                    // drive forward
                    drive();
                }
            }
            guiMain.removeCar(Car.this);
        });
        carThread.start();
    }


    //  sets a delay if the car so the car is not going to quickly
    private void driveDelay() {
        try {
            Thread.sleep(25); // delay of 25 milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //  function to drive the car
    private void drive() {
        final boolean canDrive = !guiMain.isCarInFront(this, (int) (carSize * 1.1));
        if (canDrive) {
            traveledDistance += 5;
        }
        if (traveledDistance > getMaxDstForDirection(direction)) {
            running = false;
        }
    }

//  function to draw the car on the screen

    @Override
    public void draw(Graphics2D g) {
        if (image == null) {
            throw new RuntimeException("Car image is null");
        }

        int width = guiMain.getWidth();
        int height = guiMain.getHeight();

        int x = 0;
        int y = 0;
        // the following computations compute the location for the car on the screen
        if (direction == DIRECTION_UP) {
            y = height - traveledDistance;
            x = width / 2 + road.getRoadSize() / 2 - carSize;
        } else if (direction == DIRECTION_RIGHT) {
            y = height / 2 + road.getRoadSize() / 2 - carSize;
            x = traveledDistance - carSize;
        } else if (direction == DIRECTION_LEFT) {
            y = height / 2 - road.getRoadSize() / 2;
            x = width - traveledDistance;
        } else if (direction == DIRECTION_DOWN) {
            y = traveledDistance - carSize;
            x = width / 2 - road.getRoadSize() / 2;
        }
        g.drawImage(image, x, y, carSize, carSize, null);
    }


    //  checks how much the car can drive before it reaches the end of the screen
//  takes in the direction of the car, as an  int
//  returns the amount of pixels till the screen ends
    private int getMaxDstForDirection(int direction) {
        if (direction == DIRECTION_UP || direction == DIRECTION_DOWN) {
            return guiMain.getHeight();
        }
        return guiMain.getWidth();
    }

    public Thread getCarThread() {
        return carThread;
    }

    public boolean isRunning() {
        return running;
    }

    public int getTraveledDistance() {
        return traveledDistance;
    }

    public int getCarSize() {
        return carSize;
    }

    public int getDirection() {
        return direction;
    }
}
