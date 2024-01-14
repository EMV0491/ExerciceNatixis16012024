import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();

        CartItem pen = new CartItem("Pen", 1.50);
        CartItem book = new CartItem("Book", 8.00);

        shoppingCart.addItem(pen, 3);
        shoppingCart.addItem(book, 2);

        printReceipt(shoppingCart);
        generateJsonFile(shoppingCart, "c:\\temp\\cart.json");
    }

    private static void printReceipt(ShoppingCart shoppingCart) {
        System.out.println("Product\t\tQuantity\t\tPrice\t\tTotal");
        System.out.println("--------------------------------------------------------------------------------");

        for (Map.Entry<CartItem, Integer> entry : shoppingCart.getItems().entrySet()) {
            CartItem item = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("" + item.getProductName() + "\t\t" + quantity + "\t\t" + item.getPrice() + "\t\t" + (item.getPrice() * quantity));
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Total" + "\t\t\t\t\t\t\t\t" + shoppingCart.calculateTotal());
    }

    private static void generateJsonFile(ShoppingCart shoppingCart, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(filePath), shoppingCart);
            System.out.println("JSON file generated successfully at " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
