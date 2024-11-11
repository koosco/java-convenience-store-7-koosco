package store.config.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.stock.domain.Product;
import store.stock.domain.Promotion;
import store.stock.domain.Stock;

public class DataSource {

    private final DataSourceReader dataSourceReader;
    private List<Stock> stocks = new ArrayList<>();

    public DataSource(DataSourceReader dataSourceReader) {
        this.dataSourceReader = dataSourceReader;
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
        findStock(product).ifPresentOrElse(
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

    private Optional<Stock> findStock(Product product) {
        return stocks.stream()
            .filter(stock -> stock.getName().equals(product.getName())).findFirst();
    }
}
