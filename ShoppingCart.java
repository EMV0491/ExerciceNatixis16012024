import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<CartItem, Integer> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    public void addItem(CartItem item, int quantity) {
        if (quantity > 0) {
            items.put(item, items.getOrDefault(item, 0) + quantity);
        } else {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
    }

    public void removeItem(CartItem item, int quantity) {
        if (items.containsKey(item)) {
            int currentQuantity = items.get(item);
            if (quantity >= 0 && quantity <= currentQuantity) {
                int newQuantity = currentQuantity - quantity;
                if (newQuantity > 0) {
                    items.put(item, newQuantity);
                } else {
                    items.remove(item);
                }
            } else {
                throw new IllegalArgumentException("Invalid quantity for removal.");
            }
        } else {
            throw new IllegalArgumentException("Item not found in the cart.");
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<CartItem, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
	
	public Map<CartItem, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }
}