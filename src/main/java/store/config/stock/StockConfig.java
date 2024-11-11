package store.config.stock;

import store.config.datasource.DataSourceConfig;
import store.config.view.ViewConfig;
import store.stock.app.StockCommandService;
import store.stock.app.StockQueryService;
import store.stock.repository.StockRepository;
import store.stock.ui.AdditionalMessageHandler;
import store.stock.ui.StockController;

public class StockConfig {

    private final DataSourceConfig dataSourceConfig;
    private final ViewConfig viewConfig;

    public StockConfig(DataSourceConfig dataSourceConfig, ViewConfig viewConfig) {
        this.dataSourceConfig = dataSourceConfig;
        this.viewConfig = viewConfig;
    }

    public StockController stockController() {
        return new StockController(stockQueryService(), stockService());
    }

    public StockCommandService stockService() {
        return new StockCommandService(stockQueryService(), stockRepository());
    }

    public StockQueryService stockQueryService() {
        return new StockQueryService(stockRepository());
    }

    public StockRepository stockRepository() {
        return new StockRepository(dataSourceConfig.dataSource());
    }

    public AdditionalMessageHandler additionalMessageHandler() {
        return new AdditionalMessageHandler(viewConfig.inputView());
    }
}
