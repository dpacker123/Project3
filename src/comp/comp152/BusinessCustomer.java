package comp.comp152;

import javax.naming.Name;
import java.util.ArrayList;

public class BusinessCustomer extends Customer{

    private double purchaseOrderBalance;


    //@Override
  //  public double PayForOrder(ArrayList<ItemForSale> itemsInOrder) {
       // System.out.println("" + getName() + purchaseOrderBalance);

 //   }

    public BusinessCustomer(String Name, int ID) {
        super(Name, ID);
    }

    public double PayOutstandingBalance(){
        if(payOutstandingBalance() > 1000){
            var newOutStandingBalance = payOutstandingBalance() * .05;
            System.out.println("The new oustanding balance is" + newOutStandingBalance);
            return 0.0;
        }
        else{
            System.out.println("The outstanding balance was below 1000");
            return 0.0;
        }
    }


}
