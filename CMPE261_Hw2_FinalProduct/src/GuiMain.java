import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;




public class GuiMain extends JPanel {

    private final Road road; // new road from road class
    private final List<Car> cars = new ArrayList<>(); // array list to store the cars
    private final List<Car> remove = new ArrayList<>(); // array list to remove the cars from the road
    private final Object crossroadLock = new Object(); // the lock to synchronize the cars

    private final List<Car> waitingCars = new ArrayList<>(); // list to store the watinging cars

    public GuiMain() {
        setBackground(Color.black); // color  of the background
        road = new Road(this); // defining a new road object, passing the gui to it
        startCarSpawner(); // calling the start car spawner to start the spawn of cars
    }

    // overriding the paint method
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 2d draw method
        Graphics2D g2d = (Graphics2D) g.create();
        road.draw(g2d);
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            car.draw(g2d);
        }
        cars.removeAll(remove);
        remove.clear();
    }
    // method to see if a car is infront of another car
    public boolean isCarInFront(Car car, int range) {
        for (Car c : cars) {
            if (c.getDirection() == car.getDirection()) {
                final int otherDistance = c.getTraveledDistance();
                final int thisDistance = car.getTraveledDistance();
                if (otherDistance > thisDistance && otherDistance < thisDistance + range) { // if the car is infront of the other car
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isOnCrossRoad(Car car) {
        int direction = car.getDirection();
        int distance = car.getTraveledDistance();
        if (direction == Car.DIRECTION_LEFT || direction == Car.DIRECTION_RIGHT) {
            int w = getWidth() / 2;
            int rw = road.getRoadSize() / 2;
            return distance > w - rw && distance - car.getCarSize() < w + rw;
        }
        int h = getHeight() / 2;
        int rh = road.getRoadSize() / 2;
        return distance > h - rh && distance - car.getCarSize() < h + rh;
    }

    public Object getCrossroadLock() {
        return crossroadLock;
    }

    public synchronized void requestDrive(Car car) {
        if (!waitingCars.contains(car)) {
            waitingCars.add(car);
        }
    }

    public synchronized boolean canDrive(Car car) {
        final boolean b = waitingCars.isEmpty() || waitingCars.get(0) == car;
        return b;
    }

    public synchronized void removeFromWaiting(Car car) {
        waitingCars.remove(car);
    }

    public void removeCar(Car car) {
        remove.add(car);
    }

    private void startCarSpawner() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(Main.SPAWN_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cars.add(createRandomCar());
            }
        }).start();
    }

    private Car createRandomCar() {
        double d = Math.random();
        if (d < 0.25) {
            return new Car(this, Car.DIRECTION_UP, road);
        } else if (d < 0.5) {
            return new Car(this, Car.DIRECTION_RIGHT, road);
        } else if (d < 0.75) {
            return new Car(this, Car.DIRECTION_DOWN, road);
        }
        return new Car(this, Car.DIRECTION_LEFT, road);
    }
}
