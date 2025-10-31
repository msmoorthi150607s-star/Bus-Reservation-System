
// set bookig details 
// get customer details
class Bookings {
    private String BookingId;
    private String CustomerName;
    private String BusNo;
    private int SeatNo;
    private String BookingDate;
    private String CustomerPhone;
    private String CustomerEmail;

    public Bookings(String BookingId, String name, String busno, int seatno, String bookingdt, String phone, String email) {
        this.BookingId = BookingId;
        this.CustomerName = name;
        this.BusNo = busno;
        this.SeatNo = seatno;
        this.BookingDate = bookingdt;
        this.CustomerPhone = phone;
        this.CustomerEmail = email;
    }

    // Getters
    public String getBusNo() { return BusNo; }
    public int getseatno() { return SeatNo; }
    public String getbookingid() { return BookingId; }
    public String getbookingdate() { return BookingDate; }
    public String getCustomerName() { return CustomerName; }
    public String getCustomerPhone() { return CustomerPhone; }
    public String getCustomerEmail() { return CustomerEmail; }

    // Setters
    public void setBusNo(String BusNo) { this.BusNo = BusNo; }
    public void setseatno(int seatno) { this.SeatNo = seatno; }
    public void setbookingid(String bookid) { this.BookingId = bookid; }
    public void setbookingdate(String bookdate) { this.BookingDate = bookdate; }
    public void setCustomerName(String name) { this.CustomerName = name; }
    public void setCustomerPhone(String phone) { this.CustomerPhone = phone; }
    public void setCustomerEmail(String email) { this.CustomerEmail = email; }
}
