package comp.comp152;

import java.util.ArrayList;
//FULL DISCAIMLER!!!! I HIRED A TUTOR WHO HELPED ME DO THIS. WE WORKED TOGETHER ON THIS PROJECT
// I WANT TO BE OPEN THAT I RECIEVED OUTSIDE HELP
// I AM ON THE VERGE OF DROPPING THIS CLASS AND THIS WAS THE ONLY WAY I WAS ABLE TO COMPLETE THE PROJECT
public class ResidentialCustomer extends Customer {

    public ResidentialCustomer(String Name, int ID) {
        super(Name, ID);
    }

    public ResidentialCustomer(String custName) {
        super(custName);
    }

    /**
     * sum up the cost of all of the items in the order
     * add any of the taxes required
     * return the sum of the item cost and taxes
     *
     * @param itemsInOrder
     * @return
     */
    public double PayForOrder(ArrayList<merchandiseItem> itemsInOrder) {
        double sum = 0;
        for (merchandiseItem item : itemsInOrder) {
            ItemType type = item.getType();
            if (ItemType.GeneralMerchandise == type) {
                sum += item.getPrice() + item.getPrice()*0.0625;
            }
            else if (ItemType.Clothing == type && item.getPrice() > 175) {
                sum += item.getPrice() + (item.getPrice() - 175)*0.0625;
            }
        }
        return sum;
    }
}