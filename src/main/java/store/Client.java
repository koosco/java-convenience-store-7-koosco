package store;

import java.util.List;
import store.membership.ui.MembershipController;
import store.stock.app.dto.AdditionalMessage;
import store.stock.app.dto.OrderRequest;
import store.stock.domain.Order;
import store.stock.domain.Stock;
import store.stock.ui.AdditionalMessageHandler;
import store.stock.ui.StockController;
import store.view.input.InputView;
import store.view.output.OutputView;

public class Client {

    private final InputView inputView;
    private final OutputView outputView;
    private final StockController stockController;
    private final MembershipController membershipController;
    private final AdditionalMessageHandler messageHandler;

    public Client(InputView inputView, OutputView outputView, StockController stockController,
        MembershipController membershipController, AdditionalMessageHandler messageHandler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.stockController = stockController;
        this.membershipController = membershipController;
        this.messageHandler = messageHandler;
    }

    public void run() {
        do {
            runOnce();
        } while (inputView.askContinue());
    }

    private void runOnce() {
        List<Stock> stocks = stockController.findAllStocks();
        printProducts(stocks);

        Order order = createOrder(stocks);
        applyMembership(order);
        outputView.printResult(order);
    }

    private void printProducts(List<Stock> stocks) {
        outputView.printProducts(stocks);
    }

    private Order createOrder(List<Stock> stocks) {
        List<OrderRequest> requestOrderDtos = inputView.askPurchaseProducts(stocks);
        List<OrderRequest> orderRequests = handleAdditionalMessages(requestOrderDtos);
        return stockController.purchaseProducts(stocks, orderRequests);
    }

    private List<OrderRequest> handleAdditionalMessages(List<OrderRequest> dtos) {
        return dtos.stream().map(this::handleAdditionalMessage).toList();
    }

    private OrderRequest handleAdditionalMessage(OrderRequest dto) {
        AdditionalMessage additionalMessage = stockController.checkPromotion(dto);
        return messageHandler.handle(dto, additionalMessage);
    }

    private void applyMembership(Order order) {
        if (inputView.askForMembership()) {
            membershipController.applyMembership(order);
        }
    }
}
