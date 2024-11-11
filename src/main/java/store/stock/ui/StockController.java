package store.stock.ui;

import java.util.List;
import store.stock.app.StockCommandService;
import store.stock.app.StockQueryService;
import store.stock.app.dto.AdditionalMessage;
import store.stock.app.dto.OrderRequest;
import store.stock.domain.Order;
import store.stock.domain.Stock;

public class StockController {

    private final StockQueryService queryService;
    private final StockCommandService commandService;

    public StockController(StockQueryService queryService, StockCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    public List<Stock> findAllStocks() {
        return queryService.findAllStocks();
    }

    public AdditionalMessage checkPromotion(OrderRequest dto) {
        return commandService.checkPromotion(dto);
    }

    public Order purchaseProducts(List<Stock> stocks, List<OrderRequest> orderRequests) {
        return commandService.purchaseAll(stocks, orderRequests);
    }
}
