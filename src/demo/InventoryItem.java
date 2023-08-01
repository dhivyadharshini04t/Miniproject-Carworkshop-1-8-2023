package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class InventoryItem extends Entity {
    private String itemName;
    private int quantityAvailable;
    private double pricePerUnit;

    public InventoryItem(String itemName, int quantityAvailable, double pricePerUnit) {
        this.itemName = itemName;
        this.quantityAvailable = quantityAvailable;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public void insertEntity() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO inventory (item_name, quantity_available, price_per_unit) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, itemName);
            preparedStatement.setInt(2, quantityAvailable);
            preparedStatement.setDouble(3, pricePerUnit);
            preparedStatement.executeUpdate();
            System.out.println("Inventory item added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<InventoryItem> getAllInventoryItems() {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM inventory";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int itemId = resultSet.getInt("item_id");
                String itemName = resultSet.getString("item_name");
                int quantityAvailable = resultSet.getInt("quantity_available");
                double pricePerUnit = resultSet.getDouble("price_per_unit");

                InventoryItem item = new InventoryItem(itemName, quantityAvailable, pricePerUnit);
                item.setId(itemId);
                inventoryItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryItems;
    }

    @Override
    public String toString() {
        return "Item ID: " + getId() +
                "\nItem Name: " + itemName +
                "\nQuantity Available: " + quantityAvailable +
                "\nPrice Per Unit: " + pricePerUnit +
                "\n---------------------";
    }

    @Override
    public void viewAllEntities() {
        System.out.println("View Inventory:");
        List<InventoryItem> inventoryItems = getAllInventoryItems();
        for (InventoryItem item : inventoryItems) {
            System.out.println(item);
        }
    }
}
