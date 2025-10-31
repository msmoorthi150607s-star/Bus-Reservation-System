import java.time.LocalDate;
import java.util.*;

public class Bus {
    private String busName;
    private String busNo;
    private String route;
    private String ticketPricing;
    private String driver;
    private String driverNo;
    private String timing;
    private int seatsAvailable;
    private int totalSeats;
    private LocalDate travelDate;
    private String type;

    private HashSet<Integer> bookedSeats = new HashSet<>();

    // Constructor with date
    public Bus(String type ,String busName, String busNo, String route, String ticketPricing, String driver, String driverNo, String timing, int seatsAvailable, LocalDate travelDate) {
        this.busName = busName;
        this.busNo = busNo;
        this.route = route;
        this.ticketPricing = ticketPricing;
        this.driver = driver;
        this.driverNo = driverNo;
        this.timing = timing;
        this.seatsAvailable = seatsAvailable;
        this.totalSeats = seatsAvailable;
        this.travelDate = travelDate;
        this.type = type;
    }

    // Getters
    public String getBusName() { return busName; }
    public String getBusNo() { return busNo; }
    public String getRoute() { return route; }
    public String getTicketPricing() { return ticketPricing; }
    public String getDriver() { return driver; }
    public String getDriverNo() { return driverNo; }
    public String getTiming() { return timing; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public int getTotalSeats() { return totalSeats; }
    public LocalDate getTravelDate() { return travelDate; }
    public HashSet<Integer> getBookedSeats() { return bookedSeats; }

    // Setters
    public void setBusName(String busName) { this.busName = busName; }
    public void setBusNo(String busNo) { this.busNo = busNo; }
    public void setRoute(String route) { this.route = route; }
    public void setTicketPricing(String ticketPricing) { this.ticketPricing = ticketPricing; }
    public void setDriver(String driver) { this.driver = driver; }
    public void setDriverNo(String driverNo) { this.driverNo = driverNo; }
    public void setTiming(String timing) { this.timing = timing; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }

    // Display method
    public void displayBusDetails() {
        System.out.println("Bus Name: " + busName);
        System.out.println("Bus Number: " + busNo);
        System.out.println("Bus type : "+type);
        System.out.println("Route: " + route);
        System.out.println("Ticket Pricing: " + ticketPricing);
        System.out.println("Driver: " + driver);
        System.out.println("Driver Number: " + driverNo);
        System.out.println("Timing: " + timing);
        System.out.println("Seats Available: " + seatsAvailable);
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Travel Date: " + travelDate);
    }

    // Book a specific seat
    public boolean bookSpecificSeat(int seat) {
        if (seat < 1 || seat > totalSeats) {
            System.out.println("\nInvalid seat number. Please select between 1 and " + totalSeats);
            return false;
        } else if (bookedSeats.contains(seat)) {
            System.out.println("\nSeat " + seat + " is already booked.");
            return false;
        } else {
            bookedSeats.add(seat);
            seatsAvailable--;
            System.out.println("\nSeat " + seat + " booked successfully.");
            return true;
        }
    }

    // Cancel a specific seat
    public boolean cancelSpecificSeat(int seat) {
        if (bookedSeats.contains(seat)) {
            bookedSeats.remove(seat);
            seatsAvailable++;
            System.out.println("\nSeat " + seat + " cancelled successfully.");
            return true;
        } else {
            System.out.println("\nSeat " + seat + " is not booked.");
            return false;
        }
    }

    // List available seats
    public List<Integer> availableSeats() {
        List<Integer> available = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            if (!bookedSeats.contains(i)) {
                available.add(i);
            }
        }
        return available;
    }
}


