import java.util.List;

public interface iParkingPlace {

    String toString();

    double getVolume();

    int getID();

    double getEmptySpace();

    List<Vehicle> getVehicles();

    List<Item> getItemsParkingPlace();

    void setOwner(Person owner);

    Person getOwner();

    void checkInParkingPlace(Person person);

    void checkOutParkingPlace(Person person);

    void checkOutParkingPlaceAdmin(Person person);

    double calculateVolume(double length, double width, double height);

    void decraseEmptySpaceBy(double volume);

    void addVehicle(Vehicle vehicle) throws TooManyThingsException;

    void addItem(Item item) throws TooManyThingsException;

    void removeVehicle(Vehicle vehicle);

    void removeItem(Item item);

}
