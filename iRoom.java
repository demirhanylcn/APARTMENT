import java.util.List;

public interface iRoom {
    public int getApartmentID();
    public int getDaysPassed();
    public Person getRoomOwner();
    public int getID();
    public TenantLetter getTenantLetter();
    public void checkInRoom(Person person) throws ProblematicTenantException;
    public void checkOutRoomAdmin(Person person, int userSelection);
    public void checkOutRoom(Person person);
    public void kickRoomOwner(Person person);
    public void renewContract();
    public void skipDay();
    public void addItemRoom(Item item) throws TooManyThingsException;
    public void removeItemRoom(Item item);
    public void decraseEmptySpaceBy(double volume);
    public double getEmptySpace();
    public List<Item> getItemsRoom();
}
