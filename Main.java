import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        checkRentals checkRentals = new checkRentals();
        checkRentals.callStartProgram();
        checkRentals.setRooms(Apartment.getAllApartments());
        Thread thread = new Thread(checkRentals);
        thread.start();


    }

}



