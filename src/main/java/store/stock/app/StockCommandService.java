package store.stock.app;

import store.stock.app.dto.AdditionalMessageDto;
import store.stock.app.dto.ProductOrderRequestDto;
import store.stock.domain.Stock;

public class StockCommandService {

    private final StockQueryService queryService;

    public StockCommandService(StockQueryService queryService) {
        this.queryService = queryService;
    }

    public AdditionalMessageDto checkPromotion(ProductOrderRequestDto dto) {
        Stock stock = queryService.findStock(dto.productName());
        return stock.checkPromotion(dto.amount());
    }
}
