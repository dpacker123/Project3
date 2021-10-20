package comp.comp152;

import javax.naming.Name;
import java.util.ArrayList;

public class BusinessCustomer extends Customer{

    private double purchaseOrderBalance;


    @Override
    public double PayForOrder(ArrayList<ItemForSale> itemsInOrder) {
        System.out.println("" + getName() + purchaseOrderBalance);

    }

    public BusinessCustomer(String Name, int ID) {
        super(Name, ID);
    }


}
