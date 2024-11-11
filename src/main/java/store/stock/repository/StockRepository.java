package store.stock.repository;

import java.util.List;
import java.util.Optional;
import store.config.datasource.DataSource;
import store.stock.domain.Stock;

public class StockRepository {

    private final DataSource dataSource;

    public StockRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Stock> findAll() {
        return dataSource.findAllStocks();
    }

    public Optional<Stock> findByName(String name) {
        return dataSource.findStockByName(name);
    }

    public void save(List<Stock> stocks) {
        dataSource.saveStocks(stocks);
    }
}
