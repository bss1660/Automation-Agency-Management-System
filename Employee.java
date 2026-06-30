// ==========================================
// 3. EMPLOYEE CLASS (Inheritance)
// ==========================================
class Employee extends User {
    private String role;
    private double salary;
    private String assignedProjects; // Comma-separated IDs

    public Employee(String id, String name, String role, double salary, String assignedProjects) {
        super(id, name, emailPlaceholder(name));
        this.role = role;
        this.salary = salary;
        this.assignedProjects = assignedProjects.isEmpty() ? "None" : assignedProjects;
    }

    private static String emailPlaceholder(String name) {
        return name.toLowerCase().replace(" ", "") + "@agency.com";
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public String getAssignedProjects() { return assignedProjects; }
    public void setAssignedProjects(String assignedProjects) { this.assignedProjects = assignedProjects; }

    @Override
    public void displayDashboard() {
        System.out.println("\n=================================");
        System.out.println("       EMPLOYEE DASHBOARD        ");
        System.out.println("=================================");
        System.out.println("Welcome, " + getName() + " (" + role + ")");
        System.out.println("Assigned Projects: " + assignedProjects);
        System.out.println("=================================");
    }
}