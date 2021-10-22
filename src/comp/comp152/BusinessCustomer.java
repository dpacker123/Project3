package comp.comp152;

import java.util.ArrayList;

public class BusinessCustomer extends Customer {

    private double purchaseOrderBalance;

    public BusinessCustomer(String Name, int ID) {
        super(Name, ID);
    }

    public BusinessCustomer(String custName) {
        super(custName);
    }


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
        this.purchaseOrderBalance = sum;
        System.out.printf("This order will be for "+getName()+
                " and cumulative price of the order (including taxes) is $%,.2f\n",sum);
        return 0;
    }


    public double payOutstandingBalance() {
        double balance = this.purchaseOrderBalance;
        if (balance > 1000) {
            balance = balance*0.95;
        }
        this.purchaseOrderBalance = 0;
        return balance;
    }


    public void arrangeDelivery() {
        System.out.println("["+getName()+"] all deliveries must be from 9-5 Mon-Fri");
    }
}
