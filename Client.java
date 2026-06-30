// ==========================================
// 2. CLIENT CLASS (Inheritance)
// ==========================================
class Client extends User {
    private String company;
    private String phoneNumber;

    public Client(String id, String name, String company, String email, String phoneNumber) {
        super(id, name, email); // Invoking parent constructor
        this.company = company;
        this.phoneNumber = phoneNumber;
    }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public void displayDashboard() {
        System.out.println("\n--- Client Portal: " + getName() + " [" + company + "] ---");
        System.out.println("Status: Active Client. Please contact support for changes.");
    }
}
