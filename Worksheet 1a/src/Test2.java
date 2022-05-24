public class Test2 {

    public static void main(String[] args) {
       int n = 1;
        Theater theater = new Theater();
        theater.BookSeats(n);
        System.out.println("check1");

        theater.cancelBooking(1000);
        System.out.println("check2");

    }


}
