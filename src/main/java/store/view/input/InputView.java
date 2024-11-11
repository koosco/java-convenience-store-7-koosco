package store.view.input;

import java.util.List;
import java.util.function.Supplier;
import store.common.exception.CustomException;
import store.common.exception.ErrorMessage;
import store.stock.app.dto.ProductOrderRequestDto;
import store.stock.domain.Stock;

public class InputView {

    private final ProductInputView productInputView;
    private final MembershipInputView membershipInputView;
    private final PromotionInputView promotionInputView;

    public InputView(ProductInputView productInputView, MembershipInputView membershipInputView,
        PromotionInputView promotionInputView) {
        this.productInputView = productInputView;
        this.membershipInputView = membershipInputView;
        this.promotionInputView = promotionInputView;
    }

    public List<ProductOrderRequestDto> askPurchaseProducts(List<Stock> stocks) {
        return inputWithRetry(() -> productInputView.purchaseProducts(stocks));
    }

    public boolean askForMembership() {
        return inputWithRetry(membershipInputView::askMembership);
    }

    public boolean askWithoutPromotion(String productName, int amount) {
        return inputWithRetry(() -> promotionInputView.askWithoutPromotion(productName, amount));
    }

    private <T> T inputWithRetry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (CustomException e) {
                System.out.println(e.getErrorMessage());
            } catch (NumberFormatException e) {
                System.out.println(ErrorMessage.INVALID_FORMAT_ERROR.get());
            }
        }
    }
}
