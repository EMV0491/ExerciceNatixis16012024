public class CartItem {
    private String productName;
    private double price;

    public CartItem(String productName, double price) {
		// We consider two products with the same name but with different price as two different products
        this.productName = productName;
		if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
			return true;
		}
        if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
        CartItem cartItem = (CartItem) obj;
        return Double.compare(cartItem.getPrice(), price) == 0 && productName.equals(cartItem.getProductName());
    }

    @Override
    public int hashCode() {
        return productName.hashCode() + Double.hashCode(price);
    }
}