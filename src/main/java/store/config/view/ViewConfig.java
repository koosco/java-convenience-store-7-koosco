package store.config.view;


import store.view.input.ContinueInputView;
import store.view.input.InputView;
import store.view.input.MembershipInputView;
import store.view.input.ProductInputView;
import store.view.input.PromotionInputView;
import store.view.output.OrderOutputView;
import store.view.output.OutputView;
import store.view.output.ProductOutputView;

public class ViewConfig {

    public InputView inputView() {
        return new InputView(productInputView(), membershipInputView(), promotionInputView(), continueInputView());
    }

    public OutputView outputView() {
        return new OutputView(productOutputView(), orderOutputView());
    }

    public ProductOutputView productOutputView() {
        return new ProductOutputView();
    }

    public ProductInputView productInputView() {
        return new ProductInputView();
    }

    public MembershipInputView membershipInputView() {
        return new MembershipInputView();
    }

    public PromotionInputView promotionInputView() {
        return new PromotionInputView();
    }

    public ContinueInputView continueInputView() {
        return new ContinueInputView();
    }

    public OrderOutputView orderOutputView() {
        return new OrderOutputView();
    }
}
