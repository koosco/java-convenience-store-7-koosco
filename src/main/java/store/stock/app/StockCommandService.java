package store.stock.app;

import store.stock.app.dto.AdditionalMessage;
import store.stock.app.dto.OrderRequest;
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
}
