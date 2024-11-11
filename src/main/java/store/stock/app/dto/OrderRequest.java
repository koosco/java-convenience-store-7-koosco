package store.stock.app.dto;

public record OrderRequest(String productName, int amount, Boolean applyPromotion) {

    public static OrderRequest createPromotionDto(String name, int amount) {
        return new OrderRequest(name, amount, true);
    }

    public static OrderRequest createNoPromotionDto(String name, int amount) {
        return new OrderRequest(name, amount, false);
    }
}
