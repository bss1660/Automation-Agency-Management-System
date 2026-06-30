import java.util.*;


// ==========================================
// 8. MENU SYSTEM & CENTRAL ENTRY POINT
// ==========================================
public class MenuSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AgencyManager engine = new AgencyManager();

        System.out.println("==================================================");
        System.out.println("  AI AUTOMATION AGENCY ENTERPRISE ARCHITECTURE   ");
        System.out.println("==================================================");

        while (true) {
            String roleContext = Authentication.login(sc, FileManager.loadEmployees());
            if (roleContext == null) {
                System.out.println("Access Denied. Signature parameters incorrect. Try again.");
                continue;
            }

            if (roleContext.equals("ADMIN")) {
                boolean running = true;
                while (running) {
                    System.out.println("\n::: CENTRAL ADMINISTRATOR SYSTEM INTERFACE MENU :::");
                    System.out.println("1. Manage Clients\n2. Manage Employees\n3. Manage Projects\n4. Manage Services\n5. Generate Invoices\n6. View Performance Reports\n7. Log Out System Context\n8. Shut Down Architecture");
                    System.out.print("Input choice: ");
                    String act = sc.nextLine();

                    switch (act) {
                        case "1": engine.manageClients(sc); break;
                        case "2": engine.manageEmployees(sc); break;
                        case "3": engine.manageProjects(sc); break;
                        case "4": engine.manageServices(); break;
                        case "5": engine.generateInvoices(sc); break;
                        case "6": engine.viewReports(); break;
                        case "7": running = false; System.out.println("Context detached."); break;
                        case "8":
                            System.out.println("System files successfully written. System flushing. Shutting down.");
                            sc.close();
                            System.exit(0);
                        default: System.out.println("Option mapping error.");
                    }
                }
            } else if (roleContext.startsWith("EMP:")) {
                String targetEmpId = roleContext.substring(4);
                engine.displayEmployeeDashboardView(targetEmpId);
                System.out.println("Press Enter to log out...");
                sc.nextLine();
            }
        }
    }
}