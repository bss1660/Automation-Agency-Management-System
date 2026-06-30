// ==========================================
// 1. ABSTRACT BASE CLASS (Abstraction)
// ==========================================
abstract class User {
    private String id;       // Encapsulation: private fields
    private String name;
    private String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters (Encapsulation)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // Polymorphic method to be overridden by subclasses
    public abstract void displayDashboard();
}