package store.stock.app.dto;

public record ProductOrderRequestDto(String productName, int amount, Boolean applyPromotion) {

}
