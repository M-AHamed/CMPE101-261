import javax.swing.*;
import java.util.Arrays;

public class Theater {
    // properties of the class
    private String NameOfTheater;
    private int NumOfSeats;
    private int PriceOfTicket;
    private int freeSeats;
    private String[] MoviesOnDisplay;

    // constructor
    public Theater(String nameOfTheater, int numOfSeats, int priceOfTicket, int freeSeats, String[] moviesOnDisplay) {
        NameOfTheater = nameOfTheater;
        NumOfSeats = numOfSeats;
        PriceOfTicket = priceOfTicket;
        this.freeSeats = freeSeats;
        MoviesOnDisplay = moviesOnDisplay;
    }

    public Theater() {

    }

    // getters and setters
    public String getNameOfTheater() {
        return NameOfTheater;
    }

    public void setNameOfTheater(String nameOfTheater) {
        NameOfTheater = nameOfTheater;
    }

    public int getNumOfSeats() {
        return NumOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        NumOfSeats = numOfSeats;
    }

    public int getPriceOfTicket() {
        return PriceOfTicket;
    }

    public void setPriceOfTicket(int priceOfTicket) {
        PriceOfTicket = priceOfTicket;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public String[] getMoviesOnDisplay() {
        return MoviesOnDisplay;
    }

    public void setMoviesOnDisplay(String[] moviesOnDisplay) {
        MoviesOnDisplay = moviesOnDisplay;
    }

    // toStringMethod
    @Override
    public String toString() {
        return "Theater{" +
                "NameOfTheater='" + NameOfTheater + '\'' +
                ", NumOfSeats=" + NumOfSeats +
                ", PriceOfTicket=" + PriceOfTicket +
                ", freeSeats=" + freeSeats +
                ", MoviesOnDisplay=" + Arrays.toString(MoviesOnDisplay) +
                '}';
    }

    // methods






    // method to book seats in the theater:
    public void BookSeats(int NumberOfSeatsTOBook){
        if (NumberOfSeatsTOBook <0 || NumberOfSeatsTOBook > getFreeSeats() ){
        JOptionPane.showMessageDialog(null,"Check the values you have entered and try again. Values are too high or tpo low");
        }
        setFreeSeats(getNumOfSeats()-NumberOfSeatsTOBook); // to reduce the number of remaining seats
        System.out.println(NumberOfSeatsTOBook + " seats have been booked at a price of " + getPriceOfTicket()*NumberOfSeatsTOBook);
        // outputs the number of seats booked and the price of the booking
    }






// how to use the try catch method instead of throw new
    // how to do it in a message dialog


    // method to calculate profit of a single movie
    public void calculateProfit(int numberOfSeatsBooked){
        int seatsFilled = numberOfSeatsBooked;
        int profit = getPriceOfTicket()*seatsFilled;
        System.out.println("The theater has generated " + profit + " today");
    }

    // method to display what is for show at the theater
    public void whatsOnDisplay(){
        System.out.println(Arrays.toString(getMoviesOnDisplay()));
    }

    // method to calculate potential revenue if all movies for display are shown at theater at full capacity
    public void potentialDailyRevenue(){
        int numberOfMovies = getMoviesOnDisplay().length;
        int potentialRevenue = numberOfMovies * getNumOfSeats() * getPriceOfTicket();
        System.out.println(potentialRevenue +  " is the potential revenue if all movies are displayed in a day at full capacity");
    }

    // method to cancel booking
    public void cancelBooking(int numberOfTickets) {
        if (numberOfTickets < 0 || numberOfTickets > getNumOfSeats()) {
            throw new IllegalArgumentException("Number of seats is either too low or already booked");
        }

        int returnedSeats = numberOfTickets;
        int newFreeSeats = returnedSeats + getFreeSeats();
        setFreeSeats(newFreeSeats);
        System.out.println("Number of free seats now is " + getFreeSeats() + " number of returned seats is " + numberOfTickets);
        System.out.println("Amount to be refunded is: " + numberOfTickets * getPriceOfTicket());
    }

        // reduces the overall capacity based on the number of seats the user wants to reduce it by

        public void reduceCapacity(int NumberOfSeats){
            if(NumberOfSeats < 0 || NumberOfSeats > getNumOfSeats()){ // if the input is outside of those boundaries throws an error
                throw new IllegalArgumentException("Check number of seats to be reduced");
            } else{
                setNumOfSeats(getNumOfSeats()-NumberOfSeats);
                // reduces the number of seats allowed to be used
                System.out.println("The number of allowed seats is: " + getNumOfSeats() + " Number of seats reduced by: " + NumberOfSeats);
            }
        }

        public void increaseCapacity(int NumberOfSeats){
        if(NumberOfSeats < 0 || NumberOfSeats > 150){
            throw new IllegalArgumentException("Check the number of seats you want to increase capacity by");
        } else{
            setNumOfSeats(NumberOfSeats); // setting the new capacity as the new allowed capacity
            System.out.println("You have incresed the number of seats by " + NumberOfSeats + " New capacity is " + getNumOfSeats());
            // output the new capacity and how much it increasesd by
        }
        }





}