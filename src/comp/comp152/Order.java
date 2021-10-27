package comp.comp152;
//FULL DISCAIMLER!!!! I HIRED A TUTOR WHO HELPED ME DO THIS. WE WORKED TOGETHER ON THIS PROJECT
// I WANT TO BE OPEN THAT I RECIEVED OUTSIDE HELP
// I AM ON THE VERGE OF DROPPING THIS CLASS AND THIS WAS THE ONLY WAY I WAS ABLE TO COMPLETE THE PROJECT
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
     * @param cart
     */
    public Order(ShippingAddress dest, Customer cust, ArrayList<merchandiseItem> cart)
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
