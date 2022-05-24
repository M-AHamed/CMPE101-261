import java.util.Random;

public class Pasta {
    private int portion = 100; // a portion of pasta is equal to 30 spoonfuls
    private Random ran;

    // getters and setters for the attributes

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }


    public Pasta(int portion){
        this.portion=portion;
        ran = new Random();
    }


    public String getPasta(int pPortions) { // takes in the desired portion by each philosopher

        String msg="";
        if(portion>=pPortions) {
            try {
                Thread.currentThread().sleep(ran.nextInt(100)); // sleeps for a random interval with a maximum of 100milliseconds
                portion=portion-pPortions; // portion in pasta class is equal to portion minus the philosopher poriton
                msg=" You can get "+ pPortions +" spoonfuls "; // returns how much pasta is left
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // interrupts the current thread
            }
        }// end of the if statement
        else {
            msg=" The maximum amount you can get is: "+portion+" Spoonfuls ";
        }

        return msg;
    }
}
