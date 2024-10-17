// File: CafeApp.java
import java.util.ArrayList;
import java.util.Scanner;

// Parent class Person
abstract class Person {
    protected String name;
    protected String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id;
    }
}

// Child class Employee
class Employee extends Person {
    private String position;

    public Employee(String name, String id, String position) {
        super(name, id);
        this.position = position;
    }

    @Override
    public String toString() {
        return super.toString() + ", Position: " + position;
    }
}

// Child class Customer
class Customer extends Person {
    private String favoriteDrink;

    public Customer(String name, String id, String favoriteDrink) {
        super(name, id);
        this.favoriteDrink = favoriteDrink;
    }

    @Override
    public String toString() {
        return super.toString() + ", Favorite Drink: " + favoriteDrink;
    }
}

// Class for Menu Items
class MenuItem {
    private String itemName;
    private double price;

    public MenuItem(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu Item: " + itemName + ", Price: $" + price;
    }
}

// Group class managing collections
class Cafe {
    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;
    private ArrayList<MenuItem> menuItems;

    public Cafe() {
        employees = new ArrayList<>();
        customers = new ArrayList<>();
        menuItems = new ArrayList<>();
    }

    // Add employee to the list
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Add customer to the list
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Add menu item to the list
    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    // Retrieve an employee by ID
    public Employee getEmployeeById(String id) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    // Retrieve a customer by ID
    public Customer getCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    // Display all employees
    public void displayAllEmployees() {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    // Display all customers
    public void displayAllCustomers() {
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    // Display all menu items
    public void displayAllMenuItems() {
        for (MenuItem m : menuItems) {
            System.out.println(m);
        }
    }
}

// Main class that drives the application
public class CafeApp {
    public static void main(String[] args) {
        Cafe cafe = new Cafe();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Add Employee");
            System.out.println("2. Add Customer");
            System.out.println("3. Add Menu Item");
            System.out.println("4. Retrieve Employee by ID");
            System.out.println("5. Retrieve Customer by ID");
            System.out.println("6. Display All Employees");
            System.out.println("7. Display All Customers");
            System.out.println("8. Display All Menu Items");
            System.out.println("9. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Employee Name:");
                    String empName = scanner.nextLine();
                    System.out.println("Enter Employee ID:");
                    String empId = scanner.nextLine();
                    System.out.println("Enter Employee Position:");
                    String empPosition = scanner.nextLine();
                    cafe.addEmployee(new Employee(empName, empId, empPosition));
                    break;
                case 2:
                    System.out.println("Enter Customer Name:");
                    String custName = scanner.nextLine();
                    System.out.println("Enter Customer ID:");
                    String custId = scanner.nextLine();
                    System.out.println("Enter Customer Favorite Drink:");
                    String favDrink = scanner.nextLine();
                    cafe.addCustomer(new Customer(custName, custId, favDrink));
                    break;
                case 3:
                    System.out.println("Enter Menu Item Name:");
                    String itemName = scanner.nextLine();
                    System.out.println("Enter Menu Item Price:");
                    double itemPrice = scanner.nextDouble();
                    cafe.addMenuItem(new MenuItem(itemName, itemPrice));
                    break;
                case 4:
                    System.out.println("Enter Employee ID:");
                    String searchEmpId = scanner.nextLine();
                    Employee foundEmployee = cafe.getEmployeeById(searchEmpId);
                    System.out.println(foundEmployee != null ? foundEmployee : "Employee not found.");
                    break;
                case 5:
                    System.out.println("Enter Customer ID:");
                    String searchCustId = scanner.nextLine();
                    Customer foundCustomer = cafe.getCustomerById(searchCustId);
                    System.out.println(foundCustomer != null ? foundCustomer : "Customer not found.");
                    break;
                case 6:
                    cafe.displayAllEmployees();
                    break;
                case 7:
                    cafe.displayAllCustomers();
                    break;
                case 8:
                    cafe.displayAllMenuItems();
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}
