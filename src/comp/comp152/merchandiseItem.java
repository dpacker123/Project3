package comp.comp152;

//FULL DISCAIMLER!!!! I HIRED A TUTOR WHO HELPED ME DO THIS. WE WORKED TOGETHER ON THIS PROJECT
// I WANT TO BE OPEN THAT I RECIEVED OUTSIDE HELP
// I AM ON THE VERGE OF DROPPING THIS CLASS AND THIS WAS THE ONLY WAY I WAS ABLE TO COMPLETE THE PROJECT
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