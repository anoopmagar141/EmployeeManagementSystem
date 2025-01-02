import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Base class for all employees.
 * Contains common properties like ID, name, department, and methods for displaying details.
 */
abstract class Employee {
    private String employeeId; // Unique ID for each employee
    private String password; // Password for employee login
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String address;
    private String department; // Department the employee belongs to
    private String email;
    private String dateOfJoining; // Auto-generated on employee creation
    private double salary; // Employee's salary

    // Constructor
    public Employee(String firstName, String lastName, String middleName, String phoneNumber, String address, String department, String email, String password) {
        this.employeeId = generateEmployeeId(); // Generate random unique ID
        this.password = password; // Set login password
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = (middleName == null || middleName.isEmpty()) ? "N/A" : middleName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
        this.email = email;
        this.dateOfJoining = getCurrentDate(); // Set current date as joining date
        this.salary = 0.0; // Default salary (set later based on department)
    }

    // Method to generate a random unique employee ID
    private String generateEmployeeId() {
        return String.valueOf(new Random().nextInt(900000000) + 1000000000);
    }

    // Method to get the current date in "yyyy-MM-dd" format
    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    // Method to display employee details
    public void displayDetails() {
        System.out.printf("%-15s %-20s %-15s %-15s %-20s %-10s %.2f\n",
                employeeId, firstName + " " + lastName, phoneNumber, department, email, dateOfJoining, salary);
    }

    // Abstract method to calculate salary (to be implemented by subclasses)
    public abstract void calculateSalary();

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }
}
/**
 * FullTimeEmployee class represents permanent employees.
 * Extends the Employee class and defines salary calculation logic.
 */
class FullTimeEmployee extends Employee {

    // Constructor
    public FullTimeEmployee(String firstName, String lastName, String middleName, String phoneNumber, String address, String department, String email, String password) {
        super(firstName, lastName, middleName, phoneNumber, address, department, email, password);
    }

    // Salary calculation based on department
    @Override
    public void calculateSalary() {
        switch (getDepartment().toLowerCase()) {
            case "hr":
                setSalary(150000); // HR salary
                break;
            case "it":
                setSalary(175000); // IT salary
                break;
            case "sales":
                setSalary(125000); // Sales salary
                break;
        }
    }
}

/**
 * Main class for the Employee Management System.
 * Contains functionality for adding employees, viewing details, and admin panel.
 */
public class EmployeeManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Employee> employees = new HashMap<>(); // Employee data storage
    private static final Map<String, String> credentials = new HashMap<>(); // Login credentials
    private static final String ADMIN_ID = "admin"; // Admin login ID
    private static final String ADMIN_PASSWORD = "admin123"; // Admin password

    public static void main(String[] args) {
        preloadEmployees(); // Load some initial employees

        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee Details");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewEmployeeDetails();
                    break;
                case 3:
                    adminLogin();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

