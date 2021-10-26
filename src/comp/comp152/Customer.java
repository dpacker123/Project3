package comp.comp152;

import java.util.ArrayList;

/**
 * Class Customer
 */
public abstract class Customer {

    //
    // Fields
    //
    private ArrayList<ShippingAddress> Addresses;
    private String Name;
    private int customerID;
    private static int nextID = 5000;

    //
    // Constructors
    //
    public Customer (String Name, int ID) {
        this.Name = Name;
        customerID = ID;
        Addresses = new ArrayList<ShippingAddress>();
    };

    /**
     * @param        custName
     */
    public Customer(String custName)
    {
        Name = custName;
        nextID++;
        customerID = nextID;
        Addresses = new ArrayList<ShippingAddress>();
    }

    //
    // Methods
    //



    /**
     * Get the value of Addresses
     * @return the value of Addresses
     */
    public ArrayList<ShippingAddress> getAddresses () {

        return new ArrayList<ShippingAddress>(Addresses);
    }

    /**
     * Get the value of Name
     * @return the value of Name
     */
    public String getName () {
        return Name;
    }

    /**
     * Get the value of customerID
     * @return the value of customerID
     */
    public int getCustomerID () {
        return customerID;
    }

    //
    // Other methods
    //


    /**
     * @param        newAddress
     */
    public void addAddress(ShippingAddress newAddress)
    {
        Addresses.add(newAddress);
    }



    /**
     * @return       String
     */
    @Override
    public String toString()
    {
        return "Customer Name: " + Name +"\nCustomerID: "+customerID + "\nWith "+Addresses.size() + " addresses on file";
    }


    /**
     * PayForOrder must be an abstract method without any implementation
     * @param itemsInOrder
     * @return
     */
    public abstract double PayForOrder(ArrayList<merchandiseItem> itemsInOrder);

    /**
     * payOutstandingBalance should return 0.0 in the customer class
     * @return
     */
    public double payOutstandingBalance() {
        return 0;
    }

    /**
     * arrangeDelivery will just print, the Customer Name and then "deliver any time" in the Customer class
     */
    public void arrangeDelivery() {
        System.out.println("["+getName()+"] deliver any time");
    }

}
