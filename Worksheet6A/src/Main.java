import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Pasta pasta = new Pasta(100); // creates a new pasta class with a portion of 100


        Semaphore mySemaphore = new Semaphore(1); // one permit allowed
        PhilosopherSemaphore P1 = new PhilosopherSemaphore(pasta, 20, mySemaphore); // Philosopher 1 uses pasta and wants 20 portions
        Thread t1 = new Thread(P1); // new thread with philosopher one
        t1.setName("Mohammad"); // thread name is mohammad

        PhilosopherSemaphore P2= new PhilosopherSemaphore(pasta,40,mySemaphore); // creating the individual semaphores
        Thread t2= new Thread(P1,"John");


        PhilosopherSemaphore P3= new PhilosopherSemaphore(pasta,30,mySemaphore);
        Thread t3 = new Thread(P3,"Smith");

        PhilosopherSemaphore P4= new PhilosopherSemaphore(pasta,40,mySemaphore);
        Thread t4= new Thread(P3,"Ghanem");

//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();


        // part 2 method call
//        onePhilosopher(mySemaphore,pasta, "Daniel",50);

        // array of names for part 3
        String[] namesOfPhilosophers = {"Alex","Brian","Charles","Daniel"};
        int[] PortionsOfPhilosophers = {20,20,20,40};
        // part 3 method
        multiplePhilosophers(mySemaphore, pasta, namesOfPhilosophers, PortionsOfPhilosophers);


        // part 4
        int[] PortionsOfPhilosophers1 = {60,40,20,40};
        multiplePhilosophers(mySemaphore, pasta, namesOfPhilosophers, PortionsOfPhilosophers1);

        int[] PortionsOfPhilosophers2 = {10,10,200,90};
        multiplePhilosophers(mySemaphore, pasta, namesOfPhilosophers, PortionsOfPhilosophers2);
    }



    // part 2 method,
//    public static void onePhilosopher(Semaphore mySemaphore, Pasta pasta,  String name , int portionsSize){  // takes in an already defined semaphore type, pasta class instance, string name, and poritonSize
//
//        PhilosopherSemaphore pS = new PhilosopherSemaphore(pasta, portionsSize, mySemaphore); // creating a new philosopherSemaphore, with the pasta class instance, the portion size, and teh semaphore passed into the method
//        Thread thread = new Thread((Runnable) pS, name); // creates a new thread with the name passed along for the philosopher nad the name of the semaphore
//        thread.start(); // starts the thread
//    }

    public static void multiplePhilosophers(Semaphore mySemaphore, Pasta pasta,  String[] name , int[] portionsSize){ // takes an array of names and array of portionSizes

        PhilosopherSemaphore pS1 = new PhilosopherSemaphore(pasta, portionsSize[0], mySemaphore); // creating the philosopher semaphore
        Thread thread1 = new Thread((Runnable) pS1, name[0]); // creating the thread for the philosopher

        PhilosopherSemaphore pS2 = new PhilosopherSemaphore(pasta, portionsSize[1], mySemaphore);
        Thread thread2 = new Thread((Runnable) pS1, name[1]);

        PhilosopherSemaphore pS3 = new PhilosopherSemaphore(pasta, portionsSize[2], mySemaphore);
        Thread thread3 = new Thread((Runnable) pS3, name[2]);

        PhilosopherSemaphore pS4 = new PhilosopherSemaphore(pasta, portionsSize[3], mySemaphore);
        Thread thread4 = new Thread((Runnable) pS4, name[3]);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
