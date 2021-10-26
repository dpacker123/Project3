package comp.comp152;

public class merchandiseItem {
    private ItemType taxibleType;
    private String Name;
    private double price;

    public merchandiseItem(String Name, double price, ItemType type) {
        this.Name = Name;
        this.price = price;
        this.taxibleType = type;
    }

    public String getName() {
        return Name;
    }

    public double getPrice() {
        return price;
    }

    public ItemType getType() {
        return taxibleType;
    }
}