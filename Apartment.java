import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Apartment implements iApartment {

    private static List<Apartment> allApartments = new ArrayList<>();
    private double volume;
    private List<Room>  rooms = new ArrayList<>();
    private List<Person> tenants = new ArrayList<>();
    private List<ParkingPlace> parkingPlaces = new ArrayList<>();
    private Person responsiblePerson = null;

    private int ID;
    private static int IDcount = 0;



    Apartment(double length, double width, double height) {

        this.ID = IDcount++;
        this.volume = calculateVolume(length, width, height);
        askParkingPlace();
        for (int i = 0; i < 3; i++) {
            rooms.add(new Room(this.volume / 3,this));
        }
        allApartments.add(this);
    }

    Apartment(double length, double width, double height, int size) {

        this.ID = IDcount++;
        this.volume = calculateVolume(length, width, height);
        for (int i = 0; i < size; i++) {
            parkingPlaces.add(new ParkingPlace(length / 2, width / 2, height / 2,this));
        }
        for (int i = 0; i < size; i++) {
            rooms.add(new Room(this.volume / 3,this));
        }
        allApartments.add(this);
    }

    Apartment(double length, double width, double height, double parkingLength, double parkingWidth, double parkingHeight, int roomSize, int parkingPlaceSize) {

        this.ID = IDcount++;
        this.volume = calculateVolume(length, width, height);
        for (int i = 0; i < roomSize; i++) {
            rooms.add(new Room(this.volume / 3,this));
        }
        for (int i = 0; i < parkingPlaceSize; i++) {
            parkingPlaces.add(new ParkingPlace(parkingLength, parkingWidth, parkingHeight,this));

        }
        allApartments.add(this);
    }

    @Override
    public String toString() {
        return "Volume = " + volume + ", ID = " + ID + " Responsible Person = " + responsiblePerson;
    }


    public void askParkingPlace() {
        Scanner userInput = new Scanner(System.in);

        System.out.println("would you like to rent parking place? [1] YES [0] NO ");
        int userInputInt;
        while (true) {
            try {
                userInputInt = userInput.nextInt();
                if (userInputInt > 1 || userInputInt < 0) System.out.println("please make sure you wrote 1 or 0.");
                else break;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
        }


        switch (userInputInt) {
            case 0:
                System.out.println("no parking place added.");
                break;
            case 1:
                int[] parkingPlaceSizes = new int[3];
                System.out.println("please write \nfirst length + \"enter\" \nthen = width + \"enter\" \nlastly  = height + \"enter\"");
                for (int i = 0; i < parkingPlaceSizes.length; i++) parkingPlaceSizes[i] = userInput.nextInt();
                for (int i = 0; i < this.getRooms().size(); i++)
                    parkingPlaces.add(new ParkingPlace(parkingPlaceSizes[0], parkingPlaceSizes[1], parkingPlaceSizes[2],this));
                break;
        }
    }

    public static List<Apartment> getAllApartments() {
        return allApartments;
    }

    public double calculateVolume(double length, double width, double height) {
        return length * width * height;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public double getVolume() {
        return volume;
    }

    public int getID() {
        return ID;
    }

    public List<Person> getTenants() {
        return tenants;
    }


    public Person getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(Person person) {
        responsiblePerson = person;
    }

    public ParkingPlace getTenantsParkingPlace(Person person) {
        for (ParkingPlace each : getParkingPlaces()) {
            if (each.getOwner() == person) return each;
        }
        return null;
    }

    public List<ParkingPlace> getParkingPlaces() {
        return parkingPlaces;
    }

    public void printPeople() {
        int count = 1;
        for (Person each : tenants) {
            System.out.println("[" + count++ + "] " + each);
        }
    }


    public boolean isBelongsApartment(Person person) {
        for (Person each : getTenants())
            if (each.getID() == person.getID()) return true;

        return false;
    }

    public void addParkingPlace(ParkingPlace parkingPlace) {
        for (ParkingPlace each : parkingPlaces) {
            if (each == parkingPlace) {
                System.out.println("You cannot add the same parking place twice.");
                return;
            }
        }
        parkingPlaces.add(parkingPlace);
    }

}
