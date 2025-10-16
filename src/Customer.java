import java.util.LinkedList;
import java.util.List;

public class Customer extends Address{
    private String name;
    private String address;
    private int phoneNumber;
    private String[] favoriteItems;


    public void setName(String n) {
        name = n;
    }
    public void setAddress(String a) {
        address = a;
    }

    public void setPhoneNumber(int pN) {
        phoneNumber = pN;
    }

    public void setFavoriteItems(String[] fI) {
        favoriteItems = new String[fI.length];
        for (int i = 0; i < fI.length; i++) {
            favoriteItems[i] = fI[i];
        }
    }
}
