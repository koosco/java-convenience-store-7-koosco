package store.stock.domain;

import java.util.Optional;
import store.stock.app.dto.AdditionalMessage;
import store.stock.app.dto.FreeProductDto;
import store.stock.app.dto.ProductDto;

public class Stock {

    private final String name;
    private final int price;
    private int promotionQuantity;
    private int noPromotionQuantity;
    private Promotion promotion;

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void addProduct(Product product, Promotion promotion) {
        if (!product.hasPromotion()) {
            noPromotionQuantity = product.getQuantity();
            return;
        }
        promotionQuantity = product.getQuantity();
        noPromotionQuantity = 0;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getNoPromotionQuantity() {
        return noPromotionQuantity;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public int getQuantity() {
        return noPromotionQuantity + promotionQuantity;
    }

    public boolean hasPromotion() {
        return promotion != null;
    }

    public int getPrice() {
        return price;
    }

    public String getPromotionName() {
        return promotion.getName();
    }

    public AdditionalMessage checkPromotion(int amount) {
        if (promotion == null || !promotion.isApplicable(promotionQuantity)) {
            return new AdditionalMessage(name, null, PromotionMessage.NO_ADDITIONAL_MESSAGE, false);
        }

        int promotionProductAmount = Math.min(amount, promotionQuantity);
        int normalProductAmount = amount - promotionProductAmount;

        return promotion.apply(name, promotionQuantity, promotionProductAmount, normalProductAmount);
    }

    public void purchase(Order order, int amount, boolean applyPromotion) {
        if (promotion == null || !promotion.isApplicable(promotionQuantity) || !applyPromotion) {
            order.addProduct(new ProductDto(name, amount, price, false));
            sell(amount);
            return;
        }

        order.addProduct(new ProductDto(name, amount, price, true));
        order.addFreeProduct(
            new FreeProductDto(name, promotion.getPromotion(Math.min(amount, promotionQuantity)), price));
        sell(amount);
    }

    private void sell(int amount) {
        if (promotionQuantity >= amount) {
            promotionQuantity -= amount;
            return;
        }
        promotionQuantity = 0;
        noPromotionQuantity -= amount - promotionQuantity;
    }

    public Optional<Product> getPromotionProduct() {
        if (promotion != null) {
            return Optional.of(new Product(name, price, promotionQuantity, getPromotionName()));
        }
        return Optional.empty();
    }

    public Optional<Product> getNormalProduct() {
        if (noPromotionQuantity > 0) {
            return Optional.of(new Product(name, price, noPromotionQuantity, null));
        }
        return Optional.empty();
    }

    public Product getProduct() {
        return new Product(name, price, 0, null);
    }
}
