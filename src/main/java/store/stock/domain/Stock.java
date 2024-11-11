package store.stock.domain;

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
}
