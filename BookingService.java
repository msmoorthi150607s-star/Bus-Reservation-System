import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BookingService {
    // Get receipt from the Bookings class, and we will store the receipt in a list.
    private ArrayList<Bookings> bookingList = new ArrayList<>();

    // Arraylist to store all cancellations
    private ArrayList<Bookings> cancellationList = new ArrayList<>();

    //get bus name for future use
    

    // Add a booking

    // We need bus details, so we pass the HashMap which contains bus numbers as keys and Bus objects as values.
    public void AddBooking(HashMap<String, Bus> busmap) {
        Scanner in = new Scanner(System.in);

        // Ask the customer for their name, email, phone number, and bus number to book.
        System.out.println("Enter your name:");
        String name = in.nextLine();

        System.out.println("Enter your mobile number:");
        String Mnumber = in.nextLine();

        System.out.println("Enter your email ID:");
        String email = in.nextLine();

        System.out.println("Enter your bus number (If you don't know your bus number, please check Bus Info):");
        String busnumber = in.nextLine();

        // Access the bus using the bus number from the busmap
        Bus bus = busmap.get(busnumber);

        if (bus != null) {
            // Get available seats from the bus
            List<Integer> availableSeats = bus.availableSeats();

            if (availableSeats.isEmpty()) {
                System.out.println("Sorry, no seats are available in this bus.");
            } else {
                System.out.println("Available seats in this bus are: " + availableSeats);
                System.out.println("Enter seat number to book:");
                int seatnumber = in.nextInt();
                in.nextLine(); // Consume leftover newline

                // Check if the seat is valid before booking
                if (!availableSeats.contains(seatnumber)) {
                    System.out.println("Invalid seat number. Please choose from available seats.");
                    return;
                }

                // Try to book the specific seat
                if (bus.bookSpecificSeat(seatnumber)) {
                    String bookingId = UUID.randomUUID().toString(); // Generate unique booking ID
                    String bookingDate = java.time.LocalDate.now().toString(); // Get current date

                    // Create a booking object with all details
                    Bookings booking = new Bookings(bookingId, name, busnumber, seatnumber, bookingDate, Mnumber, email);

                    // Show receipt of booking
                    System.out.println("\nBooking Receipt:");
                    System.out.println("Customer Name: " + name);
                    System.out.println("Bus Name: " + bus.getBusName());
                    System.out.println("Bus Number: " + busnumber);
                    System.out.println("Seat Number: " + seatnumber);
                    System.out.println("Booking successful! Your booking ID is: " + bookingId);
                    System.out.println("Booking Date: " + bookingDate);
                    System.out.println("Mobile Number: " + Mnumber);
                    System.out.println("Email ID: " + email);

                    // Add booking to the list
                    bookingList.add(booking);
                } else {
                    System.out.println("Seat number " + seatnumber + " is already booked. Please choose a different seat.");
                }
            }
        } else {
            System.out.println("Invalid bus number. Please check and try again.");
        }
    }

    // it helps to admin to view all bookings
    // getters are used to access booking details
    public void ViewBookings() {
        if (bookingList.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
            System.out.println("\nAll Bookings:");
            for (Bookings booking : bookingList) {
                System.out.println("Booking ID: " + booking.getbookingid());
                System.out.println("Customer Name: " + booking.getCustomerName());
                System.out.println("Bus Number: " + booking.getBusNo());
                System.out.println("Seat Number: " + booking.getseatno());
                System.out.println("Booking Date: " + booking.getbookingdate());
                System.out.println("Customer Phone: " + booking.getCustomerPhone());
                System.out.println("Customer Email: " + booking.getCustomerEmail());
                System.out.println("Please note down your Booking ID for future reference.(It may be needed for cancellation)");
                System.out.println("---------------------------");
            }
        }
    }

    public void ViewCustomerDetails() {
        if (bookingList.isEmpty()) {
            System.out.println("No customer details available.");
        } else {
            System.out.println("\nCustomer Details:");
            for (Bookings booking : bookingList) {
                System.out.println("Customer Name: " + booking.getCustomerName());
                System.out.println("Customer Phone: " + booking.getCustomerPhone());
                System.out.println("Customer Email: " + booking.getCustomerEmail());
                System.out.println("---------------------------");
            }
        }
    }
// admin can clear all bookingsKOKO
    public void ClearBookings() {
        bookingList.clear();
        System.out.println("All bookings have been cleared.");
    }

    public void cancelBooking(HashMap<String, Bus> busmap) 
    {
        System.out.println("Enter Booking ID to cancel :");
        Scanner in = new Scanner(System.in);
        String bkid = in.nextLine();
        Bookings bookingToCancel = null; // it helps to find booking to cancel
        for (Bookings booking : bookingList) {
            if (booking.getbookingid().equals(bkid)) {
                bookingToCancel = booking;
                break;
                // traverse through booking list to find the booking
            }
        }

        if(bookingToCancel != null)
        {
            // Access the bus using the bus number from the busmap
            String busno = bookingToCancel.getBusNo(); // link booking to bus number
            int seatno = bookingToCancel.getseatno(); // link  booking to seat number

            // get bus object from busmap
            Bus bus = busmap.get(busno);

            if(bus != null && bus.cancelSpecificSeat(seatno))
            {
                bookingList.remove(bookingToCancel);
                System.out.println("Booking with ID " + bkid + " has been cancelled successfully.");
                // Add to cancellation list
                cancellationList.add(bookingToCancel);
            }

            else
            {
                System.out.println(" Please ensure your booking id is correct.");
            }
                
        }

        else
        {
            System.out.println("Booking ID not found. Cancellation failed.");
        }
    }

    public void ViewCancellations() {
        if (cancellationList.isEmpty()) {
            System.out.println("No cancellations available.");
        } else {
            System.out.println("\nAll Cancellations:");
            for (Bookings booking : cancellationList) {
                System.out.println("Booking ID: " + booking.getbookingid());
                System.out.println("Customer Name: " + booking.getCustomerName());
                System.out.println("Bus Number: " + booking.getBusNo());
                System.out.println("Seat Number: " + booking.getseatno());
                System.out.println("Booking Date: " + booking.getbookingdate());
                System.out.println("Customer Phone: " + booking.getCustomerPhone());
                System.out.println("Customer Email: " + booking.getCustomerEmail());
                System.out.println("----------------------------");
            }
        }
    }

    public void exportBookingsToFile(String filename) {
        // Implementation for exporting bookings to a file
        System.out.println("Exporting bookings to file: " + filename);
        // Code to write bookingList to the specified file
        // import bus map to get bus name
        HashMap<String, Bus> busmap = new HashMap<>();

        try (FileWriter write = new FileWriter(filename)) 
        {

            for(Bookings bookings : bookingList)
            {
                write.write("=================================");
                write.write("Booking ID: " + bookings.getbookingid() + "\n");
                write.write("Customer Name: " + bookings.getCustomerName() + "\n");
                write.write("Bus Number: " + bookings.getBusNo() + "\n");
                write.write("Seat Number: " + bookings.getseatno() + "\n");
                write.write("Booking Date: " + bookings.getbookingdate() + "\n");
                write.write("==================================");
            }

            System.out.println("Bookings exported successfully to " + filename);
        } catch (IOException e) {
            
            System.out.println("An error occurred while exporting bookings: "+e.getMessage()); // e.getMessage() provides details about the exception
        }
    }

    public void exportCancellationsToFile(String filename) {
        // Implementation for exporting cancellations to a file
        System.out.println("Exporting cancellations to file: " + filename);
        // Code to write cancellationList to the specified file

        try (FileWriter write = new FileWriter(filename)) 
        {

            for(Bookings bookings : cancellationList)
            {
                System.out.println("=================================");
                write.write("Booking ID: " + bookings.getbookingid() + "\n");
                write.write("Customer Name: " + bookings.getCustomerName() + "\n");
                write.write("Bus Number: " + bookings.getBusNo() + "\n");
                write.write("Seat Number: " + bookings.getseatno() + "\n");
                write.write("Booking Date: " + bookings.getbookingdate() + "\n");
                System.out.println("==================================");
            }

            System.out.println("Cancellations exported successfully to " + filename);
        } catch (IOException e) {
            
            System.out.println("An error occurred while exporting cancellations: "+e.getMessage()); // e.getMessage() provides details about the exception
        }
    }

    // Optional: Getter for booking list if needed elsewhere
    public ArrayList<Bookings> getBookingList() {
        return bookingList;
    }
}
