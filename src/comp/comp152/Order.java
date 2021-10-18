package comp.comp152;

/**
 * Class Order
 */
public class Order {

    //
    // Fields
    //

    private ShippingAddress destination;
    private Customer orderedBy;


    /**
     * @param        dest
     * @param        cust
     */
    public Order(ShippingAddress dest, Customer cust)
    {
        destination = dest;
        orderedBy = cust;
    }

    //
    // Methods
    //

    /**
     * @return       String
     */
    public String getDestination()
    {
        return destination.toString();
    }


    /**
     * @return       String
     */
    public String getOrderer()
    {
        return orderedBy.toString();
    }





}
