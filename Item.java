import java.util.ArrayList;
import java.util.List;

public class Item implements iITEM {

    private static List<Item> items = new ArrayList<>();
    private double volume;
    private int ID;
    private static int IDCount = 1;

    private Person owner;
    private String name;
    private ParkingPlace whereItIsStored;

    private boolean isStored = false;

    Item(double volume, String name) {
        ID = IDCount++;
        this.name = name;
        this.volume = volume;
        items.add(this);

    }

    public void setWhereItIsStored(ParkingPlace parkingPlace) {
        this.whereItIsStored = parkingPlace;
    }

    public ParkingPlace getWhereItIsStored() {
        return whereItIsStored;
    }

    public double getVolume() {
        return volume;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setStored(boolean stored) {
        isStored = stored;
    }

    public boolean getIsStored() {
        return isStored;
    }

    public static List<Item> getItems() {
        return items;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String toString() {
        return " ID = " + ID + " volume = " + volume + " name = " + name + "owner = " + owner;
    }

}
