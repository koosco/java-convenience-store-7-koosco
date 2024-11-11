package store.stock.app;

import java.util.List;
import store.stock.domain.Stock;
import store.stock.repository.StockRepository;

public class StockQueryService {

    private final StockRepository stockRepository;

    public StockQueryService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> findAllStocks() {
        return stockRepository.findAll();
    }
}
