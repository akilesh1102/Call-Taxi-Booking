package com.akilesh;

// module for setting and getting taxi details
import java.util.*;

public class Taxi { // declaring all necessary variables for taxi booking
    static int taxiCount = 0;
    int taxiId;
    char currentLocation;
    boolean isBooked;
    int timeOff;
    int totalTakings;
    List<String> trips; // creating list called trips to store the details of the booking

    public Taxi() { // constructor for initializing all variables
        isBooked = false;
        currentLocation = 'A'; // As per the problem statement we should assume taxi's initial location is 'A'
        timeOff = 6; // assuming the free time as 6:00
        totalTakings = 0;
        trips = new ArrayList<String>();
        taxiCount = taxiCount + 1; // new id is assigned for every time new taxi is created
        taxiId = taxiCount;
    }

    public void setDetails(boolean isBooked, char currentLocation, int timeOff, int totalTakings, String tripDetail) { // function
                                                                                                                       // for
                                                                                                                       // setting
                                                                                                                       // data
                                                                                                                       // to
                                                                                                                       // the
                                                                                                                       // variables
        this.isBooked = isBooked;
        this.currentLocation = currentLocation;
        this.timeOff = timeOff;
        this.totalTakings = totalTakings;
        this.trips.add(tripDetail);
    }

    public void printDetails() { // function for printing the details of the trip
        System.out.println("Travel History of Taxi - : " + this.taxiId);
        System.out.println("");
        // System.out.println("| CUSTOMER-ID | FROM | TO | PICKUP-TIME | DROP-TIME |
        // AMOUNT |");
        for (String trip : trips) {
            System.out.println(trip);
        }
        System.out.println(
                "____________________________________________________________________________________________________________");
        System.out.println("");
    }
}