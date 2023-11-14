import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Room implements Comparable<Room>, iRoom {
    private static List<Room> allRooms = new ArrayList<>();

    Apartment belongedApartment;

    private int ID;
    private static int IDcount = 1;
    private double volume;
    private double emptySpace;
    private Person roomOwner;
    private boolean isRentable = true;
    private boolean contractValid = true;

    private int daysPassed = 0;
    private TenantLetter tenantLetter;
    private List<Item> items = new ArrayList<>();



    public Room(double volume, Apartment apartment) {
        belongedApartment = apartment;
        ID = IDcount++;
        this.volume = volume;
        emptySpace = volume;
        allRooms.add(this);
    }

    public Room(Person roomOwner, double volume, Apartment apartment) {
        this(volume, apartment);
        this.roomOwner = roomOwner;
        isRentable = false;
        tenantLetter = new TenantLetter(this);
        allRooms.add(this);
    }

    @Override
    public String toString() {
        return "[ID = " + this.getID() + "] [ volume = " + (int) volume + "] [ is it rentable? " + isRentable + " ] [ Owner = " + roomOwner + " ]";
    }

    public int getApartmentID() {
        return belongedApartment.getID();
    }

    public int getDaysPassed() {
        return daysPassed;
    }

    public Person getRoomOwner() {
        return roomOwner;
    }

    public int getID() {
        return ID;
    }

    public static List<Room> getAllRooms() {
        return allRooms;
    }

    public TenantLetter getTenantLetter() {
        return tenantLetter;
    }

    public void checkInRoom(Person person) throws ProblematicTenantException {
        if (person == null) System.out.println("To perform this person can not be null.");
        else {
            if (person.getTenantLetters().size() < 3) {
                if (roomOwner == null && person.sizeOfProperties() <= 5) {
                    belongedApartment.getTenants().add(person);
                    if(belongedApartment.getResponsiblePerson() == null || belongedApartment.getTenants().isEmpty()) {
                        belongedApartment.setResponsiblePerson(person);
                    }

                    roomOwner = person;
                    person.getRentedRooms().add(this);
                    isRentable = false;
                    contractValid = true;
                    tenantLetter = new TenantLetter(this);

                } else System.out.println("this room is not empty. please check out the person first.");
            } else throw new ProblematicTenantException(person);
        }


    }

    public void checkOutRoomAdmin(Person person,int userSelection) {
        if (person == belongedApartment.getResponsiblePerson()) {

            while (true) {
                try {

                    if (userSelection >= belongedApartment.getTenants().size() || userSelection < 0)
                        System.out.println("Invalid selection. Please try again.");
                    else {
                        tenantLetter.removeTenantLetter(this);
                        tenantLetter = null;
                        belongedApartment.getTenants().get(userSelection).getRentedRooms().remove(this);
                        if (belongedApartment.getTenantsParkingPlace(belongedApartment.getTenants().get(userSelection)) != null) {
                            belongedApartment. getTenantsParkingPlace(belongedApartment.getTenants().get(userSelection)).checkOutParkingPlace(belongedApartment.getTenants().get(userSelection));
                        }
                        roomOwner = null;
                        belongedApartment.getTenants().remove(userSelection);
                        isRentable = true;
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        } else {
            System.out.println("This tenant cannot perform this action.");
        }
    }

    public void checkOutRoom(Person person) {

        try {
            if (belongedApartment.isBelongsApartment(person)) {
                if (belongedApartment.getResponsiblePerson() == person && belongedApartment.getTenants().size() != 1) {
                    belongedApartment.setResponsiblePerson(belongedApartment.getTenants().get(belongedApartment.getTenants().indexOf(person) + 1));
                }
                tenantLetter.removeTenantLetter(this);
                person.getRentedRooms().remove(this);
                if (belongedApartment.getTenantsParkingPlace(person) != null) belongedApartment.getTenantsParkingPlace(person).checkOutParkingPlace(person);
                roomOwner = null;
                isRentable = true;
                belongedApartment.getTenants().remove(person);
                tenantLetter = null;
            } else System.out.println("this person doesnt belong this room.");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    public void kickRoomOwner(Person person) {

        try {
            if (belongedApartment.isBelongsApartment(person)) {
                if (belongedApartment.getResponsiblePerson() == person && belongedApartment.getTenants().size() != 1) {
                    belongedApartment.setResponsiblePerson(belongedApartment.getTenants().get(belongedApartment.getTenants().indexOf(person) + 1));
                }
                person.getRentedRooms().remove(this);
                if (belongedApartment.getTenantsParkingPlace(person) != null) belongedApartment.getTenantsParkingPlace(person).checkOutParkingPlace(person);
                roomOwner = null;
                isRentable = true;
                belongedApartment.getTenants().remove(person);
            } else System.out.println("this person doesnt belong this room.");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }


    }

    public void renewContract() {
        Scanner userInput = new Scanner(System.in);
        int userInputInt;
        try {
            if (this.getRoomOwner() != null) {
                System.out.println("Would you like to renew your contract for room ID = " + this.getID() + "? \n"
                        + "\n[2] DONT DECIDED YET \n[1] YES \n[0] NO\n" + "If total days(" + this.getDaysPassed() + ")exceed 30 days, [Tenant = " + this.getRoomOwner().getName() + "] will be removed from room.\n");

                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt > 2 || userInputInt < 0) System.out.println("please make sure you wrote 2,1 or 0.");
                    else break;
                }
                switch (userInputInt) {
                    case 0:
                        System.out.println("contract is cancelled.");
                        contractValid = false;
                        checkOutRoom(this.getRoomOwner());
                        break;
                    case 1:
                        System.out.println("contract is renewed.");
                        this.daysPassed = 0;

                        break;
                    case 2:
                        System.out.println("giving you some time.");


                }
            } else System.out.println("this room doesnt have any tenant.");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid integer.");
            userInput.next();
            renewContract();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds exception.");
            renewContract();
        }


    }

    public void skipDay() {
        daysPassed++;
    }

    public void addItemRoom(Item item) throws TooManyThingsException {
        if (this.getEmptySpace() - item.getVolume() < 0) throw new TooManyThingsException();
        else {
            if (items.contains(item)) {
                System.out.println("This item is already in there. You cannot add the same item twice!");
            } else {
                this.decraseEmptySpaceBy(item.getVolume());
                items.add(item);

            }
        }

    }

    public void removeItemRoom(Item item) {
        if (items.contains(item)) {
            items.remove(item);
            this.emptySpace += item.getVolume();
        } else {
            System.out.println("This item doesnt belong here.");
        }
    }

    public void decraseEmptySpaceBy(double volume) {
        if (this.emptySpace - volume < 0)
            System.out.println("the operation you are trying to do is exceeding the limit of the parking place. ");
        else this.emptySpace -= volume;
    }

    public double getEmptySpace() {
        return emptySpace;
    }

    public List<Item> getItemsRoom() {
        return items;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public int compareTo(Room other) {
        return (int) this.getVolume() - (int) other.getVolume();
    }
}
