import java.util.List;
import java.util.Scanner;

// ==========================================
// 6. AUTHENTICATION MODULE
// ==========================================
class Authentication {
    public static String login(Scanner scanner, List<Employee> employees) {
        System.out.println("\n=================================");
        System.out.println("            SYSTEM LOGIN         ");
        System.out.println("=================================");
        System.out.print("Enter ID/Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        // Admin verification bypass override
        if (username.equalsIgnoreCase("admin") && password.equals("admin123")) {
            return "ADMIN";
        }

        // Validate individual employee credentials
        for (Employee emp : employees) {
            if (emp.getId().equalsIgnoreCase(username) && password.equals("password")) {
                return "EMP:" + emp.getId();
            }
        }
        return null;
    }
}
