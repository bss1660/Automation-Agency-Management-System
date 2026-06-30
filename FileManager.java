import java.io.*;
import java.util.ArrayList;
import java.util.List;


// ==========================================
// 5. FILE MANAGER (Robust File Handling)
// ==========================================
class FileManager {
    private static final String CLIENTS_FILE = "clients.txt";
    private static final String EMPLOYEES_FILE = "employees.txt";
    private static final String PROJECTS_FILE = "projects.txt";
    private static final String INVOICES_FILE = "invoices.txt";

    // Create files if they do not exist
    public static void initializeFiles() {
        try {
            new File(CLIENTS_FILE).createNewFile();
            new File(EMPLOYEES_FILE).createNewFile();
            new File(PROJECTS_FILE).createNewFile();
            new File(INVOICES_FILE).createNewFile();
        } catch (IOException e) {
            System.out.println("Error initializing data storage files: " + e.getMessage());
        }
    }

    // --- CLIENT FILE OPERATIONS ---
    public static void saveClients(List<Client> clients) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CLIENTS_FILE))) {
            for (Client c : clients) {
                writer.println(c.getId() + "|" + c.getName() + "|" + c.getCompany() + "|" + c.getEmail() + "|" + c.getPhoneNumber());
            }
        } catch (IOException e) {
            System.out.println("Error saving client records: " + e.getMessage());
        }
    }

    public static List<Client> loadClients() {
        List<Client> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    list.add(new Client(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (FileNotFoundException e) {
            // Handled quietly during initialization
        } catch (IOException e) {
            System.out.println("Error reading client records: " + e.getMessage());
        }
        return list;
    }

    // --- EMPLOYEE FILE OPERATIONS ---
    public static void saveEmployees(List<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(EMPLOYEES_FILE))) {
            for (Employee e : employees) {
                writer.println(e.getId() + "|" + e.getName() + "|" + e.getRole() + "|" + e.getSalary() + "|" + e.getAssignedProjects());
            }
        } catch (IOException e) {
            System.out.println("Error saving employee records: " + e.getMessage());
        }
    }

    public static List<Employee> loadEmployees() {
        List<Employee> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    list.add(new Employee(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), parts[4]));
                }
            }
        } catch (Exception e) {
            // Handle parsing/IO issues gracefully
        }
        return list;
    }

    // --- PROJECT FILE OPERATIONS ---
    public static void saveProjects(List<Project> projects) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PROJECTS_FILE))) {
            for (Project p : projects) {
                writer.println(p.getProjectId() + "|" + p.getClientId() + "|" + p.getServiceType() + "|" + p.getAssignedEmployeeId() + "|" + p.getStatus());
            }
        } catch (IOException e) {
            System.out.println("Error saving project records: " + e.getMessage());
        }
    }

    public static List<Project> loadProjects() {
        List<Project> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PROJECTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    list.add(new Project(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (Exception e) {
            // Graceful capture
        }
        return list;
    }

    // --- INVOICE FILE OPERATIONS ---
    public static void saveInvoices(List<Invoice> invoices) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(INVOICES_FILE))) {
            for (Invoice i : invoices) {
                writer.println(i.getInvoiceId() + "|" + i.getProjectId() + "|" + i.getAmount() + "|" + i.getStatus());
            }
        } catch (IOException e) {
            System.out.println("Error saving invoices: " + e.getMessage());
        }
    }

    public static List<Invoice> loadInvoices() {
        List<Invoice> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INVOICES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    list.add(new Invoice(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
                }
            }
        } catch (Exception e) {
            // Graceful capture
        }
        return list;
    }
}
