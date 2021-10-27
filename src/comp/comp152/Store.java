package comp.comp152;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;



//FULL DISCAIMLER!!!! I HIRED A TUTOR WHO HELPED ME DO THIS. WE WORKED TOGETHER ON THIS PROJECT
// I WANT TO BE OPEN THAT I RECIEVED OUTSIDE HELP
// I AM ON THE VERGE OF DROPPING THIS CLASS AND THIS WAS THE ONLY WAY I WAS ABLE TO COMPLETE THE PROJECT
/**
 * Class Store
 */
public class Store {

    private static final String STOCK_DATA = "stock.txt";
    private static final String CUSTOMER_DATA = "customers.txt";

    //
    // Fields
    //

    private ArrayList<Order> Orders;
    private ArrayList<Customer> Customers;
    private ArrayList<merchandiseItem> stock;
    private double revenue;

    //
    // Constructors
    //
    public Store () {
        Orders = new ArrayList<Order>();
        Customers = new ArrayList<Customer>();
        stock = new ArrayList<merchandiseItem>();
        revenue = 0;
    }

    //
    // Methods
    //

    public static void main(String[] args) throws IOException
    {
        var comp152Inc = new Store();
        comp152Inc.runStore();
    }

    /**
     */
    public void runStore() throws IOException
    {
        var inputReader = new Scanner(System.in);
        loadStock();
        loadCustomers();
        while(true){ //the main run loop
            printMainMenu();
            var userChoice = inputReader.nextInt();
            switch (userChoice){
                case 0:
                    System.exit(0);
                case 1:
                    addCustomer(inputReader);
                    break;
                case 2:
                    var selectedCustomer =selectCustomer(inputReader);
                    if(selectedCustomer.isPresent())
                        manageCustomer(selectedCustomer.get());
                    break;
                case 3:
                    collectOutstandingBalance();
                    break;
                default:
                    System.out.println("\n%%%%%%Invalid selection, please choose one of the options from the menu%%%%%%\n");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("*****************************************************************************");
        System.out.println("Welcome to the the 1980s Comp152 Store interface, what would you like to do?");
        System.out.println("   [1] Add Customer");
        System.out.println("   [2] Select Customer");
        System.out.println("   [3] Collect Outstanding Balances");
        System.out.println("   [0] Exit the program");
        System.out.println("*****************************************************************************");
        System.out.print("Enter the number of your choice:");
    }

    private void loadStock() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(STOCK_DATA))) {
            String line;
            while ((line = reader.readLine()) != null && !"".equals(line)) {
                String[] cells = line.split(",");
                ItemType type;
                if (cells[0] == "Clothing") {
                    type = ItemType.Clothing;
                } else if (cells[0] == "WCIFFood") {
                    type = ItemType.WICFFood;
                } else {
                    type = ItemType.GeneralMerchandise;
                }
                stock.add(new merchandiseItem(cells[1], Double.parseDouble(cells[2]), type));
            }
        }
    }

    private void loadCustomers() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(CUSTOMER_DATA)))) {
            String line;
            while ((line = reader.readLine()) != null & !"".equals(line)) {
                String[] cells = line.split(",");
                Customer customer;
                if ("B".equals(cells[0])) {
                    customer = new BusinessCustomer(cells[1], Integer.parseInt(cells[2]));
                } else if ("TE".equals(cells[0])) {
                    customer = new TaxExemptCustomer(cells[1], Integer.parseInt(cells[2]));
                } else {
                    customer = new ResidentialCustomer(cells[1], Integer.parseInt(cells[2]));
                }
                Customers.add(customer);
            }
        }
    }

    /**
     * it should now allow the customer to select several items for purchase
     * after the order is complete, call PayForOrder and arrangeDelivery on the customer and add the return value to the new revenue instance variable
     * @param        address
     * @param        cust
     */
    public void makeOrder(ShippingAddress address, Customer cust)
    {
        ArrayList<merchandiseItem> cart = new ArrayList<>();
        System.out.println("############################ Choose cart items ############################");
        do {
            var oItem = chooseMerchandiseToOrder();
            if (oItem.isPresent())
                cart.add(oItem.get());
            else
                break;
        }
        while (true);
        if (cart.isEmpty()) {
            System.out.println("Cart is empty, order canceled");
            return;
        }
        var newOrder = new Order(address,cust,cart);
        Orders.add(newOrder);
        revenue += cust.PayForOrder(cart);
        cust.arrangeDelivery();
        System.out.println(".......New order successfully created");
    }

    /**
     * Add new customer
     *
     * @param inputReader std input an instance of {@link Scanner}
     */
    public void addCustomer(Scanner inputReader)
    {
        //because we just came from a nextInt, there is an orphaned \n on the input stream eat it
        inputReader.nextLine();
        System.out.println("Adding Customer........");
        System.out.print("Enter the new Customers name:");
        var newName = inputReader.nextLine();
        System.out.println("Customer Types.......");
        System.out.println("B. Business");
        System.out.println("R. Residential");
        System.out.println("TE. Tax Exempt");
        System.out.print("Enter type: ");
        var type = inputReader.nextLine();
        Customer newCustomer;
        if ("B".equals(type)) {
            newCustomer = new BusinessCustomer(newName);
        }
        else if ("TE".equals(type)) {
            newCustomer = new TaxExemptCustomer(newName);
        }
        else {
            newCustomer = new ResidentialCustomer(newName);
        }
        Customers.add(newCustomer);
        System.out.println(".....Finished adding new Customer Record");
    }


    /**
     * @return       Customer
     * the original UML called for returning a Customer Object rather than an Optional
     * since I didn't know when I designed this if we would hit optional by then or not
     * but I invited anyone who asked to use Optional if they wanted to
     * either way is perfectly fine
     */
    public Optional<Customer> selectCustomer(Scanner reader)
    {
        System.out.print("Enter the ID of the customer to select:");
        var enteredID = reader.nextInt();
        for(var currentCustomer: Customers){
            if(currentCustomer.getCustomerID()==enteredID)
                return Optional.of(currentCustomer);
        }
        //If we looked through the whole list and didn't find that customer tell the user
        System.out.println("==========================> No customer with customer ID:"+enteredID);
        return Optional.empty();
    }

    /**
     * Calculates the outstanding balance from all customers
     */
    public void collectOutstandingBalance() {
        for (Customer c : Customers) {
            revenue += c.payOutstandingBalance();
        }
        System.out.printf("Total revenue collected from all customer is: $%,.2f\n",revenue);
    }

    /**
     * This method will show a list of stock to choose a merchandise from the list
     * and returns the {@link Optional} of the selected merchandise. If nothing is
     * selected an empty {@link Optional} is returned.
     *
     * @return {@link Optional} of selected merchandise or empty {@link Optional} if
     *          nothing selected
     */
    private Optional<merchandiseItem> chooseMerchandiseToOrder() {
        var inputReader = new Scanner(System.in);
        System.out.println("============================ [ Choose Merchandise ] ============================");
        int slNo = 0;
        System.out.printf(" %4s |                            Name                              | %6s\n", "sl no.", "Price");
        System.out.println("--------------------------------------------------------------------------------");
        for (merchandiseItem m : stock) {
            System.out.printf("%7d | %60s | $%,.2f\n",(slNo+1), m.getName(), m.getPrice());
            slNo++;
        }
        System.out.println("================================================================================");
        System.out.print("Enter serial no ( or any other number to exit): ");
        var choice = inputReader.nextInt();
        inputReader.nextLine();
        if (choice > 0 && choice <= stock.size()) {
            return Optional.of(stock.get(choice-1));
        }
        return Optional.empty();
    }

    /**
     * @param        selectedCustomer
     */
    public void manageCustomer(Customer selectedCustomer)
    {
        //many of you passed our existing scanner in as an extra parameter, that is more than fine.
        //I'm doing it this way to create a new one, both work the same way since both are reading from
        //System.in
        Scanner secondScanner = new Scanner(System.in);
        while(true){ //the menu loop for the manage Customer menu
            printCustomerMenu(selectedCustomer.getName());
            var userChoice = secondScanner.nextInt();
            //the syntax below is part of the 'enhanced switch' available in very recent versions of java
            //I used the traditional version in the previous switch above, so I'm experimenting here
            //this 'enhanced switch' doesn't require break statements.
            switch (userChoice){
                case 1 ->addAddress(secondScanner, selectedCustomer);
                case 2->{
                    ShippingAddress selectedAddress = pickAddress(secondScanner,selectedCustomer);
                    makeOrder(selectedAddress,selectedCustomer);
                }
                case 3-> {return;}
                default->System.out.println("Invalid option selected");
            }
        }
    }

    private ShippingAddress pickAddress(Scanner secondScanner, Customer selectedCustomer) {
        var customerAddresses = selectedCustomer.getAddresses();
        if (customerAddresses.size() ==0){ //note, this error checking was not required
            System.out.println("This customer has no addresses on file, please add an address");
            addAddress(secondScanner,selectedCustomer);
            return selectedCustomer.getAddresses().get(0); //if we are here there is only one address so use it
        }
        var count = 0;
        //if we are here then there was at least one address already in the customer.
        System.out.println("Please select a shipping address from those the customer has on file");
        for (var address : customerAddresses) {   //I'm being a little 'cute'/clever here
            System.out.print("[" + count + "]"); //but you could do for(int count; count < customerAddresses.size(); count++ for the same effect
            System.out.println(address.toString());
            count++;
        }
        System.out.print("Enter the number of the address for this order:");
        var addressNum = secondScanner.nextInt();
        //again more error checking that I didn't require of you here below:
        if (addressNum >= customerAddresses.size()){
            System.out.println("Invalid entry, defaulting to the first address on file...");
            return customerAddresses.get(0);
        }
        else
            return customerAddresses.get(addressNum);//I asked the user for the number representing the addresses position in the arrayList
    }

    private void addAddress(Scanner secondScanner, Customer selectedCustomer) {
        System.out.println("Adding new address for "+ selectedCustomer.getName());
        secondScanner.nextLine(); //lets eat the leftover '\n' from previous nextInt
        System.out.print("Enter Address Line 1:");
        var line1 = secondScanner.nextLine();
        System.out.print("Enter Address Line 2 or <enter> if there is none:");
        var line2 = secondScanner.nextLine();
        System.out.print("Enter the address City:");
        var city = secondScanner.nextLine();
        System.out.print("Enter address state:");
        var state = secondScanner.nextLine();
        System.out.print("Enter the postal code:");
        var postCode = secondScanner.nextLine();
        var newAddress  = new ShippingAddress(line1,line2,city,state,postCode);
        selectedCustomer.addAddress(newAddress);
    }

    private void printCustomerMenu(String custName) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("What do you want to do for Customer " + custName+"?");
        System.out.println("   [1] Add Address to customer");
        System.out.println("   [2] Make an order for the customer");
        System.out.println("   [3] return to the main menu");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.print("Enter the number of your choice:");
    }
}