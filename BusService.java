import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Scanner;

public class BusService {
    private HashMap<String, Bus> busmap = new HashMap<>();
    private Scanner in = new Scanner(System.in);

    public void addbus() {
        System.out.println("Enter Bus Name:");
        String name = in.nextLine();

        System.out.println("Enter Bus Number:");
        String number = in.nextLine();

        System.out.println("Enter route (format: source-destination):");
        String route = in.nextLine();

        System.out.println("Enter total seats:");
        int totalseats = in.nextInt();
        in.nextLine(); // consume leftover newline

        System.out.println("Enter bus type (AC or Non-AC):");
        String type = in.nextLine();

        System.out.println("Enter fare:");
        double fare = in.nextDouble();
        in.nextLine(); // consume leftover newline

        System.out.println("Enter driver name:");
        String drivername = in.nextLine();

        System.out.println("Enter driver number:");
        String driverNo = in.nextLine();
        in.nextLine(); // consume leftover newline

        System.out.println("Enter timing (e.g., 10:00 AM):");
        String timing = in.nextLine();

        System.out.println("Enter travel date (format: yyyy-MM-dd):");
        String dateInput = in.nextLine();
        LocalDate travelDate;

        try {
            travelDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("❌ Invalid date format. Using today's date instead.");
            travelDate = LocalDate.now();
        }

        // Constructor: Bus(String busName, String busNo, String route, String ticketPricing, String driver, int driverNo, String timing, int seatsAvailable, LocalDate travelDate)
        Bus bus = new Bus(type ,name, number, route, String.valueOf(fare), drivername, driverNo, timing, totalseats, travelDate);
        busmap.put(number, bus);

        System.out.println(" Bus added successfully!");
    }

    public void removebus()
    {
        System.out.println("Enter Bus Number to remove:");
        String busNumber = in.nextLine();

        if(busmap.isEmpty())
        {
            System.out.println("❌ No buses available to remove.");
            return;
        }

        else
        {
            if(busmap.containsKey(busNumber))
            {
                busmap.remove(busNumber);
                System.out.println(" Bus removed successfully!");
            }
            else
            {
                System.out.println("❌ Bus with number " + busNumber + " not found.");
            }
        }

    }

    void displayBusInfo()
    {
        if(busmap.isEmpty())
        {
            System.out.println("❌ No buses available to display.");
            return;
        }

        else
        {
            for (Bus bus : busmap.values()) {
                bus.displayBusDetails();
                System.out.println("---------------------------");
            }
        }
    }

    public void searchByRouteAndDate(String route, LocalDate date) {
    boolean found = false;
    for (Bus bus : busmap.values()) {
        if (bus.getRoute().equalsIgnoreCase(route) && bus.getTravelDate().equals(date)) {
            bus.displayBusDetails();
            System.out.println("---------------------------");
            found = true;
        }
    }
    if (!found) {
        System.out.println("❌ No buses found for route " + route + " on " + date);
    }
}




    public HashMap<String, Bus> getBusmap() {
        return busmap;
    }

}

