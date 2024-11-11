package store.view.output;

public class OutputView {

    private final ProductOutputView productOutputView;

    public OutputView(ProductOutputView productOutputView) {
        this.productOutputView = productOutputView;
    }

    public void printProducts() {
        productOutputView.printGreeting();
    }
}
