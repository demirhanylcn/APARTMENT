import java.io.IOException;
import java.util.List;

public interface iPerson {
    public List<Item> getOwnedItems();
    public int getID();
    public String getName();
    public String getSurname();
    public List<Room> getRentedRooms();
    public List<ParkingPlace> getRentedParkingPlaces();
    public List<Vehicle> getOwnedCars();
    public List<TenantLetter> getTenantLetters();
    public void addTenantLetter(TenantLetter tenantLetter);
    public void printRentedRooms();
    public int sizeOfProperties();
    public void savePerson() throws IOException;




}
