package store.stock.repository;

import java.util.List;
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
}
