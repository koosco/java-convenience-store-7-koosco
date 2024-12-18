package store.stock.domain;

import java.util.List;

public class Product {

    private final static String TO_STRING_DELIMITER = ",";

    private final String name;
    private final int price;
    private int quantity;
    private final String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public Product(String[] parts) {
        this(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);
    }

    public boolean hasPromotion() {
        return !promotion.equals("null");
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotion() {
        if (promotion != null) {
            return promotion;
        }
        return "null";
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.join(TO_STRING_DELIMITER, List.of(
            name,
            String.valueOf(price),
            String.valueOf(quantity),
            getPromotion()));
    }
}
