import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.*;

public class Panel {

    Person currentPerson;
    Apartment currentApartment;
    Item currentItem;
    Vehicle currentVehicle;
    ParkingPlace currentParkingPlace;
    Room currentRoom;


    public void startProgram() {

        try {
            Person first = new Person("Sasha", "Yakovleva");
            Person second = new Person("Rogue", "Amendiares");
            Person third = new Person("Wakako", "Okada");
            Person forth = new Person("David", "Martinez");
            Person fifth = new Person("Adam", "Smasher");

            Apartment apartment = new Apartment(30, 30, 30, 30, 30, 30, 10, 5);

            apartment.getParkingPlaces().get(0).checkInParkingPlace(first);
            apartment.getParkingPlaces().get(1).checkInParkingPlace(second);
            apartment.getParkingPlaces().get(2).checkInParkingPlace(third);
            apartment.getParkingPlaces().get(3).checkInParkingPlace(forth);
            apartment.getParkingPlaces().get(4).checkInParkingPlace(fifth);

            apartment.getRooms().get(0).checkInRoom(first);
            apartment.getRooms().get(1).checkInRoom(second);
            apartment.getRooms().get(2).checkInRoom(third);
            apartment.getRooms().get(3).checkInRoom(forth);
            apartment.getRooms().get(4).checkInRoom(fifth);

            Vehicle vehicle1 = new Vehicle(140, 300, "camaro", type.cityCar);
            Vehicle vehicle2 = new Vehicle(100, 200, "suzuki", type.motorcycle);
            Vehicle vehicle3 = new Vehicle(400, 700, "camaro", type.boat);
            Vehicle vehicle4 = new Vehicle(300, 500, "camaro", type.amphibious);
            Vehicle vehicle5 = new Vehicle(240, 500, "camaro", type.offRoadCar);


            first.getOwnedCars().add(vehicle1);
            first.getRentedParkingPlaces().get(0).addVehicle(vehicle2);
            second.getOwnedCars().add(vehicle2);
            second.getRentedParkingPlaces().get(0).addVehicle(vehicle1);
            third.getOwnedCars().add(vehicle3);
            third.getRentedParkingPlaces().get(0).addVehicle(vehicle3);
            forth.getOwnedCars().add(vehicle4);
            forth.getRentedParkingPlaces().get(0).addVehicle(vehicle4);
            fifth.getOwnedCars().add(vehicle5);
            fifth.getRentedParkingPlaces().get(0).addVehicle(vehicle5);

        } catch (TooManyThingsException e) {
            e.getMessage();
        } catch (ProblematicTenantException e) {
            e.getMessage();
        }


    }

    public Apartment selectApartment() {

        if (Apartment.getAllApartments().isEmpty()) {
            System.out.println("There is no apartment to select please create new one.");
            return null;
        } else {
            int count = 0;
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            System.out.println("Which apartment you want to choose?");

            for (Apartment each : Apartment.getAllApartments()) {
                System.out.println("[" + count++ + "] " + each);
            }

            while (true) {
                try {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > count)
                        System.out.println("please give number in range.");
                    else break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid integer.");
                    userInput.next();
                }
                catch (NullPointerException e) {
                    System.out.println("Null pointer exception.");

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bounds exception.");
                }
            }

            return Apartment.getAllApartments().get(userInputInt);
        }


    }

    public Person selectPersonFromApartment(Apartment apartment) {

        if (apartment == null) System.out.println("To perform this currentApartment can not be null.");
        else {
            if (apartment.getTenants().isEmpty()) {
                System.out.println("There is no tenant living in here.");
                return null;
            } else {
                int count = 0;
                int userInputInt;
                Scanner userInput = new Scanner(System.in);
                List<Person> tenants = apartment.getTenants();
                System.out.println("Which person you want to choose?");
                for (Person each : tenants) {
                    System.out.println("[" + count++ + "] " + each);
                }

                while (true) {
                    try {
                        userInputInt = userInput.nextInt();
                        if (!(userInputInt > tenants.size() - 1 || userInputInt < 0)) break;
                        else System.out.println("Please choose person in range.");
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid integer.");
                        userInput.next();
                    }
                }
                currentParkingPlace = null;
                currentVehicle = null;
                currentItem = null;
                return tenants.get(userInputInt);
            }
        }
        return null;
    }

    public Person selectPerson() {


        if (Person.getAllPeople().isEmpty()) {
            System.out.println("There is no one to select, please create new person.");
            return null;
        } else {
            int count = 0;
            List<Person> people = Person.getAllPeople();
            Scanner userInput = new Scanner(System.in);
            int userInputInt;


            for (Person each : people) {
                System.out.println("[" + count++ + "] " + each);
            }

            System.out.println("which person you want to choose?");

            while (true) {
                try {
                    userInputInt = userInput.nextInt();
                    if (!(userInputInt > people.size() - 1 || userInputInt < 0)) break;
                    else System.out.println("please choose person in range.");
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid integer.");
                    userInput.next();
                }
                catch (NullPointerException e) {
                    System.out.println("Null pointer exception.");

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index out of bounds exception.");
                }
            }
            return people.get(userInputInt);
        }
    }

    public Apartment createApartment() {
        double[] userInputs = new double[8];
        double userInputDouble;
        int userInputInt;
        Scanner userInput = new Scanner(System.in);

        try {
            for (int i = 0; i < userInputs.length; i++) {
                if (i == 0) {
                    System.out.println("What will be the length of the room?");
                    while (true) {
                        userInputDouble = userInput.nextDouble();
                        if (userInputDouble <= 0) System.out.println("please give number bigger than 0.");
                        else {
                            userInputs[i] = userInputDouble;
                            break;
                        }
                    }
                } else if (i == 1) {
                    System.out.println("What will be the width of the room?");
                    while (true) {
                        userInputDouble = userInput.nextDouble();
                        if (userInputDouble <= 0) System.out.println("please give number bigger than 0.");
                        else {
                            userInputs[i] = userInputDouble;
                            break;
                        }
                    }
                } else if (i == 2) {
                    System.out.println("What will be the height of the room?");
                    while (true) {
                        userInputDouble = userInput.nextDouble();
                        if (userInputDouble <= 0) System.out.println("please give number bigger than 0.");
                        else {
                            userInputs[i] = userInputDouble;
                            break;
                        }
                    }
                } else if (i == 3) {
                    System.out.println("What will be the length of the parking place?");
                    while (true) {
                        userInputDouble = userInput.nextDouble();
                        if (userInputDouble <= 0) System.out.println("please give number bigger than 0.");
                        else {
                            userInputs[i] = userInputDouble;
                            break;
                        }
                    }
                } else if (i == 4) {
                    System.out.println("What will be the width of the parking place?");
                    while (true) {
                        userInputDouble = userInput.nextDouble();
                        if (userInputDouble <= 0) System.out.println("please give number bigger than 0.");
                        else {
                            userInputs[i] = userInputDouble;
                            break;
                        }
                    }
                } else if (i == 5) {
                    System.out.println("What will be the height of the parking place?");
                    while (true) {
                        userInputDouble = userInput.nextDouble();
                        if (userInputDouble <= 0) System.out.println("please give number bigger than 0.");
                        else {
                            userInputs[i] = userInputDouble;
                            break;
                        }
                    }
                } else if (i == 6) {
                    System.out.println("How many rooms will be there?");
                    while (true) {
                        userInputInt = userInput.nextInt();
                        if (userInputInt <= 0) System.out.println("please give number bigger than 0.");
                        else {
                            userInputs[i] = userInputInt;
                            break;
                        }
                    }
                } else {
                    System.out.println("How many parking places will be there?");
                    while (true) {
                        userInputInt = userInput.nextInt();
                        if (userInputInt < 0) System.out.println("please give number bigger or equal to 0.");
                        else {
                            userInputs[i] = userInputInt;
                            break;
                        }
                    }
                }
            }
            return new Apartment(userInputs[0], userInputs[1], userInputs[2], userInputs[3], userInputs[4], userInputs[5], (int) userInputs[6], (int) userInputs[7]);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid integer.");
            userInput.next();
        }
        catch (NullPointerException e) {
            System.out.println("Null pointer exception.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds exception.");
        }
        return null;


    }

    public Person createPerson() {
        String[] userInputs = new String[2];
        String userInputString;
        Scanner userInput = new Scanner(System.in);

        try {
            for (int i = 0; i < userInputs.length; i++) {
                if (i == 0) {
                    System.out.println("What will be the name of the person?");

                } else {
                    System.out.println("What will be the surname of the person?");

                }
                userInputString = userInput.nextLine();
                userInputs[i] = userInputString;
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid integer.");
            userInput.next();
        }
        catch (NullPointerException e) {
            System.out.println("Null pointer exception.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds exception.");
        }

        return new Person(userInputs[0], userInputs[1]);
    }

    public void createParkingPlace() {
        double[] userInputs = new double[3];
        double userInputDouble;
        Scanner userInput = new Scanner(System.in);

        try {
            for (int i = 0; i < userInputs.length; i++) {
                if (i == 0) {
                    System.out.println("What will be the length of the parking place?");
                    while (true) {
                        userInputDouble = userInput.nextDouble();
                        if (userInputDouble <= 0) System.out.println("please give number bigger than 0.");
                        else {
                            userInputs[i] = userInputDouble;
                            break;
                        }
                    }
                } else if (i == 1) {
                    System.out.println("What will be the width of the parking place?");
                    while (true) {
                        userInputDouble = userInput.nextDouble();
                        if (userInputDouble <= 0) System.out.println("please give number bigger than 0.");
                        else {
                            userInputs[i] = userInputDouble;
                            break;
                        }
                    }
                } else {
                    System.out.println("What will be the height of the parking place?");
                    while (true) {
                        userInputDouble = userInput.nextDouble();
                        if (userInputDouble <= 0) System.out.println("please give number bigger than 0.");
                        else {
                            userInputs[i] = userInputDouble;
                            break;
                        }
                    }
                }
            }


            System.out.println("Assign parking place to apartment.");
            Apartment selectedApartment = selectApartment();
            selectedApartment.addParkingPlace(new ParkingPlace(userInputs[0], userInputs[1], userInputs[2], selectedApartment));
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid integer.");
            userInput.next();
        }
        catch (NullPointerException e) {
            System.out.println("Null pointer exception.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds exception.");
        }
    }

    public Vehicle createVehicle() {

        if (currentPerson == null) System.out.println("To perform this currentPerson can not be null.");
        else {
            double[] userInputs = new double[2];
            type[] types = {type.offRoadCar, type.cityCar, type.boat, type.motorcycle, type.amphibious};
            type givenType = null;
            String model = null;
            double userInputDouble;
            int userInputInt;
            Scanner userInput = new Scanner(System.in);


            try {
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        System.out.println("What will be the volume of the car?");
                        while (true) {
                            userInputDouble = userInput.nextDouble();
                            if (userInputDouble <= 0) System.out.println("please give number bigger than 0.");
                            else {
                                userInputs[i] = userInputDouble;
                                break;
                            }
                        }
                    } else if (i == 1) {
                        System.out.println("What will be the torque of the car?");
                        while (true) {
                            userInputDouble = userInput.nextDouble();
                            if (userInputDouble <= 0) System.out.println("please give number bigger than 0.");
                            else {
                                userInputs[i] = userInputDouble;
                                break;
                            }
                        }
                    } else if (i == 2) {
                        System.out.println("What will be the model of the car?");
                        while (true) {
                            model = userInput.nextLine();
                            if (!model.isEmpty()) break;
                        }

                    } else {
                        System.out.println("What will be the type of the car?");
                        System.out.println("""
                                [0] offRoadCar\s
                                [1] cityCar
                                [2] boat
                                [3] motorcycle
                                [4] amphibious\s
                                """);
                        while (true) {
                            userInputInt = userInput.nextInt();
                            if (userInputInt < 0 || userInputInt >= types.length)
                                System.out.println("please give a number in range.");
                            else {
                                givenType = types[userInputInt];
                                break;
                            }
                        }
                    }
                }
                Vehicle vehicle = new Vehicle(userInputs[0], userInputs[1], model, givenType);
                currentPerson.getOwnedCars().add(vehicle);
                return vehicle;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }
        }


        return null;
    }

    public Vehicle selectVehicle(Person person) {


        if (person == null) {
            System.out.println("To perform this currentPerson can not be null. Please select person first.");
            return null;
        } else if (person.getOwnedCars().isEmpty()) {
            System.out.println("This person doesnt have any car to select.");
            return null;
        } else {

            int count = 0;
            int userInputInt;
            Scanner userInput = new Scanner(System.in);

            try {
                System.out.println("which car you would like to choose");
                for (Vehicle each : person.getOwnedCars()) {
                    System.out.println("[" + count++ + "]" + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > count)
                        System.out.println("please give number in range.");
                    else break;
                }
                return person.getOwnedCars().get(userInputInt);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }

        }
        return null;
    }

    public void storeVehicle(Vehicle vehicle, ParkingPlace parkingPlace, Person person) {

        if (vehicle == null) {
            System.out.println("To perform this currentVehicle can not be null. Please select vehicle first.");
        } else if (parkingPlace == null) {
            System.out.println("To perform this currentParkingPlace can not be null. Please select parking place first.");
        } else if (person == null)
            System.out.println("To perform this currentPerson can not be null. Please select person first.");
        else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            try {
                if (parkingPlace.getEmptySpace() - vehicle.getVolume() < 0)
                    System.out.println("parking place is not big enough. nothing stored.");
                else if (vehicle.getIsStored()) {
                    System.out.println("vehicle is stored in somewhere else. would you like to change it to this place? [1] YES [0] NO");
                    userInputInt = userInput.nextInt();
                    if (userInputInt == 1) {
                        if (parkingPlace.getOwner() != person)
                            System.out.println("This parking place doesnt belong currentPerson. You can not do that.");
                        else {
                            vehicle.getWhereItIsStored().removeVehicle(vehicle);
                            parkingPlace.addVehicle(vehicle);
                            System.out.println("vehicle stored.");
                        }

                    } else System.out.println("nothing is changed.");
                } else {
                    parkingPlace.addVehicle(vehicle);
                    System.out.println("vehicle stored.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }catch (TooManyThingsException e) {
                e.getMessage();
                userInput.next();
            }

        }


    }

    public void storeItemToParkingPlace(Item item, ParkingPlace parkingPlace) {

        if (item == null) {
            System.out.println("To perform this currentItem can not be null. Please select vehicle first.");

        } else if (parkingPlace == null) {
            System.out.println("To perform this currentParkingPlace can not be null. Please select vehicle first.");
        } else if (item.getOwner() != parkingPlace.getOwner())
            System.out.println("This item and parking place belongs to different people.");
        else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            try {
                if (parkingPlace.getEmptySpace() - item.getVolume() < 0)
                    System.out.println("parking place is not big enough. nothing stored.");
                else if (item.getIsStored()) {
                    System.out.println("vehicle is stored in somewhere else. would you like to change it to this place? [1] YES [0] NO");
                    userInputInt = userInput.nextInt();
                    if (userInputInt == 1) {
                        item.getWhereItIsStored().removeItem(item);
                        parkingPlace.addItem(item);
                        item.setOwner(parkingPlace.getOwner());
                        currentItem = null;
                        System.out.println("item stored.");
                    } else System.out.println("nothing is changed.");
                } else {
                    parkingPlace.addItem(item);
                    item.setOwner(parkingPlace.getOwner());
                    System.out.println("item stored.");
                    currentItem = null;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            } catch (TooManyThingsException e) {
                e.getMessage();
                userInput.next();
            }
        }


    }

    public Item createItem() {
        Scanner userInput = new Scanner(System.in);
        if (currentPerson == null) System.out.println("To perform this currentPerson can not be null.");
        else {
            try {

                System.out.println("what will be the name of the item?");
                String userInputString = userInput.next();
                System.out.println("what will be the volume of the item?");
                double userInputDouble = userInput.nextDouble();
                Item item = new Item(userInputDouble, userInputString);
                currentPerson.getOwnedItems().add(item);
                item.setOwner(currentPerson);
                return item;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }
        }

        return null;
    }

    public ParkingPlace selectParkingPlace(Person person) {

        if (person == null) {
            System.out.println("To do this action currentPerson must be selected.");
            return null;
        } else if (person.getRentedParkingPlaces().isEmpty()) {
            System.out.println("This person doesnt have any parking place rented.");
            return null;
        } else {
            int count = 0;
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            try {
                System.out.println("Which parking place you want to select?");
                for (ParkingPlace each : person.getRentedParkingPlaces()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > person.getRentedParkingPlaces().size())
                        System.out.println("please give number in range.");
                    else break;
                }
                return person.getRentedParkingPlaces().get(userInputInt);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }
        }

        return null;
    }

    public ParkingPlace selectAllParkingPlaces() {

        int count = 0;
        Scanner userInput = new Scanner(System.in);
        int userInputInt;

        try {
            if (ParkingPlace.getAllParkingPlaces().isEmpty()) {
                System.out.println("There is no parking place to select please create new one.");
            } else {
                System.out.println("Which parking place you want to select?");
                for (ParkingPlace each : ParkingPlace.getAllParkingPlaces()) {
                    System.out.println("[" + count++ + "] " + each);
                }

                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > ParkingPlace.getAllParkingPlaces().size())
                        System.out.println("please give number in range.");
                    else break;
                }
                return ParkingPlace.getAllParkingPlaces().get(userInputInt);
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid integer.");
            userInput.next();
        }
        catch (NullPointerException e) {
            System.out.println("Null pointer exception.");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds exception.");
        }
        return null;
    }

    public Item selectItemFromParkingPlace(ParkingPlace parkingPlace) {
        if (parkingPlace == null) {
            System.out.println("To perform this currentParkingPlace can not be null.");
        } else if (parkingPlace.getItemsParkingPlace().isEmpty()) {
            System.out.println("There is no item in this parking place.");
        } else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            System.out.println("Which item you want to select?");
            try {
                for (Item each : parkingPlace.getItemsParkingPlace()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > parkingPlace.getItemsParkingPlace().size())
                        System.out.println("please give number in range.");
                    else break;
                }
                return parkingPlace.getItemsParkingPlace().get(userInputInt);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }
        }
        return null;
    }

    public Item selectItemFromRoom(Room room) {
        if (room == null) {
            System.out.println("To perform this currentRoom can not be null.");
        } else if (room.getItemsRoom().isEmpty()) {
            System.out.println("There is no item in this room.");
        } else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            System.out.println("Which item you want to select?");
            try {
                for (Item each : room.getItemsRoom()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > room.getItemsRoom().size())
                        System.out.println("please give number in range.");
                    else break;
                }
                return room.getItemsRoom().get(userInputInt);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }
        }
        return null;
    }

    public void storeItemToRoom(Item item, Room room) {
        if (item == null) {
            System.out.println("To perform this currentItem can not be null.");
        } else if (room == null) {
            System.out.println("To perform this currentRoom can not be null.");
        } else if (item.getOwner() != room.getRoomOwner())
            System.out.println("This room and item belongs to different people.");

        else {
            try {
                room.addItemRoom(item);
            } catch (TooManyThingsException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Room selectRoomCurrentPerson(Person person) {
        if (person == null) {
            System.out.println("To perform this currentPerson can not be null.");
        } else if (person.getRentedRooms().isEmpty()) {
            System.out.println("This person doesnt have any rented rooms.");
        } else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            System.out.println("Which room you want to select?");
            try {
                for (Room each : person.getRentedRooms()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > person.getRentedRooms().size())
                        System.out.println("please give number in range.");
                    else break;
                }
                return person.getRentedRooms().get(userInputInt);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }
        }
        return null;
    }

    public Room selectRoomAllList() {
        if (Room.getAllRooms().isEmpty()) {
            System.out.println("There is no room to select.");
        } else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            System.out.println("Which room you want to select?");
            try {
                for (Room each : Room.getAllRooms()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > Room.getAllRooms().size())
                        System.out.println("please give number in range.");
                    else break;
                }
                return Room.getAllRooms().get(userInputInt);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }
        }
        return null;
    }

    public void rentRoomCurrentPersontoCurrentRoom(Person person, Room room) {
        if (person == null) System.out.println("To perform this currentPerson can not be null.");
        else if (room == null) System.out.println("To perform this currentRoom can not be null.");
        else if (room.getRoomOwner() != null) System.out.println("This room is not empty. You can not rent it.");
        else {
            try {
                room.checkInRoom(person);
            } catch (ProblematicTenantException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void rentRoomCurrentPersontoAllRooms(Person person) {
        if (person == null) System.out.println("To perform this currentPerson can not be null.");
        else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            List<Room> availableRooms = new ArrayList<>();

            try {
                for (Room each : Room.getAllRooms()) {
                    if (each.getRoomOwner() == null) availableRooms.add(each);
                }
                if (!availableRooms.isEmpty()) {
                    System.out.println("Which room you want to check in?");
                    for (Room each : availableRooms) {
                        System.out.println("[" + count++ + "] " + each);
                    }
                    while (true) {
                        userInputInt = userInput.nextInt();
                        if (userInputInt < 0 || userInputInt > availableRooms.size())
                            System.out.println("please give number in range.");
                        else break;
                    }
                    availableRooms.get(userInputInt).checkInRoom(person);
                } else System.out.println("There is no room to rent.");


            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            } catch (ProblematicTenantException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void rentParkingPlaceFromCurrentApartment(Person person, Apartment apartment) {
        if (person == null) System.out.println("To perform this currentPerson can not be null.");
        else if (apartment == null) System.out.println("To perform this currentApartment can not be null.");
        else if (apartment.getParkingPlaces().isEmpty())
            System.out.println("This apartment doesnt have any parking place.");
        else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            boolean isAvailable = false;
            try {

                for (ParkingPlace each : apartment.getParkingPlaces()) {

                    if (each.getOwner() == null) {
                        System.out.println("[" + count + "] " + each);
                        isAvailable = true;
                    }
                    count++;
                }
                if (!isAvailable) System.out.println("There is no parking place to rent.");
                else {
                    while (true) {
                        userInputInt = userInput.nextInt();
                        if (userInputInt < 0 || userInputInt > count)
                            System.out.println("please give number in range.");
                        else break;
                    }
                    apartment.getParkingPlaces().get(userInputInt).checkInParkingPlace(person);

                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }

        }
    }

    public void checkOutRoomFromRented(Person person) {
        if (person == null) System.out.println("To perform this currentPerson can not be null.");
        else if (person.getRentedRooms().isEmpty()) System.out.println("This person doesnt have any rented room.");
        else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            System.out.println("Which room you want to check out?");
            try {
                for (Room each : person.getRentedRooms()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > count)
                        System.out.println("please give number in range.");
                    else break;
                }
                person.getRentedRooms().get(userInputInt).checkOutRoom(person);

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }
        }
    }

    public void kickSomeoneFromRoom(Person person) {
        if (person == null) System.out.println("To perform this currentPerson can not be null.");
        else {
            List<Apartment> availableApartments = new ArrayList<>();
            List<Room> availableRooms = new ArrayList<>();
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            try {
                for (Apartment each : Apartment.getAllApartments()) {
                    if (each.getResponsiblePerson() == person) availableApartments.add(each);
                }
                if (availableApartments.isEmpty())
                    System.out.println("this person is not taxResponsible in any apartment.");
                else {
                    for (Apartment apartment : availableApartments) {
                        for (Room room : apartment.getRooms()) {
                            if (room.getRoomOwner() != person && room.getRoomOwner() != null) availableRooms.add(room);
                        }
                    }
                    if (availableRooms.isEmpty()) System.out.println("there is no room.");
                    else {
                        System.out.println("Which room you want to kick out?");
                        for (Room each : availableRooms) {
                            System.out.println("[" + count++ + "] " + each);
                        }
                        while (true) {
                            userInputInt = userInput.nextInt();
                            if (userInputInt < 0 || userInputInt > count)
                                System.out.println("please give number in range.");
                            else break;
                        }
                        availableRooms.get(userInputInt).checkOutRoomAdmin(person, userInputInt);
                    }

                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }


        }

    }

    public void checkOutPersonFromParkingPlace(Person person) {
        if (person == null) System.out.println("To perform this currentPerson can not be null.");
        else if (person.getRentedParkingPlaces().isEmpty())
            System.out.println("This person doesnt have any parking place.");
        else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            System.out.println("Which parking place you want to check out?");
            try {
                for (ParkingPlace each : person.getRentedParkingPlaces()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > count)
                        System.out.println("please give number in range.");
                    else break;
                }
                person.getRentedParkingPlaces().get(userInputInt).checkOutParkingPlace(person);

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }
        }

    }

    public void savePerson(Person person) {

        if (person == null) System.out.println("To perform this currentPerson can not be null.");
        else {
            try {
                person.savePerson();
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void removeVehicleFromCurrentParkingPlace(ParkingPlace parkingPlace) {
        if (parkingPlace == null) System.out.println("To perform this currentParkingPlace can not be empty.");
        else if (parkingPlace.getVehicles().isEmpty()) System.out.println("There is no vehicle in this parking place");
        else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            System.out.println("Which vehicle you want to remove?");
            try {
                for (Vehicle each : parkingPlace.getVehicles()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > count)
                        System.out.println("please give number in range.");
                    else break;
                }
                parkingPlace.removeVehicle(parkingPlace.getVehicles().get(userInputInt));

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }

        }
    }

    public void removeItemFromCurrentParkingPlace(ParkingPlace parkingPlace) {
        if (parkingPlace == null) System.out.println("To perform this currentParkingPlace can not be empty.");
        else if (parkingPlace.getItemsParkingPlace().isEmpty())
            System.out.println("There is no item in this parking place");
        else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            System.out.println("Which item you want to remove?");
            try {
                for (Item each : parkingPlace.getItemsParkingPlace()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > count)
                        System.out.println("please give number in range.");
                    else break;
                }
                parkingPlace.removeItem(parkingPlace.getItemsParkingPlace().get(userInputInt));

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }

        }
    }

    public void removeItemFromCurrentRoom(Room room) {
        if (room == null) System.out.println("To perform this currentRoom can not be empty.");
        else if (room.getItemsRoom().isEmpty()) System.out.println("There is no item in this room.");
        else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            System.out.println("Which item you want to remove?");
            try {
                for (Item each : room.getItemsRoom()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > count)
                        System.out.println("please give number in range.");
                    else break;
                }
                room.removeItemRoom(room.getItemsRoom().get(userInputInt));

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
            catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }
        }
    }

    public Item selectItemFromAllList() {
        if (Item.getItems().isEmpty()) System.out.println("There is no item to select");
        else {
            Scanner userInput = new Scanner(System.in);
            int userInputInt;
            int count = 0;
            try {
                for (Item each : Item.getItems()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > count)
                        System.out.println("please give number in range.");
                    else break;
                }
                return Item.getItems().get(userInputInt);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            }
             catch (NullPointerException e) {
                System.out.println("Null pointer exception.");

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds exception.");
            }
        }

        return null;

    }

    public Item selectItemFromCurrentPerson(Person person) {
        Scanner userInput = new Scanner(System.in);
        int userInputInt;
        int count = 0;
        if (currentPerson == null) System.out.println("To perform this currentPerson can not be null.");
        else if (person.getOwnedItems().isEmpty()) System.out.println("This person doesnt have any item.");
        else {
            try {
                for (Item each : person.getOwnedItems()) {
                    System.out.println("[" + count++ + "] " + each);
                }
                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > count)
                        System.out.println("please give number in range.");
                    else break;
                }
                return person.getOwnedItems().get(userInputInt);
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                userInput.next();
            } catch ( NoSuchElementException e) {
                System.out.println("No input provided. Please try again.");
            }
        }

        return null;
    }

    public void getApp() throws IOException {
        Scanner userInput = new Scanner(System.in);
        int userInputInt;
        boolean closePanel = false;


        System.out.println("\nWelcome to estate app. How can I help you today?\n");
        try {
            while (!closePanel) {
                System.out.println("\n\n=== Estate App Menu ===");
                System.out.println("Property Operations:");
                System.out.println("[0] Select Apartment || [1] Create Apartment");
                System.out.println("[2] Select Parking Place (from currentPerson) || [3] Select Parking Place (from all List)");
                System.out.println("[4] Create Parking Place || [5] Create Car (to currentPerson)");
                System.out.println("[6] Select Car (from currentPerson) || [7] Store Car (to currentParkingPlace)");
                System.out.println("[8] Create Item (to currentPerson) || [9] Select Item (from currentParkingPlace)");
                System.out.println("[10] Select Item (from currentRoom) || [11] Store Item (to currentParkingPlace)");
                System.out.println("[12] Store Item (to currentRoom) || [13] Select Item ( from All list ) ");
                System.out.println("[14] Select Item ( from currentPerson ) || [15] Remove Vehicle (from currentParkingPlace)");
                System.out.println("[16] Remove Item (from currentParkingPlace) || [17] Remove Item (from currentRoom)");


                System.out.println("\nRental Operations:");
                System.out.println("[18] Select Room (from currentPerson)");
                System.out.println("[19] Select Room (from all list) || [20] Rent Room (currentRoom to currentPerson)");
                System.out.println("[21] Rent Room (currentPerson to select among all rooms available) || [22] Rent Parking Place (currentApartment's)");
                System.out.println("[23] Check Out Room (select among currentPerson's Rented ) || [24] Check Out Parking Place ( select from currentPerson's rented )");

                System.out.println("\n Person Operations:");
                System.out.println("[25] Select Person ( from currentApartment ) || [26] Select Person ( from all list )");
                System.out.println("[27] Kick Someone From Room (currentPerson is taxResponsible) || [28] Create Person");
                System.out.println("[29] Save Person");

                System.out.println("\nAdditional Operations:");
                System.out.println("[30] Set currentPerson null || [31] Set currentRoom null");
                System.out.println("[32] Set currentApartment null || [33] Set currentItem null");
                System.out.println("[34] Set currentVehicle null  || [35] Set currentParkingPlace null ");
                System.out.println("[36] Set allObjects null || [37] Close Panel");
                System.out.println("[38] Exit Program ");


                System.out.println("\nCurrent Objects:");
                System.out.print("currentApartment = " + currentApartment);
                System.out.print(" | currentRoom = " + currentRoom);
                System.out.print(" | currentParkingPlace = " + currentParkingPlace);
                System.out.print("\ncurrentPerson = " + currentPerson);
                System.out.print(" | currentItem = " + currentItem);
                System.out.print(" | currentVehicle = " + currentVehicle + "\n");


                System.out.println("Your input --> ");


                while (true) {
                    userInputInt = userInput.nextInt();
                    if (userInputInt < 0 || userInputInt > 38)
                        System.out.println("Please write a number in the range.");
                    else
                        break;
                }

                switch (userInputInt) {
                    case 0:
                        currentApartment = selectApartment();
                        break;
                    case 1:
                        createApartment();
                        break;
                    case 2:
                        currentParkingPlace = selectParkingPlace(currentPerson);
                        break;
                    case 3:
                        currentParkingPlace = selectAllParkingPlaces();
                        break;
                    case 4:
                        createParkingPlace();
                        break;
                    case 5:
                        createVehicle();
                        break;
                    case 6:
                        currentVehicle = selectVehicle(currentPerson);
                        break;
                    case 7:
                        storeVehicle(currentVehicle, currentParkingPlace, currentPerson);

                        break;
                    case 8:
                        createItem();
                        break;
                    case 9:
                        currentItem = selectItemFromParkingPlace(currentParkingPlace);
                        break;
                    case 10:
                        currentItem = selectItemFromRoom(currentRoom);
                        break;
                    case 11:
                        storeItemToParkingPlace(currentItem, currentParkingPlace);

                        break;
                    case 12:
                        storeItemToRoom(currentItem, currentRoom);

                        break;
                    case 13:
                        currentItem = selectItemFromAllList();
                        break;
                    case 14:
                        currentItem = selectItemFromCurrentPerson(currentPerson);
                        break;
                    case 15:
                        removeVehicleFromCurrentParkingPlace(currentParkingPlace);
                        break;
                    case 16:
                        removeItemFromCurrentParkingPlace(currentParkingPlace);
                        break;
                    case 17:
                        removeItemFromCurrentRoom(currentRoom);
                        break;
                    case 18:
                        currentRoom = selectRoomCurrentPerson(currentPerson);
                        break;
                    case 19:
                        currentRoom = selectRoomAllList();
                        break;
                    case 20:
                        rentRoomCurrentPersontoCurrentRoom(currentPerson, currentRoom);

                        break;
                    case 21:
                        rentRoomCurrentPersontoAllRooms(currentPerson);

                        break;
                    case 22:
                        rentParkingPlaceFromCurrentApartment(currentPerson, currentApartment);

                        break;
                    case 23:
                        checkOutRoomFromRented(currentPerson);

                        break;
                    case 24:
                        checkOutPersonFromParkingPlace(currentPerson);

                        break;
                    case 25:
                        currentPerson = selectPersonFromApartment(currentApartment);
                        break;
                    case 26:
                        currentPerson = selectPerson();

                        break;
                    case 27:
                        kickSomeoneFromRoom(currentPerson);
                        break;
                    case 28:
                        createPerson();
                        break;
                    case 29:
                        savePerson(currentPerson);
                        break;
                    case 30:
                        currentPerson = null;
                        break;

                    case 31:
                        currentRoom = null;
                        break;

                    case 32:
                        currentApartment = null;
                        break;

                    case 33:
                        currentItem = null;
                        break;

                    case 34:
                        currentVehicle = null;
                        break;

                    case 35:
                        currentParkingPlace = null;
                        break;

                    case 36:
                        currentVehicle = null;
                        currentApartment = null;
                        currentRoom = null;
                        currentPerson = null;
                        currentItem = null;
                        currentParkingPlace = null;
                        break;

                    case 37:
                        closePanel = true;
                        break;

                    case 38:
                        System.out.println("Thank you for choosing us :>");
                        System.exit(1);
                        break;

                    default:
                        System.out.println("Invalid input. Please enter a valid option.");
                        break;
                }
            }


        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid integer.");
            getApp();
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception.");
            getApp();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds exception.");
            getApp();
        }


    }


}

