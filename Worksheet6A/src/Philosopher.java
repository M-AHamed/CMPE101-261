public class Philosopher extends Thread{
  private int pPortions; // the portions size the philosopher wants
  private Pasta pasta; // an instance of class pasta

    // setters and getters
    public int getpPortions() {
        return pPortions;
    }

    public void setpPortions(int pPortions) {
        this.pPortions = pPortions;
    }

    public Pasta getPasta() {
        return pasta;
    }

    public void setPasta(Pasta pasta) {
        this.pasta = pasta;
    }
    // constructor
    public Philosopher(Pasta pasta, int pPortions){
        this.pasta = pasta;
        this.pPortions = pPortions;
    }


    @Override
    public void run(){
        String result =""; // result to store the result
        result= getName()+" "+ pasta.getPasta(pPortions); // gets the name and the pasta portion
        System.out.println(result);
    }


}
