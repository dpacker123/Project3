package comp.comp152;

//FULL DISCAIMLER!!!! I HIRED A TUTOR WHO HELPED ME DO THIS. WE WORKED TOGETHER ON THIS PROJECT
// I WANT TO BE OPEN THAT I RECIEVED OUTSIDE HELP
// I AM ON THE VERGE OF DROPPING THIS CLASS AND THIS WAS THE ONLY WAY I WAS ABLE TO COMPLETE THE PROJECT
public class ShippingAddress {


    private String AddressLine1;
    private String AddressLine2;
    private String City;
    private String State;
    private String PostalCode;


    public ShippingAddress (String Line1, String Line2, String city, String State, String postCode) {
        AddressLine1 = Line1;
        AddressLine2 = Line2;
        City = city;
        this.State = State;
        PostalCode = postCode;
    }


    public String toString()
    {
        if(AddressLine2.length() > 0)
            return AddressLine1+'\n'+ AddressLine2+"\n"+City+", "+State+"  "+PostalCode;
        return AddressLine1+'\n'+City+", "+State+"  "+PostalCode;
    }




}