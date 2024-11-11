package store.stock.app.dto;

import store.stock.domain.PromotionMessage;

public record AdditionalMessageDto(String productName, Integer amount, PromotionMessage message) {

}
