package store.stock.ui;

import java.util.List;
import store.stock.app.StockCommandService;
import store.stock.app.StockQueryService;
import store.stock.app.dto.AdditionalMessageDto;
import store.stock.app.dto.ProductOrderRequestDto;
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

    public AdditionalMessageDto checkPromotion(ProductOrderRequestDto dto) {
        return commandService.checkPromotion(dto);
    }
}
