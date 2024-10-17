import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// StoreItem (Abstract Parent Class)
abstract class StoreItem {
    private int id;
    private String title;
    private double price;

    public StoreItem(int id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }

    public abstract String toString();
}

// Book (Child Class)
class Book extends StoreItem {
    private String author;
    private String publisher;  // Publisher field
    private int year;

    public Book(int id, String title, double price, String author, String publisher, int year) {
        super(id, title, price);  // Call parent constructor
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book: " + getTitle() + " by " + author + " (" + year + "), Publisher: " + publisher;  // Include publisher
    }
}

// EBook (Child Class)
class EBook extends StoreItem {
    private double fileSize;
    private String format;

    public EBook(int id, String title, double price, double fileSize, String format) {
        super(id, title, price);  // Call parent constructor
        this.fileSize = fileSize;
        this.format = format;
    }

    @Override
    public String toString() {
        return "EBook: " + getTitle() + " [" + format + ", " + fileSize + "MB]";
    }
}

// Customer
class Customer {
    private int customerId;
    private String name;
    private String email;

    public Customer(int customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Customer: " + name + " (" + email + ")";
    }
}

// Order
class Order {
    private int orderId;
    private Customer customer;
    private List<StoreItem> items;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addItem(StoreItem item) {
        items.add(item);
    }

    public List<StoreItem> getItems() {
        return items;
    }

    // Add a getter for orderId
    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer: " + customer.getName() + ", Items: " + items.size();
    }
}

// Bookstore (Collection Class)
class Bookstore {
    private List<StoreItem> inventory;
    private List<Customer> customers;

    public Bookstore() {
        inventory = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public void addStoreItem(StoreItem item) {
        inventory.add(item);
    }

    public StoreItem getItemById(int id) {
        for (StoreItem item : inventory) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null; // Return null if not found
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }
        return null; // Return null if not found
    }

    public void displayAllItems() {
        for (StoreItem item : inventory) {
            System.out.println(item);
        }
    }

    public void displayAllCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}

// OrderManager (Collection Class for Orders)
class OrderManager {
    private List<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getOrderId() == id) {  // Accessing orderId via getter
                return order;
            }
        }
        return null; // Return null if not found
    }
}

// Main (Driver Class)




public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bookstore bookstore = new Bookstore();
        OrderManager orderManager = new OrderManager();

        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n--- Bookstore Menu ---");
            System.out.println("1. Add a Book");
            System.out.println("2. Add an EBook");
            System.out.println("3. Add a Customer");
            System.out.println("4. Place an Order");
            System.out.println("5. Display all Books and EBooks");
            System.out.println("6. Display all Customers");
            System.out.println("7. Retrieve an Order by ID");
            System.out.println("8. Exit");
            System.out.print("Choose an option (1-8): ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:  // Add a Book
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter Book Title: ");
                    String bookTitle = scanner.nextLine();

                    System.out.print("Enter Book Price: ");
                    double bookPrice = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter Book Author: ");
                    String bookAuthor = scanner.nextLine();

                    System.out.print("Enter Book Publisher: ");
                    String bookPublisher = scanner.nextLine();

                    System.out.print("Enter Publication Year: ");
                    int bookYear = scanner.nextInt();

                    Book newBook = new Book(bookId, bookTitle, bookPrice, bookAuthor, bookPublisher, bookYear);
                    bookstore.addStoreItem(newBook);
                    System.out.println("Book added successfully!");
                    break;

                case 2:  // Add an EBook
                    System.out.print("Enter EBook ID: ");
                    int ebookId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter EBook Title: ");
                    String ebookTitle = scanner.nextLine();

                    System.out.print("Enter EBook Price: ");
                    double ebookPrice = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter File Size (in MB): ");
                    double fileSize = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter EBook Format (e.g., PDF, EPUB): ");
                    String format = scanner.nextLine();

                    EBook newEBook = new EBook(ebookId, ebookTitle, ebookPrice, fileSize, format);
                    bookstore.addStoreItem(newEBook);
                    System.out.println("EBook added successfully!");
                    break;

                case 3:  // Add a Customer
                    System.out.print("Enter Customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();

                    System.out.print("Enter Customer Email: ");
                    String customerEmail = scanner.nextLine();

                    Customer newCustomer = new Customer(customerId, customerName, customerEmail);
                    bookstore.addCustomer(newCustomer);
                    System.out.println("Customer added successfully!");
                    break;

                case 4:  // Place an Order
                    System.out.print("Enter Customer ID for the Order: ");
                    int orderCustomerId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    Customer orderCustomer = bookstore.getCustomerById(orderCustomerId);
                    if (orderCustomer == null) {
                        System.out.println("Customer not found!");
                        break;
                    }

                    System.out.print("Enter Order ID: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    Order newOrder = new Order(orderId, orderCustomer);
                    
                    boolean addingItems = true;
                    while (addingItems) {
                        System.out.print("Enter Store Item ID to add to order (or -1 to finish): ");
                        int itemId = scanner.nextInt();
                        if (itemId == -1) {
                            addingItems = false;
                        } else {
                            StoreItem storeItem = bookstore.getItemById(itemId);
                            if (storeItem != null) {
                                newOrder.addItem(storeItem);
                                System.out.println("Item added to the order.");
                            } else {
                                System.out.println("Item not found!");
                            }
                        }
                    }

                    orderManager.placeOrder(newOrder);
                    System.out.println("Order placed successfully!");
                    break;

                case 5:  // Display all Books and EBooks
                    System.out.println("Books and EBooks in the Bookstore:");
                    bookstore.displayAllItems();
                    break;

                case 6:  // Display all Customers
                    System.out.println("Customers in the Bookstore:");
                    bookstore.displayAllCustomers();
                    break;

                case 7:  // Retrieve an Order by ID
                    System.out.print("Enter Order ID: ");
                    int searchOrderId = scanner.nextInt();
                    Order retrievedOrder = orderManager.getOrderById(searchOrderId);
                    if (retrievedOrder != null) {
                        System.out.println("Retrieved Order: " + retrievedOrder);
                    } else {
                        System.out.println("Order not found!");
                    }
                    break;

                case 8:  // Exit
                    exit = true;
                    System.out.println("Exiting the application.");
                    break;

                default:
                    System.out.println("Invalid option! Please choose again.");
            }
        }

        scanner.close();
    }
}
