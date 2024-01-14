import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testAddingItemsWithSameProductNameAndDifferentPrice() {
        ShoppingCart shoppingCart = new ShoppingCart();

        CartItem pen1 = new CartItem("Pen", 1.50);
        CartItem pen2 = new CartItem("Pen", 2.00);

        shoppingCart.addItem(pen1, 3);
        shoppingCart.addItem(pen2, 4);

        assertEquals(3, shoppingCart.getItems().get(pen1));
		assertEquals(4, shoppingCart.getItems().get(pen2));
        assertEquals(2, shoppingCart.getItems().size());
    }

    @Test
    public void testUpdatingQuantityForSameProduct() {
        ShoppingCart shoppingCart = new ShoppingCart();

        CartItem pen1 = new CartItem("Pen", 1.50);
        CartItem pen2 = new CartItem("Pen", 2.00);

        shoppingCart.addItem(pen1, 3);
        shoppingCart.addItem(pen2, 4);
        shoppingCart.addItem(pen1, 2);

        assertEquals(5, shoppingCart.getItems().get(pen1));
        assertEquals(2, shoppingCart.getItems().size());
    }

    @Test
    public void testCalculatingTotal() {
        ShoppingCart shoppingCart = new ShoppingCart();

        CartItem pen = new CartItem("Pen", 1.50);
        CartItem book = new CartItem("Book", 8.00);

        shoppingCart.addItem(pen, 3);
        shoppingCart.addItem(book, 2);

        assertEquals((1.50 * 3 + 8.00 * 2), shoppingCart.calculateTotal(), 0.01);
    }
	
	@Test
	public void testUpdatingCartWithRemoveItem() {
		ShoppingCart shoppingCart = new ShoppingCart();
		CartItem pen = new CartItem("Pen", 1.50);

		shoppingCart.addItem(pen, 5);
		assertEquals(5, shoppingCart.getItems().get(pen));

		shoppingCart.removeItem(pen, 2);
		assertEquals(3, shoppingCart.getItems().get(pen));

		shoppingCart.removeItem(pen, 3);
		assertNull(shoppingCart.getItems().get(pen));
	}
	
	@Test
	public void testCartItemWithNegativePrice() {
		assertThrows(IllegalArgumentException.class, () -> {
			new CartItem("InvalidItem", -1.50);
		});
	}

    @Test
    public void testAddingItemsWithNegativeQuantity() {
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem pen = new CartItem("Pen", 1.50);

        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.addItem(pen, -3);
        });
    }

    @Test
    public void testRemovingItemsWithNegativeQuantity() {
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem pen = new CartItem("Pen", 1.50);

        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.removeItem(pen, -2);
        });
    }

    @Test
    public void testRemovingMoreItemsThanAvailable() {
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem pen = new CartItem("Pen", 1.50);

        shoppingCart.addItem(pen, 2);

        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.removeItem(pen, 3);
        });
    }

    @Test
    public void testRemovingNonexistentItem() {
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem pen = new CartItem("Pen", 1.50);

        assertThrows(IllegalArgumentException.class, () -> {
            shoppingCart.removeItem(pen, 1);
        });
    }
}