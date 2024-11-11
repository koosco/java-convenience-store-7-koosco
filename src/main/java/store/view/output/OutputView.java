package store.view.output;

import java.util.List;
import store.stock.domain.Stock;

public class OutputView {

    private final ProductOutputView productOutputView;

    public OutputView(ProductOutputView productOutputView) {
        this.productOutputView = productOutputView;
    }

    public void printProducts(List<Stock> stocks) {
        productOutputView.printGreeting();
        productOutputView.printProducts(stocks);
    }
}
