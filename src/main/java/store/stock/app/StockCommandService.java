package store.stock.app;

import java.util.List;
import store.stock.app.dto.AdditionalMessage;
import store.stock.app.dto.OrderRequest;
import store.stock.domain.Order;
import store.stock.domain.Stock;

public class StockCommandService {

    private final StockQueryService queryService;

    public StockCommandService(StockQueryService queryService) {
        this.queryService = queryService;
    }

    public AdditionalMessage checkPromotion(OrderRequest dto) {
        Stock stock = queryService.findStock(dto.productName());
        return stock.checkPromotion(dto.amount());
    }

    public Order purchaseAll(List<Stock> stocks, List<OrderRequest> dtos) {
        Order order = new Order();
        for (OrderRequest dto : dtos) {
            purchase(stocks, order, dto);
        }

        return order;
    }

    private void purchase(List<Stock> stocks, Order order, OrderRequest dto) {
        Stock findStock = stocks.stream().filter(stock -> stock.getName().equals(dto.productName())).findFirst()
            .orElseThrow();
        findStock.purchase(order, dto.amount(), dto.applyPromotion());
    }
}
