import java.util.List;

public interface iApartment {
    public void askParkingPlace();


    public double calculateVolume(double length, double width, double height);
    public List<Room> getRooms();
    public double getVolume();
    public int getID();
    public List<Person> getTenants();
    public Person getResponsiblePerson();
    public void setResponsiblePerson(Person responsiblePerson);
    public ParkingPlace getTenantsParkingPlace(Person person);
    public List<ParkingPlace> getParkingPlaces();
    public void printPeople();
    public boolean isBelongsApartment(Person person);
    public void addParkingPlace(ParkingPlace parkingPlace);
}
