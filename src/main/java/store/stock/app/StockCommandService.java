package store.stock.app;

import store.stock.repository.StockRepository;

public class StockCommandService {

    private final StockRepository stockRepository;

    public StockCommandService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
}
