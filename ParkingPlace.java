import java.util.ArrayList;
import java.util.List;

public class ParkingPlace  implements iParkingPlace {

    private static List<ParkingPlace> allParkingPlaces = new ArrayList<>();
    private double volume;

    private Apartment belongedApartment;
    private int ID;
    private static int IDcount = 1;
    private double emptySpace;
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private Person owner;


    ParkingPlace(double length, double width, double height,Apartment apartment) {
        belongedApartment = apartment;
        this.ID = IDcount++;
        volume = calculateVolume(length, width, height);
        emptySpace = volume;
        allParkingPlaces.add(this);
    }


    public String toString() {
        return "volume = " + getVolume() + " ID = " + ID + " emptySpace = " + getEmptySpace() + " owner = " + owner;
    }


    public double getVolume() {
        return volume;
    }


    public int getID() {
        return ID;
    }

    public double getEmptySpace() {
        return emptySpace;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Item> getItemsParkingPlace() {
        return items;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Person getOwner() {
        return owner;
    }

    public void checkInParkingPlace(Person person) {
        if (owner == null && person.sizeOfProperties() <= 5) {
            owner = person;
            person.getRentedParkingPlaces().add(this);
        } else System.out.println("you can not rent this place. it is already full.");
    }

    public void checkOutParkingPlace(Person person) {
        owner = null;
        for (Vehicle each : vehicles) each.setStored(false);
        vehicles.clear();
        for (Item each : items) each.setStored(false);
        items.clear();
        emptySpace = volume;
        person.getRentedParkingPlaces().remove(this);

    }

    public void checkOutParkingPlaceAdmin(Person person) {
        if (belongedApartment.getResponsiblePerson() == person) {
            this.owner = null;
            for (Vehicle each : vehicles) each.setStored(false);
            vehicles.clear();
            for (Item each : items) each.setStored(false);
            items.clear();
            emptySpace = volume;
            this.getOwner().getRentedParkingPlaces().remove(this);
        } else System.out.println("This person is not tax responsible. You can not perform this.");


    }
    public double calculateVolume(double length, double width, double height) {
        volume = length * width * height;
        return volume;
    }

    public void decraseEmptySpaceBy(double volume) {
        if (this.emptySpace - volume < 0)
            System.out.println("the operation you are trying to do is exceeding the limit of the parking place. ");
        else this.emptySpace -= volume;
    }

    public void addVehicle(Vehicle vehicle) throws TooManyThingsException{
        if (this.getEmptySpace() - vehicle.getVolume() < 0) throw new TooManyThingsException();
        if (vehicles.contains(vehicle)) {
            System.out.println("This vehicle is already in there. You cannot add the same object twice!");
        } else {
            this.decraseEmptySpaceBy(vehicle.getVolume());
            vehicles.add(vehicle);
            vehicle.setStored(true);
            vehicle.setWhereItIsStored(this);

        }
    }

    public void addItem(Item item) throws TooManyThingsException {
        if (this.getEmptySpace() - item.getVolume() < 0) throw new TooManyThingsException();
        if (items.contains(item)) {
            System.out.println("This item is already in there. You cannot add the same item twice!");
        } else {
            this.decraseEmptySpaceBy(item.getVolume());
            items.add(item);
            item.setStored(true);
            item.setWhereItIsStored(this);

        }
    }

    public void removeVehicle(Vehicle vehicle) {
        if (vehicles.contains(vehicle)) {
            vehicles.remove(vehicle);
            vehicle.setStored(false);
            vehicle.setWhereItIsStored(null);
        }
    }

    public void removeItem(Item item) {
        if (items.contains(item)) {
            vehicles.remove(item);
            item.setStored(false);
            item.setWhereItIsStored(null);
        }
    }

    public static List<ParkingPlace> getAllParkingPlaces() {
        return allParkingPlaces;
    }

}
