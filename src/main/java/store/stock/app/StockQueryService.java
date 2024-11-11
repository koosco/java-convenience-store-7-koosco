package store.stock.app;

import java.util.List;
import store.common.exception.CustomException;
import store.common.exception.ErrorMessage;
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

    public Stock findStock(String name) {
        return stockRepository.findByName(name)
            .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_PRODUCT));
    }
}
