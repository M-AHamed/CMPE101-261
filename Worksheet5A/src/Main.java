public class Main {


    public static void main(String[] args) throws InterruptedException {
        Theater thread1 = new Theater("Istanbul Auditoriums", 400, 50, 400);
        Theater thread2 = new Theater("Istanbul cinema" , 1000, 100 , 1000);
        Theater thread3 = new Theater("Istanbul Magic show", 5000, 20, 5000);

        thread1.start();
         thread2.start();

        // part 3
        thread2.sleep(10000); // delays the running of thread3 by 10 seconds
        thread3.start();
    }
// question 4 is answered in the creation of 3 separate threads that run the same methods with different parameters


}
