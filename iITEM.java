public interface iITEM {
    double getVolume();

    int getID();

    String getName();

    void setWhereItIsStored(ParkingPlace parkingPlace);

    ParkingPlace getWhereItIsStored();

    void setStored(boolean stored);

    boolean getIsStored();

    String toString();

}
