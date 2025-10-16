import javax.swing.*;

public class Restaurant {

    private String name;
    private String[] menuItems;
    private Integer[] menuPrices;

    public void setName(String n){
        name = n;
    }
    public void assignItem(String mi, int mp){
        String[] temp1 = menuItems;
        Integer[] temp2 = menuPrices;
        menuItems = new String[menuItems.length+1];
        menuPrices = new Integer[menuPrices.length+1];
        for (int i = 0; i != temp1.length; i++){
            menuItems[i] = temp1[i];
            menuPrices[i] = temp2[i];
        }
        menuItems[menuItems.length-1] = mi;
        menuPrices[menuPrices.length-1] = mp;
    }
    public void itemList(){
        for (int i = 0; i < menuItems.length; i++){
            System.out.println("Item " + i + " is " + menuItems[i] + " which is priced at " + menuPrices[i] + ".");
        }
    }

}
