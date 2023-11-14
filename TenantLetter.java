public class TenantLetter {

    private int apartmentID;
    private Person owner;


    TenantLetter(Room room) {
        apartmentID = room.getID();
        owner = room.getRoomOwner();
        room.getRoomOwner().addTenantLetter(this);
    }

    public void removeTenantLetter(Room room) {
        if ( room.getRoomOwner() != null) room.getRoomOwner().getTenantLetters().remove(room.getTenantLetter());
        else System.out.println("room owner is empty.");
    }

    @Override
    public String toString() {
        return "apartmentID  = " + apartmentID ;
    }
}
