package comp.comp152;

import java.lang.reflect.Type;

public class merchandiseItem {

    private ItemType taxibleType;
    private String Name;
    private double Price;

    public merchandiseItem(String Name, double Price, Type ItemType){

    }
    public String getName(){
        return Name;
    }

    public double getPrice(){
        return Price;
    }

    public ItemType getType(){
        return taxibleType;
    }



}
