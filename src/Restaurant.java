public class Restaurant {
    private String name;
    private String[] menuItems;
    private String address;
    private int prices;

    public void setName(String n) {
        name = n;
    }

    public void setAddress (String a) {
        address = a;
    }

    public void setPrices (int p) {
        prices = p;
    }

    public void setMenuItems(String[] mI) {
        menuItems = new String[mI.length];
        for (int i = 0; i < mI.length; i++) {
            menuItems[i] = mI[i];
        }
    }

}
