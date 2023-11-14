import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class checkRentals extends Thread {
    private List<Room> rooms = new ArrayList<>();
    private Panel panel = new Panel();



    private Scanner userInput = new Scanner(System.in);

    public void setRooms(List<Apartment> apartments) {
        for (Apartment each : apartments) rooms.addAll(each.getRooms());
    }

    public boolean checkRooms() {
        int count = 0;
        for (Room each : rooms) {
            if (each.getRoomOwner() == null) count++;
        }
        return count == rooms.size();
    }

    public void callStartProgram() {
        panel.startProgram();
    }

    public void run() {
        int count = 0;




        while (true) {
            try {

                Thread.sleep(5000);

                if (checkRooms()) {
                    System.out.println("no one left. to continue please add people.");
                    panel.getApp();
                }
                if (count % 2 == 0 && count != 0) {

                    System.out.println("Would you like to open panel? [1] YES [0] NO ");
                    int userInputInt;
                    while (true) {
                         userInputInt = userInput.nextInt();
                        if (userInputInt > 1 || userInputInt < 0) System.out.println("Write number in range.");
                        else break;
                    }
                    if ( userInputInt == 1) panel.getApp();


                    for (Room each : rooms) {
                        if (each.getRoomOwner() == null) {
                            continue;
                        }
                        System.out.println("Checking rent for room ID = " + each.getID() + "...");
                        if (each.getDaysPassed() > 30) {
                            System.out.println("Total days passed = " + each.getDaysPassed() +
                                    "\n[Tenant = " + each.getRoomOwner().getName() + "] is kicked from room.");
                            each.kickRoomOwner(each.getRoomOwner());
                        } else if (each.getDaysPassed() % 2 == 0) {
                            each.renewContract();
                        }
                        each.skipDay();

                    }
                } else {
                    for (Room each : rooms) {
                        if (each.getRoomOwner() == null) {
                            System.out.println("this room doesnt have any tenant. ID = " + each.getID());
                            continue;
                        }
                        each.skipDay();
                        System.out.println("day skipped for room ID = " + each.getID());
                    }
                }
                System.out.println();
                count++;

            }  catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (InputMismatchException e) {
                System.out.println("please enter valid input.");
                userInput.next();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
