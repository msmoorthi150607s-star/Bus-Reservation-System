import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// Menu services — contains both customer and admin services
public class MenuService {

    BusService buservice = new BusService(); // Bus-related operations
    BookingService bookingservice = new BookingService(); // Booking-related operations

    Scanner in = new Scanner(System.in);
    private int choice;

    // Admin menu — provides all admin-level services
    public void ShowAdminMenu() {
        System.out.println("1. Add a Bus");
        System.out.println("2. Remove a Bus");
        System.out.println("3. View Bus Info");
        System.out.println("4. View Booking List");
        System.out.println("5. View Cancellation List");
        System.out.println("6. View Customer Details");
        System.out.println("7. Clear Booking Data");
        System.out.println("8.Export booking details to a file");
        System.out.println("9.Export cancellation details to a file");
        System.out.println("What service do you want, Admin? :");

        choice = in.nextInt();
        in.nextLine(); // Consume leftover newline

        switch (choice) {
            case 1:
                buservice.addbus();
                break;

            case 2:
                buservice.removebus();
                break;

            case 3:
                buservice.displayBusInfo();
                break;

            case 4:
                bookingservice.ViewBookings(); // View booking list
                break;

            case 5:
                bookingservice.ViewCancellations(); // View cancellation list
                break;

            case 6:
                bookingservice.ViewCustomerDetails(); // View customer contact info
                break;

            case 7:
                bookingservice.ClearBookings(); // Clear all booking data
                break;

            case 8:
              System.out.println("Enter filename to export booking details(must put extension like (.txt , .csv)):");
              String BookingFilename = in.nextLine();
              
              bookingservice.exportBookingsToFile(BookingFilename);

            case 9:
              System.out.println("Enter filename to export cancellation details(must put extenion like(.txt , .csv)):");
              String CancelFilename = in.nextLine();
              bookingservice.exportCancellationsToFile(CancelFilename);
              break;

            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    // Customer menu — provides all customer-level services
    public void ShowCustomerMenu() {
        System.out.println("1. View Bus Info");
        System.out.println("2. Book a Seat");
        System.out.println("3. Cancel Booking");
        System.out.println("4. Search your bus by route and travel date (YYYY-MM-DD)");
        System.out.println("5. Customer Care");
        System.out.println("What service do you want, Customer? :");

        choice = in.nextInt();
        in.nextLine(); // Consume leftover newline

        switch (choice) {
            case 1:
                buservice.displayBusInfo();
                break;

            case 2:
                
                bookingservice.AddBooking(buservice.getBusmap());
                break;

            case 3:
                bookingservice.cancelBooking(buservice.getBusmap());
                break;

            case 4:
                System.out.println("Enter route (source - destination):");
                String searchRoute = in.nextLine();

                System.out.println("Enter travel date (YYYY-MM-DD):");
                try {
                    LocalDate searchDate = LocalDate.parse(in.nextLine());
                    buservice.searchByRouteAndDate(searchRoute, searchDate);
                } catch (DateTimeParseException e) {
                    System.out.println("❌ Invalid date format. Please use YYYY-MM-DD.");
                }
                break;

            case 5:
                System.out.println("📞 For customer care, please contact us at +91 123456789");
                System.out.println("📧 Or email us at msmoorthi150607s@gmail.com");
                break;

            default:
                System.out.println("Invalid choice!");
                break;
        }
    }
}
