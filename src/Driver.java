/**
 * Documentation: Driver
 *
 * The driver class represents a delivery driver in the system and it stores the drivers id, name, and license
 * number.
 *
 * Module Purpose:
 * - Stores driver information
 * - Provides getter methods to access the drivers information
 *
 */

import javax.swing.*;

public class Driver {
    private int id;
    private String name;
    private String licenseNumber;

    /**
     * Constructor to create a new driver object
     *
     * @param id driver id
     * @param name driver's name
     * @param licenseNumber driver's license number
     */

    public Driver(int id, String name, String licenseNumber) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    /**
     * Get id of the driver
     *
     * @return the id of the driver
     */
    public int getId() { return id; }

    /**
     * Get name of the driver
     *
     * @return the name of the driver
     */
    public String getName() { return name; }

    /**
     * Get license number of the driver
     *
     * @return the license number of the driver
     */
    public String getLicenseNumber() { return licenseNumber; }


    /**
     * Return a string representation of the driver
     *
     * @return formatted string "Driver[id] name - License: licenseNumber"
     */
    @Override
    public String toString() {
        return "Driver[" + id + "] " + name + " - License: " + licenseNumber;
    }
}

