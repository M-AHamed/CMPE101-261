import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.*;

public class Test {
    public static void main(String[] args) {
        String[] movies = {"james Bond", "Venom", "Superman"};
    Theater theater = new Theater("IstanbulTheater", 50, 15,50, movies);


        // Worksheet 2A
        String[] options = {"Book Seats", "Cancel Booking", "Reduce Capacity", "Increase Capacity", "Calculate Profit"};
        // Stores the available options
        int x = JOptionPane.showOptionDialog(null, "Choose an option ", "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        System.out.println(x);
        // outputs the available options in an options' dialog using options array


        int input = 0; // saves user input from the JOptionsPane
        // used as to store the argument sent to the method called


//        switch (x){
//            case 0: // if option 1 was chosen display optionspane and call bookseats method
//              input = Integer.parseInt(JOptionPane.showInputDialog("How many seats do you want to book")); // asking the user to input the number of seats
//              theater.BookSeats(input); // passing the input to book seats method
//                break;
//            case 1: // if option 2 was chosen display optionspane and call cancelBooking method
//                input = Integer.parseInt(JOptionPane.showInputDialog("How many tickets do you want to cancel"));
//                theater.cancelBooking(input);
//                break;
//            case 2: // if option 3 was selected display optionsPane and call reduceCapacity method
//                input = Integer.parseInt(JOptionPane.showInputDialog("How many seats do you want to reduce capacity by?")); // asks the user to input the number of seats to reduce capacity by
//                theater.reduceCapacity(input); // use input as the arguemnt for reduce capacity method
//                break;
//            case 3: // if options 4 is selectd output the Joptions panel and ask for an input to send to the incresae capacity method
//                input = Integer.parseInt(JOptionPane.showInputDialog("How many seats do you want to increase capacity by?"));
//                theater.increaseCapacity(input);
//                break;
//            case 4: // if option 5 is selectd output the Jopttion panel and use input in calculate profit method
//                input = Integer.parseInt(JOptionPane.showInputDialog("How many seats were booked today"));
//                theater.calculateProfit(input);
//        }

        // For part 4
        // creating a JFrame to for the input of the theater Object




    }

}
