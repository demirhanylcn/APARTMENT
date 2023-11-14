import java.util.ArrayList;
import java.util.List;

enum type {
    offRoadCar,
    cityCar,
    boat,
    motorcycle,
    amphibious
}

public class Vehicle {
    public static List<Vehicle> allVehicles = new ArrayList<>();
    private double volume;
    private int ID;
    private static int IDCount = 1;
    private double torque;
    private String model;

    private boolean isStored;
    private type typeOfCar;
    private ParkingPlace whereItIsStored;

    Vehicle(double volume, double torque, String model, type typeOfCar) {
        ID = IDCount++;
        isStored = false;
        this.volume = volume;
        this.torque = torque;
        this.model = model;
        this.typeOfCar = typeOfCar;
        allVehicles.add(this);
    }

    public static List<Vehicle> getAllVehicles() {
        return allVehicles;
    }

    public String getModel() {
        return model;
    }


    public double getTorque() {
        return torque;
    }


    public double getVolume() {
        return volume;
    }



    public type getType() {
        return typeOfCar;
    }

    public void setStored(boolean stored) {
        isStored = stored;
    }

    public boolean getIsStored() {
        return isStored;
    }

    public void setWhereItIsStored(ParkingPlace parkingPlace) {
        this.whereItIsStored = parkingPlace;
    }

    public ParkingPlace getWhereItIsStored() {
        return whereItIsStored;
    }

    public String toString() {
        return "ID = " + ID + " model = " + model + " volume = " + volume + " torque = " + torque + " type = " + typeOfCar;
    }
}
