import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Person implements iPerson {

    private static List<Person> allPeople = new ArrayList<>();
    private String name;
    private String surname;
    private int ID;
    private static int countID = 1;
    private List<Room> rentedRooms;
    private List<ParkingPlace> rentedParkingPlaces;
    private List<Item> ownedItems;
    private List<Vehicle> ownedCars;
    private List<TenantLetter> tenantLetters;

    Person() {
        ownedItems = new ArrayList<>();
        rentedRooms = new ArrayList<>();
        rentedParkingPlaces = new ArrayList<>();
        ownedCars = new ArrayList<>();
        tenantLetters = new ArrayList<>();
    }

    Person(String name, String surname) {
        this();
        this.name = name;
        this.surname = surname;
        ID = countID++;
        allPeople.add(this);
    }

    @Override
    public String toString() {
        return "[ name = " +name + " ][ surname = " + surname + " ][ ID = " + this.ID + " tenantLetters = " + tenantLetters ;
    }

    public List<Item> getOwnedItems() {
        return ownedItems;
    }

    public static List<Person> getAllPeople() {
        return allPeople;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Room> getRentedRooms() {
        return rentedRooms;
    }

    public List<ParkingPlace> getRentedParkingPlaces() {
        return rentedParkingPlaces;
    }

    public List<Vehicle> getOwnedCars() {
        return ownedCars;
    }


    public List<TenantLetter> getTenantLetters() {
        return tenantLetters;
    }

    public void addTenantLetter(TenantLetter tenantLetter) {
        tenantLetters.add(tenantLetter);
    }

    public void printRentedRooms() {
        int count = 1;
        for (Room each : rentedRooms)
            System.out.println("[ " + count++ + " ] " + each);
    }

    public int sizeOfProperties() {
        return rentedRooms.size() + rentedParkingPlaces.size();
    }

    public void savePerson() throws IOException {
        Collections.sort(rentedRooms);

        try (FileWriter writer = new FileWriter("src/savedPeople/" + this.getName() + ".txt");
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write("Name: " + this.getName() + "\n");
            bw.write("Surname: " + this.getSurname() + "\n");
            bw.write("ID: " + this.getID() + "\n");

            bw.write("Rented Rooms (sorted by volume):\n");
            for (int i = 0; i < rentedRooms.size(); i++) {
                bw.write("[ " + (i + 1) + " ] " + rentedRooms.get(i).toString() + "\n");
            }
        }
    }
}
