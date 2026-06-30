import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ==========================================
// 7. CORE AGENCY MANAGER & LOGIC
// ==========================================
class AgencyManager {
    private List<Client> clients;
    private List<Employee> employees;
    private List<Project> projects;
    private List<Invoice> invoices;
    private List<ServicePackage> services;

    public AgencyManager() {
        FileManager.initializeFiles();
        clients = FileManager.loadClients();
        employees = FileManager.loadEmployees();
        projects = FileManager.loadProjects();
        invoices = FileManager.loadInvoices();

        services = new ArrayList<>();
        services.add(new ServicePackage("Chatbot Automation"));
        services.add(new ServicePackage("Email Automation"));
        services.add(new ServicePackage("CRM Automation"));
        services.add(new ServicePackage("WhatsApp Automation"));
        services.add(new ServicePackage("AI Customer Support"));
    }

    // --- VALIDATION HELPERS ---
    private boolean isIdDuplicate(String id, String entityType) {
        if (entityType.equals("client")) {
            return clients.stream().anyMatch(c -> c.getId().equalsIgnoreCase(id));
        } else if (entityType.equals("employee")) {
            return employees.stream().anyMatch(e -> e.getId().equalsIgnoreCase(id));
        } else if (entityType.equals("project")) {
            return projects.stream().anyMatch(p -> p.getProjectId().equalsIgnoreCase(id));
        } else if (entityType.equals("invoice")) {
            return invoices.stream().anyMatch(i -> i.getInvoiceId().equalsIgnoreCase(id));
        }
        return false;
    }

    private String getValidInput(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String val = sc.nextLine().trim();
            if (!val.isEmpty() && !val.contains("|")) {
                return val;
            }
            System.out.println("Invalid Entry. Fields cannot be empty or contain '|'.");
        }
    }

    private double getValidDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double val = Double.parseDouble(sc.nextLine().trim());
                if (val >= 0) return val;
                System.out.println("Value must be a positive numeric value.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Please enter a valid decimal number.");
            }
        }
    }

    // --- CRUD OPERATIONS ---
    public void manageClients(Scanner sc) {
        while (true) {
            System.out.println("\n--- Client Operations Menu ---");
            System.out.println("1. Add Client\n2. View Clients\n3. Update Client\n4. Delete Client\n5. Return");
            System.out.print("Select choice: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                String id = getValidInput(sc, "Enter unique Client ID: ");
                if (isIdDuplicate(id, "client")) { System.out.println("Error: ID already exists."); continue; }
                String name = getValidInput(sc, "Enter Client Name: ");
                String comp = getValidInput(sc, "Enter Company Title: ");
                String mail = getValidInput(sc, "Enter Email Address: ");
                String phone = getValidInput(sc, "Enter Phone Number: ");

                clients.add(new Client(id, name, comp, mail, phone));
                FileManager.saveClients(clients);
                System.out.println("Client saved successfully.");
            } else if (choice.equals("2")) {
                if (clients.isEmpty()) System.out.println("No client data recorded.");
                for (Client c : clients) {
                    System.out.println("[" + c.getId() + "] " + c.getName() + " - " + c.getCompany() + " (" + c.getPhoneNumber() + ")");
                }
            } else if (choice.equals("3")) {
                System.out.print("Enter Client ID to update: ");
                String id = sc.nextLine();
                Client client = clients.stream().filter(c -> c.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
                if (client != null) {
                    client.setName(getValidInput(sc, "Enter New Name: "));
                    client.setCompany(getValidInput(sc, "Enter New Company: "));
                    client.setPhoneNumber(getValidInput(sc, "Enter New Phone: "));
                    FileManager.saveClients(clients);
                    System.out.println("Client baseline records modified.");
                } else System.out.println("Client target record missing.");
            } else if (choice.equals("4")) {
                System.out.print("Enter Client ID to wipe: ");
                String id = sc.nextLine();
                if (clients.removeIf(c -> c.getId().equalsIgnoreCase(id))) {
                    FileManager.saveClients(clients);
                    System.out.println("Record removed.");
                } else System.out.println("Target ID not matched.");
            } else break;
        }
    }

    public void manageEmployees(Scanner sc) {
        while (true) {
            System.out.println("\n--- Employee Operations Menu ---");
            System.out.println("1. Add Employee\n2. View Employees\n3. Update Status\n4. Return");
            System.out.print("Select choice: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                String id = getValidInput(sc, "Enter Employee ID/Username: ");
                if (isIdDuplicate(id, "employee")) { System.out.println("Error: ID matching existing element."); continue; }
                String name = getValidInput(sc, "Enter Full Name: ");
                String role = getValidInput(sc, "Enter Title/Role: ");
                double sal = getValidDouble(sc, "Set Gross Salary: ");

                employees.add(new Employee(id, name, role, sal, ""));
                FileManager.saveEmployees(employees);
                System.out.println("Employee operational node saved. System Password defaults to 'password'.");
            } else if (choice.equals("2")) {
                for (Employee e : employees) {
                    System.out.println("[" + e.getId() + "] " + e.getName() + " - " + e.getRole() + " ($" + e.getSalary() + ")");
                }
            } else if (choice.equals("3")) {
                System.out.print("Enter Target ID: ");
                String id = sc.nextLine();
                Employee emp = employees.stream().filter(e -> e.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
                if (emp != null) {
                    emp.setRole(getValidInput(sc, "New Assigned Role: "));
                    emp.setSalary(getValidDouble(sc, "Adjusted Base Salary: "));
                    FileManager.saveEmployees(employees);
                    System.out.println("Changes successfully committed.");
                } else System.out.println("Employee targeted data footprint not located.");
            } else break;
        }
    }

    public void manageProjects(Scanner sc) {
        while (true) {
            System.out.println("\n--- Project Blueprint Operations ---");
            System.out.println("1. Design/Launch Project\n2. Track/Display Active Ecosystem\n3. Mutate Progress Lifecycle State\n4. Return");
            System.out.print("Select operational track: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                String pid = getValidInput(sc, "Enter Unique Project ID Reference: ");
                if (isIdDuplicate(pid, "project")) { System.out.println("Conflict detected."); continue; }
                String cid = getValidInput(sc, "Verify Client Associated ID: ");

                System.out.println("Choose service domain package index configuration:");
                for (int i = 0; i < services.size(); i++) {
                    System.out.println((i + 1) + ". " + services.get(i).getServiceName());
                }
                System.out.print("Input numeric system mapping choice: ");
                int sIndex = Integer.parseInt(sc.nextLine()) - 1;
                String service = services.get(sIndex).getServiceName();

                String eid = getValidInput(sc, "Assign Employee Architect ID Account: ");

                projects.add(new Project(pid, cid, service, eid, "Pending"));
                FileManager.saveProjects(projects);

                // Link within Employee reference structures explicitly
                for (Employee e : employees) {
                    if (e.getId().equalsIgnoreCase(eid)) {
                        String current = e.getAssignedProjects().equals("None") ? "" : e.getAssignedProjects() + ",";
                        e.setAssignedProjects(current + pid);
                        FileManager.saveEmployees(employees);
                    }
                }
                System.out.println("Project operational entry verified and recorded.");
            } else if (choice.equals("2")) {
                for (Project p : projects) {
                    System.out.println("Proj ID: " + p.getProjectId() + " | Type: " + p.getServiceType() + " | Progress Lifecycle State: [" + p.getStatus() + "]");
                }
            } else if (choice.equals("3")) {
                System.out.print("Input matching target project node context string: ");
                String pid = sc.nextLine();
                Project p = projects.stream().filter(pr -> pr.getProjectId().equalsIgnoreCase(pid)).findFirst().orElse(null);
                if (p != null) {
                    System.out.println("Set new operational state validation index option:\n1. Pending\n2. In Progress\n3. Completed");
                    String opt = sc.nextLine();
                    if (opt.equals("1")) p.setStatus("Pending");
                    else if (opt.equals("2")) p.setStatus("In Progress");
                    else if (opt.equals("3")) p.setStatus("Completed");
                    FileManager.saveProjects(projects);
                    System.out.println("Lifecycle pipeline parameters optimized.");
                }
            } else break;
        }
    }

    public void manageServices() {
        System.out.println("\n--- Strategic Automation Competence Track Matrix ---");
        for (ServicePackage s : services) {
            System.out.println(" -> Core Service Node Capability: " + s.getServiceName());
        }
    }

    public void generateInvoices(Scanner sc) {
        System.out.println("\n--- Account Billing Ledger System Context ---");
        String invId = getValidInput(sc, "Generate Tracking Invoicing Alpha ID: ");
        if (isIdDuplicate(invId, "invoice")) { System.out.println("Duplicate billing tracking matrix."); return; }
        String projId = getValidInput(sc, "Relational Target Project Context Link ID: ");
        double amt = getValidDouble(sc, "Total Project Service Cost Valuation: ");

        invoices.add(new Invoice(invId, projId, amt, "Unpaid"));
        FileManager.saveInvoices(invoices);
        System.out.println("Billing parameters generated.");
    }

    public void viewReports() {
        System.out.println("\n====== SYSTEM PERFORMANCE LEDGER METRICS ======");
        System.out.println("Total Tracked Strategic Corporate Enterprise Client Nodes: " + clients.size());
        System.out.println("Total Workforce Employee Infrastructure Units: " + employees.size());
        System.out.println("Total Initiated Pipeline Engagements: " + projects.size());

        double revenueCollected = invoices.stream().filter(i -> i.getStatus().equalsIgnoreCase("Paid")).mapToDouble(Invoice::getAmount).sum();
        double pipelineOutstanding = invoices.stream().filter(i -> i.getStatus().equalsIgnoreCase("Unpaid")).mapToDouble(Invoice::getAmount).sum();

        System.out.println("Successfully Liquidated Cleared Invoiced Balance Assets: $" + revenueCollected);
        System.out.println("Accounts Receivable System Pipeline Deficit Balance: $" + pipelineOutstanding);
        System.out.println("================================================");
    }

    public void displayEmployeeDashboardView(String employeeId) {
        Employee emp = employees.stream().filter(e -> e.getId().equalsIgnoreCase(employeeId)).findFirst().orElse(null);
        if (emp != null) {
            emp.displayDashboard(); // Demonstrating OOP Polymorphism
        } else {
            System.out.println("Error fetching local account configurations.");
        }
    }
}
