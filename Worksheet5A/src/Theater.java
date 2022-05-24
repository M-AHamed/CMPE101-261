public class Theater  extends Thread{// properties of the class
    private String NameOfTheater;
    private int NumOfSeats;
    private int PriceOfTicket;
    private int freeSeats;


    // constructor
    public Theater(String nameOfTheater, int numOfSeats, int priceOfTicket, int freeSeats) {
        NameOfTheater = nameOfTheater;
        NumOfSeats = numOfSeats;
        PriceOfTicket = priceOfTicket;
        this.freeSeats = freeSeats;
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



    // Method 1 reduces capacity of theater
    public void reduceCapacity(int NumberOfSeats) {
        if (NumberOfSeats < 0 || NumberOfSeats > getNumOfSeats()) { // if the input is outside of those boundaries throws an error
            throw new IllegalArgumentException("Check number of seats to be reduced");
        } else {
            setNumOfSeats(getNumOfSeats() - NumberOfSeats);
            // reduces the number of seats allowed to be used
            System.out.println(NameOfTheater + " The number of allowed seats is: " + getNumOfSeats() + " Number of seats reduced by: " + NumberOfSeats);
        }
    }

    // Method 2 calculates the profit of the theater
    public void calculateProfit(int numberOfSeatsBooked) {
        int seatsFilled = numberOfSeatsBooked;
        int profit = getPriceOfTicket() * seatsFilled;
        System.out.println("The " + NameOfTheater+ " generated " + profit + " today");
    }

    // Method 3 cancels the booking
    public void cancelBooking(int numberOfTickets) {
        if (numberOfTickets < 0 || numberOfTickets > getNumOfSeats()) {
            throw new IllegalArgumentException("Number of seats is either too low or already booked");
        }

        int returnedSeats = numberOfTickets;
        int newFreeSeats = returnedSeats + getFreeSeats();
        setFreeSeats(newFreeSeats);
        System.out.println(NameOfTheater +" Number of free seats now is " + getFreeSeats() + " number of returned seats is " + numberOfTickets);
        System.out.println("Amount to be refunded is: " + numberOfTickets * getPriceOfTicket());
    }
    // part 2
    @Override
    public void run(){
    reduceCapacityThread();
    calculateProfitThread();
    cancelBookingThread();
    }

    public void reduceCapacityThread() {

        for (int i = 0; i < getNumOfSeats(); i++) {
            reduceCapacity(1); // reduces capacity by 1
        }

    }

    public void calculateProfitThread(){
        for(int i = 0; i < 400; i++){
            calculateProfit(1); // calculatees the profit of 1 booked until it reaches 400 operations
        }
    }

    public void cancelBookingThread(){
        for(int i = 400; i>getFreeSeats(); i--){
            reduceCapacity(2); // reduces capacity by 2 until no more free seats
        }

    }

}
