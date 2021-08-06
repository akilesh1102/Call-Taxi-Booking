# Call-Taxi-Booking
This application consists of two modules which mainly focuses on booking taxi.

Taxi.java file consists of declaration of variables necessary for application , Setting the data to the respective variables and printing the list consists of trip details of booked taxi. Here we initialized variables using constructor. 

setDetails()   -> sets the values 

printDetails() -> prints the information about all the trip details.
___________________________________________________________________________________________________________________________________________________________

Booking.java is the main module where all the booking operation takes place.The operations are explained function by function.

main()-> The main function consists of code which is needed for taking input from the console to book a new taxi and to show the trip details of all taxis. Here I used              switch case for those two operations. Since in our problem statement only 5 points are allowed (A B C D E) , hence those exceptions are also handled if the user enters other than this locations.

createTaxis()-> This function is responsible for creating number of taxis. Here in our application we have assumed as 5.

getFreeTaxis()-> Here this function returns the free taxis available for customer at the instant of time.

bookTaxi()-> This is the main function of program. Here the booking process takes place by various operations and stores all values in string which will be returned to Taxi module for printing all trip details. 
