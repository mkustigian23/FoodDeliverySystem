public class Menu {
    private int restaurantId;
    private String name;
    private double price;
    private String menu_imagePath;

    public Menu(int restaurantId, String name, double price, String menu_imagePath) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.menu_imagePath = menu_imagePath;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getMenu_imagePath() {
        return menu_imagePath;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f", name, price);
    }
}
