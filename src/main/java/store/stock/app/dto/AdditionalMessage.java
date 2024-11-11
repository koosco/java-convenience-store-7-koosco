package store.stock.app.dto;

import store.stock.domain.PromotionMessage;

public record AdditionalMessage(String productName, Integer amount, PromotionMessage message) {

}
