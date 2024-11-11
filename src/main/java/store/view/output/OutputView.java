package store.view.output;

import java.util.List;
import store.stock.domain.Order;
import store.stock.domain.Stock;

public class OutputView {

    private final ProductOutputView productOutputView;
    private final OrderOutputView orderOutputView;

    public OutputView(ProductOutputView productOutputView, OrderOutputView orderOutputView) {
        this.productOutputView = productOutputView;
        this.orderOutputView = orderOutputView;
    }

    public void printProducts(List<Stock> stocks) {
        productOutputView.printGreeting();
        productOutputView.printProducts(stocks);
    }

    public void printResult(Order order) {
        orderOutputView.printReceipt(order);
    }
}
