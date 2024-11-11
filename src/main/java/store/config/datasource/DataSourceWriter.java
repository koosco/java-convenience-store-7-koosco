package store.config.datasource;

import static store.config.datasource.DataSourceConst.PRODUCT_FILE_PATH;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.stock.domain.Product;
import store.stock.domain.Stock;

public class DataSourceWriter {

    public void save(List<Stock> stocks) {
        try {
            List<String> strings = new ArrayList<>();
            addHeader(strings);
            stocks.forEach(stock -> saveStock(stock, strings));
            writeProducts(strings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveStock(Stock stock, List<String> strings) {
        addProducts(stock, strings);
    }

    private static void writeProducts(List<String> strings) throws IOException {
        Files.write(Paths.get(PRODUCT_FILE_PATH.get()), strings);
    }

    private static void addHeader(List<String> strings) throws IOException {
        List<String> productFile = readDataFromFile(PRODUCT_FILE_PATH.get());
        String header = productFile.getFirst();
        strings.add(header);
    }

    private static void addProducts(Stock stock, List<String> strings) {
        Optional<Product> promotionProduct = stock.getPromotionProduct();
        if (promotionProduct.isPresent() && stock.getNormalProduct().isEmpty()) {
            // promotion 재고가 존재하고 일반 재고가 없는 경우
            strings.add(promotionProduct.get().toString());
            return;
        }

        stock.getPromotionProduct().ifPresent(product -> strings.add(product.toString()));
        stock.getNormalProduct().ifPresent(product -> strings.add(product.toString()));
    }

    private static List<String> readDataFromFile(String fileName) throws IOException {
        return Files.readAllLines(Paths.get(fileName));
    }
}
