
public class Main {
    public static void main(String[] args) {
        LoginFrame login = new LoginFrame();
        login.setVisible(true);

        try {
            // Creating Tables in the SQLite file via DAO's
            CustomerDAO customerDAO = new CustomerDAO();
            DriverDAO driverDAO = new DriverDAO();
            RestaurantDAO restaurantDAO = new RestaurantDAO();
            DeliveryDAO deliveryDAO = new DeliveryDAO();

            customerDAO.createTable();
            driverDAO.createTable();
            restaurantDAO.createTable();
            deliveryDAO.createTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
