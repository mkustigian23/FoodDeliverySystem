public class Customer {
    private String name;
    private String address;
    private int phoneNumber;
    private String[] favoriteItems;
    private int creditCard;


    public void setName(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }
    public void setAddress(String a) {
        address = a;
    }
    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(int pN) {
        phoneNumber = pN;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setFavoriteItems(String[] fI) {
        favoriteItems = new String[fI.length];
        for (int i = 0; i < fI.length; i++) {
            favoriteItems[i] = fI[i];
        }
    }

    public void setCreditCard(int cC) {
        creditCard = cC;
    }
    public int getCreditCard() {
        return creditCard;
    }
}
