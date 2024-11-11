package store.stock.ui;

import static store.stock.domain.PromotionMessage.CAN_ADD_ADDITIONAL_PRODUCT;
import static store.stock.domain.PromotionMessage.CAN_NOT_GET_PROMOTION;

import store.stock.app.dto.AdditionalMessage;
import store.stock.app.dto.OrderRequest;
import store.view.input.InputView;

public class AdditionalMessageHandler {

    private final InputView inputView;

    public AdditionalMessageHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public OrderRequest handle(OrderRequest dto, AdditionalMessage additionalMessage) {
        if (additionalMessage.message().equals(CAN_ADD_ADDITIONAL_PRODUCT)) {
            return handleAdditionalProduct(dto);
        }
        if (additionalMessage.message().equals(CAN_NOT_GET_PROMOTION)) {
            return handleCanNotGetPromotion(dto, additionalMessage);
        }
        return new OrderRequest(dto.productName(), dto.amount(), additionalMessage.applyPromotion());
    }

    private OrderRequest handleAdditionalProduct(OrderRequest dto) {
        int amount = dto.amount();
        if (askForPromotion(dto)) {
            return OrderRequest.createPromotionDto(dto.productName(), amount + 1);
        }
        return OrderRequest.createNoPromotionDto(dto.productName(), amount);
    }

    private OrderRequest handleCanNotGetPromotion(OrderRequest dto, AdditionalMessage additionalMessage) {
        if (askWithoutPromotion(dto, additionalMessage)) {
            if (additionalMessage.applyPromotion()) {
                return OrderRequest.createPromotionDto(dto.productName(), dto.amount());
            }
            return OrderRequest.createNoPromotionDto(dto.productName(), dto.amount());
        }
        return OrderRequest.createPromotionDto(dto.productName(), dto.amount() - additionalMessage.amount());
    }

    private boolean askWithoutPromotion(OrderRequest dto, AdditionalMessage additionalMessage) {
        return inputView.askWithoutPromotion(dto.productName(), additionalMessage.amount());
    }

    private boolean askForPromotion(OrderRequest dto) {
        return inputView.askForPromotion(dto.productName());
    }
}
