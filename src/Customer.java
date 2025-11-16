/**
 * Documentation: Customer
 *
 * This class represents a customer.
 * It stores the customers id, name, and email
 *
 * Module Purpose:
 * - Represents a customer
 * - Provides access to basic customer information
 *
 * Key Methods:
 * - Customer(int id, String name, String email):
 * - Constructor to initialize the customer with an id, name, and email
 *
 * int get id():
 * - Returns the customer's id
 *
 * String getEmail():
 * - Returns the customer's email
 *
 * String toString():
 * - Returns a string in the format: "Customer[ID] Name - Email"
 *
 */

public class Customer {
    private int id;
    private String name;
    private String email;

    /**
     * Constructor to create a new customer.
     *
     * @param id the ID of the customer
     * @param name the name of the customer
     * @param email the email of the customer
     */

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Gets the customer's ID
     *
     * @return the ID of the customer
     */
    public int getId() { return id; }

    /**
     * Gets the customer's name
     *
     * @return the name of the customer
     */
    public String getName() { return name; }

    /**
     * Gets the customer's email
     *
     * @return the email of the customer
     */
    public String getEmail() { return email; }

    /**
     * Returns a string in the format: "Customer[ID] Name - Email"
     *
     * @return Formatted string with the ID, name, and email
     */

    @Override
    public String toString() {
        return "Customer[" + id + "] " + name + " - " + email;
    }
}
