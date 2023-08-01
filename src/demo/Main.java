package demo;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Customer");
            System.out.println("2. Add Appointment");
            System.out.println("3. Add Inventory Item");
            System.out.println("4. View Customers");
            System.out.println("5. View Appointments");
            System.out.println("6. View Inventory");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter full name: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    Customer customer = new Customer(fullName, phoneNumber, email);
                    customer.insertEntity();
                    break;
                case 2:
                    System.out.print("Enter customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter appointment date (YYYY-MM-DD): ");
                    String appointmentDate = scanner.nextLine();
                    System.out.print("Enter service type: ");
                    String serviceType = scanner.nextLine();
                    System.out.print("Enter appointment status: ");
                    String status = scanner.nextLine();
                    Appointment appointment = new Appointment(customerId, appointmentDate, serviceType, status);
                    appointment.insertEntity();
                    break;
                case 3:
                    System.out.print("Enter item name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter quantity available: ");
                    int quantityAvailable = scanner.nextInt();
                    System.out.print("Enter price per unit: ");
                    double pricePerUnit = scanner.nextDouble();
                    InventoryItem item = new InventoryItem(itemName, quantityAvailable, pricePerUnit);
                    item.insertEntity();
                    break;
                case 4:
                    Customer customer1 = new Customer("", "", "");
                    customer1.viewAllEntities();
                    break;
                case 5:
                    Appointment appointment1 = new Appointment(0, "", "", "");
                    appointment1.viewAllEntities();
                    break;
                case 6:
                    InventoryItem item1 = new InventoryItem("", 0, 0.0);
                    item1.viewAllEntities();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
