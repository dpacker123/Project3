package comp.comp152;

import java.util.ArrayList;

/**
 * Class Order
 */
public class Order {

    //
    // Fields
    //

    private ShippingAddress destination;
    private Customer orderedBy;
    private ArrayList<merchandiseItem> cartForOrder;


    /**
     * @param        dest
     * @param        cust
     */
    public Order(ShippingAddress dest, Customer cust)
    {
        destination = dest;
        orderedBy = cust;
        cartForOrder = new ArrayList<merchandiseItem>();
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
