package com.akilesh;

// module for booking the call taxi
import java.util.*;

public class Booking {

    public static List<Taxi> createTaxis(int n) { // returns the number of taxis as Taxis list
        List<Taxi> taxis = new ArrayList<Taxi>(); // creating array list of type Taxi
        for (int i = 1; i <= n; i++) {
            Taxi t = new Taxi();
            taxis.add(t);
        }
        return taxis;
    }

    public static List<Taxi> getFreeTaxis(List<Taxi> taxis, int pickupTime, char pickupPoint) { // getting taxis
                                                                                                // available at that
                                                                                                // time
        List<Taxi> freeTaxis = new ArrayList<Taxi>();
        for (Taxi t : taxis) {
            if ((t.timeOff <= pickupTime)
                    && (Math.abs(t.currentLocation - '0') - pickupPoint - '0') <= pickupTime - t.timeOff) { // taxi
                // should
                // be
                // free.
                // taxi
                // should
                // have
                // enough
                // time
                // to
                // reach the
                // customer
                // before
                // pickuptime
                freeTaxis.add(t);
            }
        }
        return freeTaxis;
    }

    public static void bookTaxi(int customerID, char pickupPoint, char dropPoint, int pickupTime, List<Taxi> freeTaxis) // function
                                                                                                                        // responsible
                                                                                                                        // for
                                                                                                                        // booking
                                                                                                                        // taxis
    {
        int min = 2021; // setting min as random value to find distance
        int distanceBetweenPickupAndDrop = 0;
        int earning = 0;
        int nextFreeTime = 0;
        char nextSpot = 'Z';
        Taxi bookedTaxi = null;
        String tripDetail = "";
        for (Taxi t : freeTaxis) {
            int distanceBetweenCustomerAndTaxi = Math.abs((t.currentLocation) - pickupPoint) * 15;
            if (distanceBetweenCustomerAndTaxi < min) {
                bookedTaxi = t;
                distanceBetweenPickupAndDrop = Math.abs(dropPoint - pickupPoint) * 15;
                earning = (distanceBetweenPickupAndDrop - 5) * 10 + 50; // first 5kms 50rs and
                                                                        // for next subsequent kms 10rs each
                int dropTime = pickupTime;
                int droppTime = pickupTime + 1;
                if (distanceBetweenPickupAndDrop < 60) {
                    String time = Integer.toString(dropTime) + "." + Integer.toString(distanceBetweenPickupAndDrop);
                    nextFreeTime = dropTime;
                    nextSpot = dropPoint;
                    tripDetail = "CUSTOMER ID - " + customerID + "   PICKUP POINT - " + pickupPoint + "   DROP POINT - "
                            + dropPoint + "   PICKUP TIME - " + pickupTime + ".00" + "   DROP TIME - " + time + ""
                            + "   AMOUNT - " + earning;
                } else {
                    tripDetail = "CUSTOMER ID - " + customerID + "   PICKUP POINT - " + pickupPoint + "   DROP POINT - "
                            + dropPoint + "   PICKUP TIME - " + pickupTime + ".00" + "   DROP TIME - " + droppTime
                            + ".00" + "   AMOUNT - " + earning;

                }

            }
        }
        bookedTaxi.setDetails(true, nextSpot, nextFreeTime, bookedTaxi.totalTakings + earning, tripDetail); // calling
                                                                                                            // function
                                                                                                            // with
                                                                                                            // updated
                                                                                                            // datas
        System.out.println("");
        System.out.println("BOOKING ID   -> " + customerID);
        System.out.println("ALLOTED TAXI -> TAXI-" + bookedTaxi.taxiId);

    }

    public static void main(String[] args) { // main function for base operations
        List<Taxi> taxis = createTaxis(5); // assuming total number of taxis as 5
        int id = 1; // initaial customer id
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("");
            System.out.println("Enter 'Book' -> Book a Taxi "); // Book for booking taxi
            System.out.println("Enter 'Show' -> Print the Taxi details"); // Show for displaying the taxi details

            String choice = sc.next(); // Book or Show
            switch (choice) {
                case "Book": {
                    System.out.print("Enter the customer ID  -> ");
                    int ID = sc.nextInt();
                    System.out.print("Enter the Pickup Point -> ");
                    char pickupPoint = sc.next().charAt(0); // pickup Point(A-E)
                    System.out.print("Enter the Drop Point   -> ");
                    char dropPoint = sc.next().charAt(0); // drop Point(A-E)
                    System.out.print("Enter the Pickup Time  -> ");
                    int pickupTime = sc.nextInt(); // pickuptime

                    if (pickupPoint < 'A' || pickupPoint > 'E' || dropPoint < 'A' || dropPoint > 'E') { // condition for
                                                                                                        // ensuring
                                                                                                        // pointes
                                                                                                        // between A and
                                                                                                        // E
                        System.out.println("Please enter the valid pickup and drop points such as A B C D E");
                        System.out.println("...........");
                        System.out.println("........");
                        System.out.println(".....");
                        return;
                    }
                    List<Taxi> freeTaxis = getFreeTaxis(taxis, pickupTime, pickupPoint); // getting free taxis that can
                                                                                         // reach customer on or before
                                                                                         // pickup time
                    if (freeTaxis.size() == 0) { // if all taxis are busy
                        System.out.println("No Taxi can be allocated");
                        System.out.println("............");
                        System.out.println(".......");
                        return;
                    }
                    Collections.sort(freeTaxis, (a, b) -> a.totalTakings - b.totalTakings); // sorting the taxis based
                                                                                            // on the earning of the
                                                                                            // taxis

                    bookTaxi(id, pickupPoint, dropPoint, pickupTime, freeTaxis);
                    id++;

                    break;
                }

                case "Show": {
                    for (Taxi t : taxis)
                        t.printDetails();
                    break;
                }
                default:
                    return;
            }
        }
    }
}