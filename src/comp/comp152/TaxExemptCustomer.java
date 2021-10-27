package comp.comp152;

import java.util.ArrayList;
//FULL DISCAIMLER!!!! I HIRED A TUTOR WHO HELPED ME DO THIS. WE WORKED TOGETHER ON THIS PROJECT
// I WANT TO BE OPEN THAT I RECIEVED OUTSIDE HELP
// I AM ON THE VERGE OF DROPPING THIS CLASS AND THIS WAS THE ONLY WAY I WAS ABLE TO COMPLETE THE PROJECT
public class TaxExemptCustomer extends Customer {
    public TaxExemptCustomer(String Name, int ID) {
        super(Name, ID);
    }

    public TaxExemptCustomer(String custName) {
        super(custName);
    }

    /**
     * sum up the value of all of the items in the order
     * return that value
     *
     * @param itemsInOrder
     * @return
     */
    public double PayForOrder(ArrayList<merchandiseItem> itemsInOrder) {
        double sum = 0;
        for (merchandiseItem item : itemsInOrder) {
            sum += item.getPrice();
        }
        return sum;
    }

    /**
     * print please contact <name from customer> on the day of delivery
     */
    public void arrangeDelivery() {
        System.out.println("Please contact "+getName()+" on the day of delivery");
    }
}
