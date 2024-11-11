package store.config.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.stock.domain.Product;
import store.stock.domain.Promotion;
import store.stock.domain.Stock;

public class DataSource {

    private final DataSourceReader dataSourceReader;
    private final DataSourceWriter dataSourceWriter;
    private List<Stock> stocks = new ArrayList<>();

    public DataSource(DataSourceReader dataSourceReader, DataSourceWriter dataSourceWriter) {
        this.dataSourceReader = dataSourceReader;
        this.dataSourceWriter = dataSourceWriter;
        initStock();
    }

    public List<Stock> findAllStocks() {
        return stocks;
    }

    private void initStock() {
        List<Promotion> promotions = dataSourceReader.loadPromotions();
        List<Product> products = dataSourceReader.loadProducts();

        stocks = new ArrayList<>();
        for (Product product : products) {
            Promotion promotion = findPromotion(promotions, product.getPromotion());
            addToStock(product, promotion);
        }
    }

    private void addToStock(Product product, Promotion promotion) {
        findStockByProduct(product).ifPresentOrElse(
            stock -> stock.addProduct(product, promotion),
            () -> {
                Stock stock = new Stock(product.getName(), product.getPrice());
                stock.addProduct(product, promotion);
                stocks.add(stock);
            }
        );
    }

    private Promotion findPromotion(List<Promotion> promotions, String name) {
        return promotions.stream()
            .filter(promotion -> promotion.getName().equals(name))
            .findFirst().orElse(null);
    }

    private Optional<Stock> findStockByProduct(Product product) {
        return stocks.stream()
            .filter(stock -> stock.getName().equals(product.getName())).findFirst();
    }

    public Optional<Stock> findStockByName(String name) {
        return stocks.stream()
            .filter(stock -> stock.getName().equals(name)).findFirst();
    }

    public void saveStocks(List<Stock> stocks) {
        dataSourceWriter.save(stocks);
        initStock();
    }
}
