import java.util.concurrent.Semaphore;

public class PhilosopherSemaphore implements Runnable{
    private Pasta pasta; // pasta of type pasta
    private int portion; // portions
    private Semaphore mySemaphore;

    // class constructor:
    public PhilosopherSemaphore(Pasta pasta, int portion, Semaphore mySemaphore){
        this.pasta = pasta;
        this.portion = portion;
        this.mySemaphore=mySemaphore;
    }

    @Override
    public void run() {
        String result = ""; // result is equal to an empty string

        try{ // to catch errors
            mySemaphore.acquire();
            result = Thread.currentThread().getName()+" "+pasta.getPasta(portion); // the result is equal to the name of the current thread and the pasta portion
            mySemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace(); // if exception occurred print stackTrace
        }
        System.out.println(result);
    }
}
